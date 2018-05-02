package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.connexion.ConnexionInterface;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDInterface;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import parametrage.SettingServeurJVM;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		
		// Définition du type de SGBD utilisé
		SGBDInterface sgbd;
		
		//sgbd = new SGBDOracle();
		
		sgbd = new SGBDMySQL();

		
		Map<String ,MethodeServeur> listBind = new HashMap<>();
		listBind.put("SGBD", sgbd);

		
		//Création du serveur
		new Serveur(listBind);
		System.out.println("Lancement du Serveur");
		
	}
	
}
