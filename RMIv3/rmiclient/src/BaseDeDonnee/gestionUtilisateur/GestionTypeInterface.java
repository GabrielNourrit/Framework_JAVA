package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import util.Droit;
import util.Type;

public interface GestionTypeInterface extends Remote {
	
	/**
	 * Retourne une liste de tous les types
	 * @return une liste de type
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Type> getAllType() throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * Retourne tous les droits d'un type
	 * @param idType l'id du type
	 * @return une liste de droit
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Droit> getAllDroitInType(int idType) throws ClassNotFoundException, RemoteException, SQLException;

	/**
	 * Retourne tous les droits qu'un type ne possede pas
	 * @param idType l'id du type
	 * @return une liste de droit
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Droit> getAllDroitNotInType(int idType) throws ClassNotFoundException, RemoteException, SQLException;

	/**
	 * Retourne tous les droits possibles
	 * @return une liste de droit
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Droit> getAllDroit() throws ClassNotFoundException, RemoteException, SQLException;

	/**
	 * Ajoute un type
	 * @param libelleType le libelle du type
	 * @param l la liste de droit associee au type
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addType(String libelleType, List<String> l) throws RemoteException, ClassNotFoundException, SQLException;

	/**
	 * Modifie un type
	 * @param type le type a modifier
	 * @param l la nouvelle liste de droit associee
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifierType(Type type, List<String> l) throws ClassNotFoundException, SQLException, RemoteException;
}
