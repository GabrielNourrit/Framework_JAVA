package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import util.Droit;
import util.Type;

public interface TypesInterface extends Remote,MethodeServeur {
public interface TypesInterface extends Remote {

	public List<Type> getAllTypes() throws RemoteException, ClassNotFoundException, SQLException;
	
	public void addType(String type) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Droit> getDroit(int type) throws RemoteException, ClassNotFoundException, SQLException;
}