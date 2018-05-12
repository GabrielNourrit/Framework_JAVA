package sondage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SondageListener extends Remote {
	public void nouveauVote(String resultat, Integer sondage) throws RemoteException;
	public void nouveauSondage(SondageObj sondage) throws RemoteException;
}
