package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import util.Utilisateur;
import BaseDeDonnee.MethodeServeur;

public interface MelInterface extends Remote, MethodeServeur{

	/**
	 * Retourne la liste de tous les utilisateurs
	 * @return une liste d'utilisateur
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Utilisateur> getAllUsers() throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Methode d'envoie de mail
	 * @param u le login de l'expediteur
	 * @param receveur le login du receveur
	 * @param message le contenu du mail
	 * @param objet l'objet du mail
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void saveMessage(String u, String receveur, String message,String objet) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Charge un message recu
	 * @param chemin chemin du mail
	 * @return le message
	 * @throws RemoteException
	 */
	public String chargerMessage(String chemin) throws RemoteException;
	
	/**
	 * Methode qui charge tous les mails recus
	 * @param receveur le login du receveur
	 * @return une liste de mail
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException;
	
	/**
	 * Methode qui charge tous les mails envoyes
	 * @param expediteur le login de l'expediteur
	 * @return une liste de mail
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMailsExp(String expediteur) throws ClassNotFoundException, RemoteException, SQLException;
	
	/**
	 * Supprimer un mail envoye
	 * @param id l'id du mail a supprimer
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprMailExp(int id) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Supprimer un mail recu
	 * @param id l'id du mail a supprimer
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprMailRec(int id) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * ajoute un listener pour les mails recus
	 * @param listener le listener
	 * @param login le login de l'utilisateur
	 * @throws java.rmi.RemoteException
	 */
	public void addMailRecuListener (MelListener listener, String login) throws java.rmi.RemoteException;
	
	/**
	 * ajoute un listener pour les mails envoyes
	 * @param listener le listener
	 * @param login le login de l'utilisateur
	 * @throws java.rmi.RemoteException
	 */
	public void addMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException;
	
	/**
	 * Supprime un listener pour les mails recus
	 * @param listener le listener
	 * @param login le login de l'utilisateur
	 * @throws java.rmi.RemoteException
	 */
	public void removeMailRecuListener (MelListener listener, String login) throws java.rmi.RemoteException;
	
	/**
	 * supprime un listener pour les mails envoyes
	 * @param listener le listener
	 * @param login le login de l'utilisateur
	 * @throws java.rmi.RemoteException
	 */
	public void removeMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException;
}






