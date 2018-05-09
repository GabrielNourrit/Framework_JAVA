package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MelListener extends Remote {
	public void nouveauMailRecu(MelCell message) throws RemoteException;
	public void nouveauMailEnvoye(MelCell message) throws RemoteException;
}
