package mail;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BD.Connexionsgbd;
import connexion.MethodeServeur;

public class Mel extends MethodeServeur implements MelInterface{

	public Mel (String url) throws RemoteException {  
		super(url);
	}

	@Override
	public ArrayList<melCell> melUnUser(int id) {
		    /* Connecte sgbd
		     * Recupere tout les messages
		     * Pour chaque message on instancie objet, date ...
		     * retourne liste
		     * */
		try {
			Connexionsgbd csgbd = new Connexionsgbd();
			Connection conn = csgbd.openConnexionsgbd();
			Statement stmt;
	
			stmt = conn.createStatement();
		
			ResultSet rs = stmt.executeQuery("select contenu from Messages where login='"+ id +"' and type ='mail'");
		
			while (rs.next())
				System.out.println("ok");
		
		
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
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
