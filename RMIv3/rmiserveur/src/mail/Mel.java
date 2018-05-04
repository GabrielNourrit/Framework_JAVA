package mail;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import BaseDeDonnee.MethodeServeur;

public class Mel implements MethodeServeur, MelInterface{

	public Mel (String url) throws RemoteException {  
	}


	@Override
	public void test() throws RemoteException{
		
		System.out.println("on est en finale !!");
		
	}
	
	
	/*public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		int n = 0;
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from utilisateurs where login='"+ login +"' and motDePasse='" + mdp + "'");
		if (rs.next()) n = rs.getInt(1);
		System.out.println(n);
		csgbd.closeConnexionsgbd(conn);
		if(n == 1) return true;
		return false;
	}*/
}
