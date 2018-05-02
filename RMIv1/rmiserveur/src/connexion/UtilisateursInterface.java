package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface UtilisateursInterface extends Remote {
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException;
}
