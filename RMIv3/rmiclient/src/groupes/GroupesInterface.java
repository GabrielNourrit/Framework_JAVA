package groupes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import util.Groupe;

public interface GroupesInterface  extends Remote {
	public List<Groupe> getAllGroupes()  throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<Groupe> getGroupeLogin(String login) throws ClassNotFoundException, RemoteException, SQLException;
	
	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException;
	
	public void ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<String> getAllLogin(int idGr) throws RemoteException, ClassNotFoundException, SQLException;
	
	public List<String> getAllLoginNotInGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException;
	
	public void ajouterUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException;
	
	public void supprimerUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException;
}
