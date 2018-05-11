package util;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javafx.scene.control.Alert.AlertType;
import parametrage.PropertiesClient;

public class Connectable<T extends Remote> {
	/**
	 * Creation d'une instance de Registry
	 */
	private static Registry registry;
	static {
		try {
			registry = java.rmi.registry.LocateRegistry.getRegistry(PropertiesClient.getAdresseServeur(),PropertiesClient.getNumeroPort());
		} catch (RemoteException e) {
			Fenetre.creatAlert(AlertType.ERROR, "Registry", "Annuaire du Registre non trouver");
		}
	}
	/**
	 * Constructeur
	 */
	public Connectable(){		
	}
	
	/**
	 * Permet d'effectuer des demandes au Serveur
	 * @param lookup : le nom de l'objet dans l'annuaire que l'on recherche
	 * @return Une interface du objet demander au Serveur
	 * @throws Exception
	 */
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
