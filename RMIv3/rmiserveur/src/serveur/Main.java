package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import connexion.Connexion;
import connexion.ConnexionInterface;
import fichier.GestionFichier;
import fichier.GestionFichierInterface;
import mail.Mel;
import mail.MelInterface;
import parametrage.PropertiesServeur;
import parametrage.SettingServeurJVM;
import tchat.Tchat;
import tchat.TchatInterface;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// D�finition du type de SGBD utilis�
		SGBD sgbd;
		TchatInterface tchat;
		GestionFichierInterface fichier;
		switch(PropertiesServeur.getTypeSGBD().toUpperCase()) {
			case "MYSQL": sgbd = new SGBDMySQL();System.out.println("MYSQL");break;
			case "ORACLE": sgbd =new SGBDOracle();System.out.println("ORACLE");break;
			default : sgbd = new SGBDOracle();break;
		}
		tchat = new Tchat();
		fichier = new GestionFichier(sgbd);
		MelInterface mel = new Mel("salut");
		ConnexionInterface connexion = new Connexion(sgbd);
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("Tchat", tchat);
		listBind.put("Fichier", fichier);
		listBind.put("Connexion", connexion);
		listBind.put("Mel", mel);

		
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
