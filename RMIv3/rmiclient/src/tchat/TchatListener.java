package tchat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TchatListener extends Remote {
	
	/**
	 * Notification d'un nouveau message
	 * @param message le nouveau message
	 * @param groupe l'id du groupe du tchat
	 * @throws RemoteException
	 */
	public void nouveauMessage(String message, Integer groupe) throws RemoteException;
}
