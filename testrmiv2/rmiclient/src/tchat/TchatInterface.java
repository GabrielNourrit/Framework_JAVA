package tchat;

import java.rmi.*;

public interface TchatInterface extends Remote {
	public String getName() throws RemoteException;
	public void envoyerMessage(String msg) throws RemoteException;
	public void setClient(TchatInterface c)throws RemoteException;
	public TchatInterface getClient() throws RemoteException;
	/*public String getContenu() throws RemoteException;
	public String getInfo() throws RemoteException;*/
	public String getMessage() throws RemoteException;
	public String getHistorique() throws RemoteException;
	//public String[] historique() throws RemoteException;
}
