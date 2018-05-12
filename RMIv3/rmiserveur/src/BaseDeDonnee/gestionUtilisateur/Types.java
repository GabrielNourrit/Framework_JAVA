package BaseDeDonnee.gestionUtilisateur;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.sgbd.SGBD;
import util.Droit;
import util.Type;

public class Types implements TypesInterface {

	private SGBD sgbd;
	
	public Types(SGBD _sgbd) throws RemoteException {
		super();
		sgbd = _sgbd;
	}
	
	@Override
	public List<Type> getAllTypes() throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getAllType();
	}

	@Override
	public void addType(String type) throws RemoteException, ClassNotFoundException, SQLException {
		//sgbd.ajouterType(type);
	}

	@Override
	public List<Droit> getDroit(int type) throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getDroits(type);
	}

}
