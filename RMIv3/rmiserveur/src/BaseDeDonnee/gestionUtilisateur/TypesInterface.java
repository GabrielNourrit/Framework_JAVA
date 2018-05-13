package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.MethodeServeur;
import util.Droit;
import util.Type;

public interface TypesInterface extends Remote,MethodeServeur {

	/**
	 * Recuperer tous les types
	 * @return une liste de type
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Type> getAllTypes() throws RemoteException, ClassNotFoundException, SQLException;
	
	
	/**
	 * Ajouter un type
	 * @param type le type a ajouter
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addType(String type) throws RemoteException, ClassNotFoundException, SQLException;
	
	
	/**
	 * Retourne la liste de droit d'un type
	 * @param type l'id du type
	 * @return une liste de droit
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Droit> getDroit(int type) throws RemoteException, ClassNotFoundException, SQLException;;
}