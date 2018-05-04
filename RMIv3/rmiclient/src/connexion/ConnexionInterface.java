package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import fichier.Fichier;

public interface ConnexionInterface extends Remote{
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
}
