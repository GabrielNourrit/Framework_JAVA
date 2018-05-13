package connexion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import BaseDeDonnee.sgbd.SGBD;
import util.Utilisateur;

public class Connexion extends UnicastRemoteObject implements ConnexionInterface{
	
	protected String utilisateur;
	protected String motdepasse;
	protected String link_properties;
	private SGBD sgbd;
	
	public Connexion(SGBD sgbd) throws RemoteException {
		super();
		this.sgbd=sgbd;
	}
	
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		 return sgbd.verifierMdp(utilisateur, mdp);
	}
	
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.getUse(login);
	}
}
