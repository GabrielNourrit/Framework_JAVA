package groupes;

import java.rmi.Remote;
import java.rmi.RemoteException;

import util.Groupe;

public interface GroupeListener extends Remote {
	public void nouveauGroupe(Groupe g) throws RemoteException;	
}
