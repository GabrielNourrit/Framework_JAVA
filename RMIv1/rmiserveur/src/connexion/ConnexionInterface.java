package connexion;


import java.rmi.*;
import java.sql.SQLException;

public interface ConnexionInterface extends Remote {
	public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
}
