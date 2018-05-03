package connexion;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import BD.Connexionsgbd;

public class Connexion extends MethodeServeur implements ConnexionInterface {

	public Connexion (String url) throws RemoteException {  
		super(url);
	}
	
	public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		String mdpCrypt="";
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select motDePasse from utilisateurs where login='"+ login +"'");
		if (rs.next()) mdpCrypt = rs.getString(1);
		//System.out.println(n);
		csgbd.closeConnexionsgbd(conn);
		if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		return false;
	}
}
