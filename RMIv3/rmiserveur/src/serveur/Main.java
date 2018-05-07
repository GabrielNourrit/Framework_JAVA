package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.gestionUtilisateur.OperationUtilisateur;
import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import BaseDeDonnee.gestionUtilisateur.Utilisateurs;
import BaseDeDonnee.gestionUtilisateur.UtilisateursInterface;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import connexion.Connexion;
import connexion.ConnexionInterface;
import fichier.GestionFichier;
import fichier.GestionFichierInterface;
import parametrage.PropertiesServeur;
import parametrage.SettingServeurJVM;
import tchat.Tchat;
import tchat.TchatInterface;

public class Main {
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Ajout d'un type de SGBD
		SGBD.addTypeSGBD("oracle", new SGBDOracle());
		SGBD.addTypeSGBD("mysql", new SGBDMySQL());
		
		//Determinie le type de SGBD utiliser
		SGBD sgbd = SGBD.determine(PropertiesServeur.getTypeSGBD());
		
		TchatInterface tchat = new Tchat();
		GestionFichierInterface fichier = new GestionFichier(sgbd);
		ConnexionInterface connexion = new Connexion(sgbd);
		OperationUtilisateurInterface ou = new OperationUtilisateur(sgbd);
		UtilisateursInterface ui = new Utilisateurs(sgbd);
		
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("Tchat", tchat);
		listBind.put("Fichier", fichier);
		listBind.put("Connexion", connexion);		
		listBind.put("Utilisateurs", ui);		
		listBind.put("OperationUtilisateur", ou);
		
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
