package fichier;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FichierListener extends Remote{
	
	/**
	 * Methode de notification d'un nouveau fichier ajoute
	 * @param f le nouveau fichier ajoute
	 * @param g l'id du groupe ou a ete ajoute le fichier
	 * @throws RemoteException
	 */
	public void nouveauFichier(Fichier f, Integer g) throws RemoteException;
}
