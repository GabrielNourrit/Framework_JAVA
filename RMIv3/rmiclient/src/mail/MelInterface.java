package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import fichier.Fichier;
import interfaceGraph.mail.melCell;

public interface MelInterface extends Remote{

	public void test() throws RemoteException;
	public String test2() throws RemoteException;
	public ArrayList<Utilisateur> getAllUsers() throws RemoteException;
	public void saveMessage(Utilisateur u, String message) throws RemoteException;
}
