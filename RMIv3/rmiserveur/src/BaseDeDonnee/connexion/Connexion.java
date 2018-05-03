package BaseDeDonnee.connexion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public abstract class Connexion implements ConnexionInterface{
	
	protected String utilisateur;
	protected String motdepasse;
	protected String link_properties;
	//protected boolean connexionValide;
	
	public Connexion() throws RemoteException {
		super();
	}
	
	/*protected void testConnexionValide() throws Exception {
		if (!connexionValide) throw new Exception("Connexion non verifie");
	}*/
	
	public abstract Connexion creeConnexion(String utilisateur, String motdepasse,String link_properties) throws RemoteException;
	
	public abstract boolean verifierMdp() throws RemoteException, ClassNotFoundException, SQLException;
}
