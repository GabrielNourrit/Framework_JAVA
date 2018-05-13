package sondage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SondageListener extends Remote {
	/**
	 * Notification d'un nouveau vote dans un sondage
	 * @param resultat le nouveau resultat
	 * @param sondage l'id du sondage
	 * @throws RemoteException
	 */
	public void nouveauVote(String resultat, Integer sondage) throws RemoteException;
	
	/**
	 * Notification d'un nouveau sondage
	 * @param sondage le nouveau sondage
	 * @throws RemoteException
	 */
	public void nouveauSondage(SondageObj sondage) throws RemoteException;
}
