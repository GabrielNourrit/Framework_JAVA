package BaseDeDonnee.connexion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public abstract class Connexion extends UnicastRemoteObject implements ConnexionInterface{
	
	protected String utilisateur;
	protected String motdepasse;
	protected String link_properties;
	
	public Connexion() throws RemoteException {
		super();
	}
	
	public abstract Connexion creeConnexion(String utilisateur, String motdepasse,String link_properties) throws RemoteException;
	
	public abstract boolean verifierMdp() throws RemoteException, ClassNotFoundException, SQLException;
}
