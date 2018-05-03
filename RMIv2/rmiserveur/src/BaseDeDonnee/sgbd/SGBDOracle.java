package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;

import BaseDeDonnee.connexion.Connexion;
import BaseDeDonnee.connexion.ConnexionMySQL;
import BaseDeDonnee.connexion.ConnexionOracle;

public class SGBDOracle extends SGBD {

	public SGBDOracle() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_ORACLE = "ressources/BDOracle.properties";
	
	@Override
	protected Connexion creeSGBD(String utilisateur, String motdepasse) throws RemoteException {
		Connexion conn = null;
		conn = new ConnexionOracle();
		conn.creeConnexion(utilisateur, motdepasse, LINK_SETTING_ORACLE);
		return conn;
	}
}
