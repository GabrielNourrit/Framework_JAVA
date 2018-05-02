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
		
		// D�finition du type de SGBD utilis�
		SGBDInterface sgbd;
		
		switch(PropertiesServeur.getTypeSGBD().toUpperCase()) {
			case "MYSQL": sgbd = new SGBDMySQL();break;
			default : sgbd = new SGBDOracle();break;
		}
		
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("SGBD", sgbd);

		
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
