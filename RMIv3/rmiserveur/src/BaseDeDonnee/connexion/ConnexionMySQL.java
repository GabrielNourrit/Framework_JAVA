package BaseDeDonnee.connexion;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;

import BaseDeDonnee.bd.Connexionsgbd;


public class ConnexionMySQL extends ConnexionBase {

	public ConnexionMySQL( String link_properties) throws RemoteException {
		super(link_properties);
	}

	public Connection prepare() throws SQLException, ClassNotFoundException {
		Connexionsgbd csgbd = new Connexionsgbd(link_properties);
		Connection conn = csgbd.openConnexionsgbd();
		return conn;
	}

}
