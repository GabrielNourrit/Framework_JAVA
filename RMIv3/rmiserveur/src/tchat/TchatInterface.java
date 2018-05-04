package tchat;

import java.rmi.*;

import BaseDeDonnee.MethodeServeur;

public interface TchatInterface extends Remote, MethodeServeur {
	public void envoyerMessage(String msg) throws RemoteException;
	public String getMessage() throws RemoteException;
	public String getHistorique() throws RemoteException;
}
