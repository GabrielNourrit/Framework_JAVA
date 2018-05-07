package tchat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TchatListener extends Remote {
	public void nouveauMessage(String message, Integer groupe) throws RemoteException;
}
