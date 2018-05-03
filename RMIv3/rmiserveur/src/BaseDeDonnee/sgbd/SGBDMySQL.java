package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDonnee.connexion.ConnexionBase;
import BaseDeDonnee.connexion.ConnexionMySQL;


public class SGBDMySQL extends SGBD {
	
	public SGBDMySQL() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_MYSQL = "ressources/BDMySQL.properties";

	@Override
	protected ConnexionBase creeSGBD() throws RemoteException {
		return new ConnexionMySQL(LINK_SETTING_MYSQL);
	}

	@Override
	public ResultSet executeSelect(String requete) throws SQLException, ClassNotFoundException, RemoteException {
		ResultSet rs = null;
		try {
			conn = new ConnexionMySQL(LINK_SETTING_MYSQL).prepare();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(requete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void closeReq(ResultSet rs) throws SQLException {
		conn.close();
		stmt.close();
		rs.close();
	}
}
