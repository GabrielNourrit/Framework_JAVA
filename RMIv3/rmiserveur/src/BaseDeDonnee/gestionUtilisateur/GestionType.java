package BaseDeDonnee.gestionUtilisateur;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.sgbd.SGBD;
import util.Droit;
import util.Type;

public class GestionType implements GestionTypeInterface {

	private SGBD sgbd;
	
	public GestionType (SGBD sgbd) throws RemoteException {  
		super();
		this.sgbd=sgbd;
	}
	
	public List<Type> getAllType() throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getAllType();
	}
	
	public List<Droit> getAllDroitInType(int idType) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.getAllDroitInType(idType);
	}
	
	public List<Droit> getAllDroitNotInType(int idType) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.getAllDroitNotInType(idType);
	}
	
	public void addType(String libelleType, List<String> l) throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.ajouterType(libelleType, l);
	}
	
	public void modifierType(Type type, List<String> l) throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.modifierType(type, l);
	}
	
	public List<Droit> getAllDroit() throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.getAllDroit();
	}
}
