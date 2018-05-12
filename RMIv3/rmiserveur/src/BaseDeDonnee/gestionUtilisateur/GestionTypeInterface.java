package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.MethodeServeur;
import util.Droit;
import util.Type;

public interface GestionTypeInterface extends Remote,MethodeServeur {
	public List<Type> getAllType() throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Droit> getAllDroit() throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Droit> getAllDroitInType(int idType) throws ClassNotFoundException, RemoteException, SQLException;
	
	public List<Droit> getAllDroitNotInType(int idType) throws ClassNotFoundException, RemoteException, SQLException;
	
	public void addType(String libelleType, List<String> l) throws RemoteException, ClassNotFoundException, SQLException;
	
	public void modifierType(Type type, List<String> l) throws RemoteException, ClassNotFoundException, SQLException;
}
