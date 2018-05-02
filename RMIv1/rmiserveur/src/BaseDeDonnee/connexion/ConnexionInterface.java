package BaseDeDonnee.connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;

public interface ConnexionInterface extends Remote{
	public boolean verifierMdp() throws RemoteException, ClassNotFoundException, SQLException;
}
