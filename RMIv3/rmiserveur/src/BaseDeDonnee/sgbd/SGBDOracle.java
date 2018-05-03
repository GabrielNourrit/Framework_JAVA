package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDonnee.connexion.ConnexionBase;
import BaseDeDonnee.connexion.ConnexionOracle;


public class SGBDOracle extends SGBD {
	
	public SGBDOracle() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_ORACLE = "ressources/BDOracle.properties";
	
	@Override
	protected ConnexionBase creeSGBD() throws RemoteException {
		return new ConnexionOracle(LINK_SETTING_ORACLE);
	}

	
	@Override
	public ResultSet executeSelect(String requete) throws SQLException, ClassNotFoundException, RemoteException {
		ResultSet rs = null;
		try {
			conn = new ConnexionOracle(LINK_SETTING_ORACLE).prepare();
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
