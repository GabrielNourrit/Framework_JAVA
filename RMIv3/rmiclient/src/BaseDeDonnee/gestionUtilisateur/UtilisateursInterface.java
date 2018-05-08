package BaseDeDonnee.gestionUtilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.MethodeServeur;
import util.Utilisateur;

public interface UtilisateursInterface extends Remote,MethodeServeur {
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException;
	public Utilisateur getUser(String login) throws ClassNotFoundException, RemoteException, SQLException;
}
