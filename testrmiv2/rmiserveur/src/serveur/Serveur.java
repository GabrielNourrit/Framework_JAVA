package serveur;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.ArrayList;

import connexion.MethodeServeur;

public class Serveur {
	
	public Serveur(ArrayList<MethodeServeur> l) throws RemoteException, MalformedURLException, AlreadyBoundException {
		Registry registry = java.rmi.registry.LocateRegistry.createRegistry(1099);
		for (MethodeServeur t : l) {
			registry.bind(t.getClass().getSimpleName(), UnicastRemoteObject.exportObject(t, 0));
		}
	}
}
