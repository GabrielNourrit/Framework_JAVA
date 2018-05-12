package tchat;

import java.rmi.*;

public interface TchatInterface extends Remote {

	public void envoyerMessage(String s, Integer groupe) throws RemoteException;
	public String getHistorique(Integer groupe) throws RemoteException;
	public void addTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException;
	public void removeTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException;
	public void ajouterGroupeTchat(Integer idGr) throws java.rmi.RemoteException;
}
