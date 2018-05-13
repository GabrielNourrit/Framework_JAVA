package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MelListener extends Remote {
	
	/**
	 * Notification d'un nouveau mail recu
	 * @param message le nouveau mail
	 * @throws RemoteException
	 */
	public void nouveauMailRecu(MelCell message) throws RemoteException;
	
	/**
	 * Notification d'un nouveau mail envoye
	 * @param message le nouveau mail
	 * @throws RemoteException
	 */
	public void nouveauMailEnvoye(MelCell message) throws RemoteException;
}
