package connexion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class MethodeServeur extends UnicastRemoteObject {
	protected String name;
	
	public MethodeServeur(String n) throws RemoteException {
		name = n ;
	}
	
	public String name() { return this.name; }
}
