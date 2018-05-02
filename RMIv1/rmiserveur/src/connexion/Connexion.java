package connexion;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Connexionsgbd;

public class Connexion extends MethodeServeur implements ConnexionInterface {

	public Connexion (String url) throws RemoteException {  
		super(url);
	}
	
	public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		int n = 0;
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from utilisateurs where login='"+ login +"' and motDePasse='" + mdp + "'");
		if (rs.next()) n = rs.getInt(1);
		System.out.println(n);
		rs.close();
        stmt.close();
		csgbd.closeConnexionsgbd(conn);
		if(n == 1) return true;
		return false;
	}
}
