package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;

import BaseDeDonnee.connexion.Connexion;
import BaseDeDonnee.connexion.ConnexionMySQL;

public class SGBDMySQL extends SGBD {

	public SGBDMySQL() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_MYSQL = "ressources/BDMySQL.properties";
	
	@Override
	protected Connexion creeSGBD(String utilisateur, String motdepasse) throws RemoteException {
		Connexion conn = null;
		conn = new ConnexionMySQL();
		conn.creeConnexion(utilisateur, motdepasse, LINK_SETTING_MYSQL);
		return conn;
	}

}
