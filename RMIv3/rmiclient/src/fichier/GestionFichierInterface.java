package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;
import java.util.List;

import util.Groupe;

public interface GestionFichierInterface extends Remote {
	
	/**
	 * Upload un fichier
	 * @param nom le nom du fichier a upload
	 * @param contenu le contenu sous forme d'octet
	 * @return vrai si l'upload c est bien passe false sinon
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean upload(String nom, byte[] contenu, String l, int id) throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Download un fichier
	 * @param id id du fichier a download
	 * @return un flux d'octet representant le fichier
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public byte[] download(int id) throws ClassNotFoundException, SQLException, IOException;
	
	/**
	 * Recupere tous les fichiers
	 * @return une liste de fichier
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Fichier> recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException;
	
	/**
	 * Recupere tous les fichiers d'un groupe
	 * @param id id du groupe
	 * @return une liste de fichier
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Fichier> recupererFichierGroupe(int id) throws ClassNotFoundException, RemoteException, SQLException;
	
	/**
	 * Recuperer tous les groupes d'un utilsateur
	 * @param l le login de l'utilisateur
	 * @return une liste de groupe
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Groupe> recupererGroupe(String l) throws ClassNotFoundException, RemoteException, SQLException;
	
	/**
	 * ajouter une groupe au fichier (utile pour les listeners)
	 * @param idGr l'id du groupe
	 * @throws java.rmi.RemoteException
	 */
	public void ajouterGroupeFichier(Integer idGr) throws java.rmi.RemoteException;
	
	/**
	 * Ajouter un listener pour recevoir les notifications
	 * @param listener le listener
	 * @param groupe le groupe associe au listener
	 * @throws java.rmi.RemoteException
	 */
	public void addGroupListener (FichierListener listener, Integer groupe) throws java.rmi.RemoteException;
	
	/**
	 * Supprimer un listener
	 * @param listener le listener
	 * @param groupe l'id du groupe
	 * @throws java.rmi.RemoteException
	 */
	public void removeGroupListener (FichierListener listener, Integer groupe) throws java.rmi.RemoteException;
}
