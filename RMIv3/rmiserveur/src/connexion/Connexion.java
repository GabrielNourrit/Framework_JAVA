package connexion;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDonnee.sgbd.SGBD;

public class Connexion implements ConnexionInterface{
	
	protected String utilisateur;
	protected String motdepasse;
	protected String link_properties;
	private SGBD sgbd;
	
	public Connexion(SGBD sgbd) throws RemoteException {
		super();
		this.sgbd=sgbd;
	}
	
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		 return sgbd.verifierMdp("dzq", "dzq");
	}
}
