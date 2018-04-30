package serveur;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;

import connexion.Connexion;
import connexion.MethodeServeur;

public class Serveur {

	private ArrayList<MethodeServeur> l;
	
	public Serveur(ArrayList<MethodeServeur> l) throws RemoteException, MalformedURLException {
		String url = "";
		java.rmi.registry.LocateRegistry.createRegistry(1099);
		for (MethodeServeur t : l) {
			url += "rmi://localhost/";
			url += t.name();
			Naming.rebind(url, t);
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
