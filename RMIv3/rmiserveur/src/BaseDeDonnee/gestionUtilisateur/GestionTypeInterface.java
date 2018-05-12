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
	
	public List<Droit> getAllDroitType(int idType) throws ClassNotFoundException, RemoteException, SQLException;
	
	public void addType(String libelleType, List<Droit> l) throws RemoteException, ClassNotFoundException, SQLException;
	
	public void modifierType(Type type, List<Droit> l) throws ClassNotFoundException, SQLException;
}
