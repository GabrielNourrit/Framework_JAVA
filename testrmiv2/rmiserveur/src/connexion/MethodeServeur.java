package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract class MethodeServeur implements Remote {
	protected String name;
	
	public MethodeServeur(String n) throws RemoteException {
		name = n ;
	}
	
	public String name() { return this.name; }
}
