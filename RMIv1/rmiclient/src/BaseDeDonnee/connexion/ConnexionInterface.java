package BaseDeDonnee.connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ConnexionInterface extends Remote{
	public boolean verifierMdp() throws RemoteException, ClassNotFoundException, SQLException;
}
