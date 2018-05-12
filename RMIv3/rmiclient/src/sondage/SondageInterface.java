package sondage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import util.Utilisateur;

public interface SondageInterface extends Remote {
	public boolean creerSondage(String owner, String question, String reponses,boolean multiple, String date) throws RemoteException;
	public List<SondageObj> getSondageFait(Utilisateur owner) throws RemoteException;
	public List<SondageObj> getSondageNew(Utilisateur owner) throws RemoteException;
	public boolean updateSondage(String actor,int id, ArrayList<String> ret) throws RemoteException;
	public void addTchatListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException;
	public void removeTchatListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException;
	public void addTchatListenerNewSondage (SondageListener listener) throws java.rmi.RemoteException;
	public void removeTchatListenerNewSondage  (SondageListener listener) throws java.rmi.RemoteException;
}
