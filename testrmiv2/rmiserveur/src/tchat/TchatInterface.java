package tchat;

import java.rmi.*;

public interface TchatInterface extends Remote {
	public void envoyerMessage(String msg) throws RemoteException;
	public String getMessage() throws RemoteException;
	public String getHistorique() throws RemoteException;
}
