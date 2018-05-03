package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface OperationUtilisateurInterface extends Remote {
	public boolean ModifierUtilisateur(Utilisateur uOld, Utilisateur uNew) throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean SupprimerUtilisateur(String login) throws RemoteException, ClassNotFoundException, SQLException;
	
	public boolean AjouterUtilisateur(Utilisateur utilisateur) throws RemoteException, ClassNotFoundException, SQLException;
}
