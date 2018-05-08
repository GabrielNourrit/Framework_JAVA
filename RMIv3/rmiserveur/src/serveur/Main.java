package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.gestionUtilisateur.OperationUtilisateur;
import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import BaseDeDonnee.gestionUtilisateur.Types;
import BaseDeDonnee.gestionUtilisateur.TypesInterface;
import BaseDeDonnee.gestionUtilisateur.Utilisateurs;
import BaseDeDonnee.gestionUtilisateur.UtilisateursInterface;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import connexion.Connexion;
import connexion.ConnexionInterface;
import fichier.GestionFichier;
import fichier.GestionFichierInterface;
import groupes.Groupes;
import groupes.GroupesInterface;
import mail.Mel;
import mail.MelInterface;
import parametrage.PropertiesServeur;
import parametrage.SettingServeurJVM;
import tchat.Tchat;
import tchat.TchatInterface;

public class Main {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, ClassNotFoundException, SQLException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Ajout d'un type de SGBD
		SGBD.addTypeSGBD("oracle", new SGBDOracle());
		SGBD.addTypeSGBD("mysql", new SGBDMySQL());
		
		//Determinie le type de SGBD utiliser
		System.out.println(PropertiesServeur.getTypeSGBD());
		SGBD sgbd = SGBD.determine(PropertiesServeur.getTypeSGBD());
		
		List<Integer> s = new ArrayList<>();
		s.add(1);
		s.add(2);
		
		TchatInterface tchat = new Tchat(s);
		GestionFichierInterface fichier = new GestionFichier(sgbd);
		ConnexionInterface connexion = new Connexion(sgbd);
		OperationUtilisateurInterface ou = new OperationUtilisateur(sgbd);
		UtilisateursInterface ui = new Utilisateurs(sgbd);
		GroupesInterface gi = new Groupes(sgbd);
		TypesInterface ti = new Types(sgbd);
		MelInterface m = new Mel(sgbd);
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("Tchat", tchat);
		listBind.put("Fichier", fichier);
		listBind.put("Connexion", connexion);		
		listBind.put("Utilisateurs", ui);		
		listBind.put("OperationUtilisateur", ou);
		listBind.put("Groupes", gi);
		listBind.put("Types", ti);
		listBind.put("Mel", m);
		//Cr�ation du serveur
		try{	
			new Serveur(listBind);
			System.out.println("Lancement du Serveur");
			
		}catch(NumberFormatException e){
			System.err.println("Num�ro de port non d�finit");
		} catch (Exception e) {
			System.err.println("Erreur Aux lancement du Serveur : "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
