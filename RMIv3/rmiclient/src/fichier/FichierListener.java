package fichier;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FichierListener extends Remote{
	public void nouveauFichier(Fichier f, Integer g) throws RemoteException;
}
