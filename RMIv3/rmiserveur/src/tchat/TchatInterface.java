package tchat;

import java.rmi.*;

import BaseDeDonnee.MethodeServeur;

public interface TchatInterface extends Remote,MethodeServeur {
	/**
	 * Envoie d'un nouveau message
	 * @param s le message
	 * @param groupe le groupe auquel est destine le message
	 * @throws RemoteException
	 */
	public void envoyerMessage(String s, Integer groupe) throws RemoteException;
	
	/**
	 * recuperer l'historique des messages envoyes
	 * @param groupe l'id du group du tchat
	 * @return l'historique des messages
	 * @throws RemoteException
	 */
	public String getHistorique(Integer groupe) throws RemoteException;
	
	/**
	 * Ajoute un listener sur un tchat
	 * @param listener le listener
	 * @param groupe l'id du groupe du tchat
	 * @throws java.rmi.RemoteException
	 */
	public void addTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException;
	
	/**
	 * Supprime un listener sur un tchat
	 * @param listener le listener
	 * @param groupe l'id du groupe du tchat
	 * @throws java.rmi.RemoteException
	 */
	public void removeTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException;
	
	/**
	 * ajoute un nouveau groupe
	 * @param idGr l'id du nouveau groupe
	 * @throws java.rmi.RemoteException
	 */
	public void ajouterGroupeTchat(Integer idGr) throws java.rmi.RemoteException;
}
