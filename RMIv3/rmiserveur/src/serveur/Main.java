package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.sgbd.SGBDInterface;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import fichier.Fichier;
import fichier.FichierInterface;
import parametrage.PropertiesServeur;
import parametrage.SettingServeurJVM;
import tchat.Tchat;
import tchat.TchatInterface;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Définition du type de SGBD utilisé
		SGBDInterface sgbd;
		TchatInterface tchat;
		FichierInterface fichier;
		switch(PropertiesServeur.getTypeSGBD().toUpperCase()) {
			case "MYSQL": sgbd = new SGBDMySQL();System.out.println("MYSQL");break;
			case "ORACLE": sgbd =new SGBDOracle();System.out.println("ORACLE");break;
			default : sgbd = new SGBDOracle();break;
		}
		tchat = new Tchat();
		fichier = new Fichier();
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("SGBD", sgbd);
		listBind.put("Tchat", tchat);
		listBind.put("Fichier", fichier);

		
		//Création du serveur
		try{	
			new Serveur(listBind);
			System.out.println("Lancement du Serveur");
			
		}catch(NumberFormatException e){
			System.err.println("Numéro de port non définit");
		} catch (Exception e) {
			System.err.println("Erreur Aux lancement du Serveur : "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
