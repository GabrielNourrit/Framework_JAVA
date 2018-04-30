package serveur;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.ArrayList;

import connexion.Connexion;
import connexion.ConnexionInterface;
import connexion.MethodeServeur;

public class Serveur {

	private ArrayList<MethodeServeur> l;

	public Serveur(ArrayList<MethodeServeur> l) throws RemoteException, MalformedURLException, AlreadyBoundException {
		String url = "";
		Registry registry = java.rmi.registry.LocateRegistry.createRegistry(1099);
		/*ConnexionInterface c = new Connexion("Connexion");
		registry.bind("Connexion", UnicastRemoteObject.exportObject(c, 0));
		c.getClass().getSimpleName();*/
		for (MethodeServeur t : l)
			registry.bind(t.getClass().getSimpleName(), UnicastRemoteObject.exportObject(t, 0));
			//registry.bind("Connexion", (MethodeServeur) UnicastRemoteObject.exportObject(t, 0));
			/*url += "rmi://localhost/";
			url += t.name();
			Naming.rebind(url, t);
		}*/
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
