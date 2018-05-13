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
import BaseDeDonnee.gestionUtilisateur.GestionType;
import BaseDeDonnee.gestionUtilisateur.GestionTypeInterface;
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
import sondage.Sondage;
import sondage.SondageInterface;
import tchat.Tchat;
import tchat.TchatInterface;
import util.Groupe;

public class Main {
	
	private static TchatInterface tchat;
	
	private static GestionFichierInterface fichier;
	private static ConnexionInterface connexion;
	private static OperationUtilisateurInterface ou ;
	private static UtilisateursInterface ui;
	private static GroupesInterface gi;
	private static TypesInterface ti;
	private static MelInterface m;
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, ClassNotFoundException, SQLException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Ajout d'un type de SGBD
		SGBD.addTypeSGBD("oracle", new SGBDOracle());
		SGBD.addTypeSGBD("mysql", new SGBDMySQL());
		
		//Determinie le type de SGBD utiliser
		System.out.println(PropertiesServeur.getTypeSGBD());
		SGBD sgbd = SGBD.determine(PropertiesServeur.getTypeSGBD());
		
		List<Groupe> s = sgbd.getGroupes();
		
		
		SondageInterface so = new Sondage(sgbd);
		tchat = new Tchat(s);
		fichier = new GestionFichier(sgbd);
		connexion = new Connexion(sgbd);
		ou = new OperationUtilisateur(sgbd);
		ui = new Utilisateurs(sgbd);
		gi = new Groupes(sgbd);
		ti = new Types(sgbd);
		m = new Mel(sgbd);
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("Tchat", tchat);
		listBind.put("Fichier", fichier);
		listBind.put("Connexion", connexion);		
		listBind.put("Utilisateurs", ui);		
		listBind.put("OperationUtilisateur", ou);
		listBind.put("Groupes", gi);
		listBind.put("Types", ti);
		listBind.put("Mel", m);
		listBind.put("Sondage", so);
		//Creation du serveur
		try{	
			new Serveur(listBind);
			System.out.println("Lancement du Serveur");
			
		}catch(NumberFormatException e){
			System.err.println("Numero de port non definit");
		} catch (Exception e) {
			System.err.println("Erreur Aux lancement du Serveur : "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
