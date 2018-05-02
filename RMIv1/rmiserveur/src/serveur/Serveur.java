package serveur;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Map.Entry;

import BaseDeDonnee.MethodeServeur;
import parametrage.PropertiesServeur;

public class Serveur {
	
	public Serveur(Map<String,MethodeServeur> l) throws Exception,NumberFormatException {
		Registry registry = java.rmi.registry.LocateRegistry.createRegistry(PropertiesServeur.getPortServeur());
		for (Entry<String, MethodeServeur> t : l.entrySet()) {			
			registry.bind(t.getKey(), (Remote) t.getValue());
		}
	}
	/*public static void main (String[] argv) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			Addition Hello = new Addition();
			Connexion c = new Connexion();
			Naming.rebind("rmi://localhost/ABC", Hello);
			Naming.rebind("rmi://localhost/c", c);
			System.out.println("Addition Server is ready.");
		}catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}*/
	
	
}
