package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import util.Utilisateur;

public interface ConnexionInterface extends Remote{
	
	/**
	 * Methode qui verifie si le mot de passe et le login sont correct
	 * @param utilisateur login de l'utilisateur
	 * @param mdp le mot de passe de l'utilisateur
	 * @return vrai si le mot de passe et le login sont correct false sinon 
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Recupere un utilisateur en fonction de son login
	 * @param login le login de l'utilisateur a rechercher
	 * @return un utilisateur
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException;
}
