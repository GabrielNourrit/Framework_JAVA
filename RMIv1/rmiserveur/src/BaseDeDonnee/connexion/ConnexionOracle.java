package BaseDeDonnee.connexion;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BaseDeDonnee.bd.Connexionsgbd;

public class ConnexionOracle extends Connexion {

	

	public ConnexionOracle() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean verifierMdp() throws RemoteException, ClassNotFoundException, SQLException {	
		int n = 0;
		Connexionsgbd csgbd = new Connexionsgbd(this.link_properties);
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from utilisateurs where login='"+ this.utilisateur +"' and motDePasse='" + this.motdepasse + "'");
		if (rs.next()) n = rs.getInt(1);
		System.out.println(n);
		csgbd.closeConnexionsgbd(conn);
		if(n == 1) return true;
		return false;
	}

	@Override
	public Connexion creeConnexion(String utilisateur, String motdepasse, String link_properties) {
		this.utilisateur = utilisateur;
		this.motdepasse = motdepasse;
		this.link_properties = link_properties;
		return this;
	}
	
}
