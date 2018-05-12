package util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javafx.scene.control.Alert.AlertType;
import parametrage.PropertiesClient;

public class Connectable<T extends Remote> {
	
	private static Registry registry;
	static {
		try {
			registry = java.rmi.registry.LocateRegistry.getRegistry(PropertiesClient.getAdresseServeur(),PropertiesClient.getNumeroPort());
		} catch (RemoteException e) {
			Fenetre.creatAlert(AlertType.ERROR, "Registry", "Annuaire du Registre non trouver");
		}
	}
	
	public Connectable(){		
	}
	

	@SuppressWarnings("unchecked")
	public T connexion(String lookup) throws Exception {
		try {
			return (T) registry.lookup(lookup);
		} catch (Exception e) {
			//Fenetre.creatAlert(AlertType.ERROR,"Registry","Recherche Introuvable : '"+ lookup+"'");
			throw new Exception("Recherche Introuvable : '"+lookup+"'");
		}
	}
	
}
