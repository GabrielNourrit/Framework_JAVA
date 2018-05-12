package groupes;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import BaseDeDonnee.sgbd.SGBD;
import util.Groupe;

public class Groupes implements GroupesInterface {

	private SGBD sgbd;
	
	public Groupes(SGBD _sgbd) {
		super();
		sgbd = _sgbd;
	}
	
	@Override
	public List<Groupe> getAllGroupes() throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getGroupes();
	}
	
	public List<Groupe> getGroupeLogin(String login) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.getGroupeUtilisateur(login);
	}

	@Override
	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.suprimerGroupe(idGr);
	}

	@Override
	public int ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException {
		int idGr = sgbd.ajouterGroupe(groupe, lstUser);
		return idGr;
	}

	@Override
	public List<String> getAllLogin(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getAllLoginGroupe(idGr);
	}

	@Override
	public List<String> getAllLoginNotInGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getAllLoginNotInGroupe(idGr);
	}

	@Override
	public void ajouterUtilisateur(int idGr, String login)
			throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.ajouterUtilisateur(idGr, login);
	}

	@Override
	public void supprimerUtilisateur(int idGr, String login)
			throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.supprimerUtilisateur(idGr, login);
		
	}

	

}
