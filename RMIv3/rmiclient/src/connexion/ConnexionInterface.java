package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;
import util.Utilisateur;

public interface ConnexionInterface extends Remote,MethodeServeur{
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException;
}
