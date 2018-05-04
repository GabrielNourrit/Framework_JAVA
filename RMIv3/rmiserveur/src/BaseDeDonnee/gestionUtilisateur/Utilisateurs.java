package BaseDeDonnee.gestionUtilisateur;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.sgbd.SGBD;
import util.Utilisateur;


public class Utilisateurs implements UtilisateursInterface {
	
	private SGBD sgbd;
	
	public Utilisateurs(SGBD _sgbd) throws RemoteException {
		super();
		sgbd = _sgbd;
	}

	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		
		return sgbd.getUsers();
	}
	
	

}
