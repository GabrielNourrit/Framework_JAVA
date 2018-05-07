package serveur;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Map.Entry;

import BaseDeDonnee.MethodeServeur;
import parametrage.PropertiesServeur;

public class Serveur {
	
	public Serveur(Map<String,MethodeServeur> l) throws Exception,NumberFormatException {
		Registry registry = java.rmi.registry.LocateRegistry.createRegistry(PropertiesServeur.getPortServeur());
		for (Entry<String, MethodeServeur> t : l.entrySet()) {		
			System.out.println("-"+t.getKey());
			registry.bind(t.getKey(), UnicastRemoteObject.exportObject(t.getValue(),0));
		}
	}	
}