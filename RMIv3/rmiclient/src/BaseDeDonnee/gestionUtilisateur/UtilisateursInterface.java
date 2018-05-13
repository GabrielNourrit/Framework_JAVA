package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import util.Utilisateur;

public interface UtilisateursInterface extends Remote {
	
	/**
	 * Retourne tous les utilisateurs existant
	 * @return une liste d'utilisateur
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Retourne un utilisateur
	 * @param login le login de l'utilisateur a rechercher
	 * @return un utilisateur
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public Utilisateur getUser(String login) throws ClassNotFoundException, RemoteException, SQLException;
}
