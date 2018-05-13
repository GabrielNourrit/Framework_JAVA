package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;
import util.Utilisateur;

public interface OperationUtilisateurInterface extends Remote,MethodeServeur {
	/**
	 * Modifie un utilisateur
	 * @param uOld l'ancien utilisateur
	 * @param uNew le nouvel utilisateur
	 * @return vrai si la modification a marche false sinon
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean ModifierUtilisateur(Utilisateur uOld, Utilisateur uNew) throws RemoteException, ClassNotFoundException, SQLException;
	
	
	/**
	 * Supprime un utilisateur
	 * @param login le login de l'utilisateur a supprimer
	 * @return vrai si la suppression a marche false sinon
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean SupprimerUtilisateur(String login) throws RemoteException, ClassNotFoundException, SQLException;
	
	
	/**
	 * Ajoute un utilisateur
	 * @param utilisateur l'utilisateur a ajoute
	 * @return vrai si l'ajout a marche false sinon
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean AjouterUtilisateur(Utilisateur utilisateur) throws RemoteException, ClassNotFoundException, SQLException;
	
	
	/**
	 * Modifie le mot de passe d'un utilisateur
	 * @param login le login de l'utilisateur
	 * @param mdp le nouveau mot de passe
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void ModifierMdpUtilisateur(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
}
