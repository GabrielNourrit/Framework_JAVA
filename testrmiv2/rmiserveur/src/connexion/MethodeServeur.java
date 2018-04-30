package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class MethodeServeur implements Remote {
	protected String name;
	
	protected MethodeServeur(String n) throws RemoteException {
		name = n ;
	}
	
	protected String name() { return this.name; }
}
