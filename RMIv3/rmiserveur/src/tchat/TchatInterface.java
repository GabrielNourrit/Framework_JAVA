package tchat;

import java.rmi.*;

import BaseDeDonnee.MethodeServeur;

public interface TchatInterface extends Remote,MethodeServeur {
	public void envoyerMessage(String s, String groupe) throws RemoteException;
	public String getHistorique(String groupe) throws RemoteException;
	public void addTchatListener (TchatListener listener, String groupe) throws java.rmi.RemoteException;
	public void removeTchatListener (TchatListener listener, String groupe) throws java.rmi.RemoteException;
}
