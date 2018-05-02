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
import parametrage.PropertiesServeur;
import parametrage.SettingServeurJVM;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Définition du type de SGBD utilisé
		SGBDInterface sgbd;
		
		switch(PropertiesServeur.getTypeSGBD().toUpperCase()) {
			case "MYSQL": sgbd = new SGBDMySQL();break;
			default : sgbd = new SGBDOracle();break;
		}
		
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("SGBD", sgbd);

		
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
