package connexion;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import BaseDeDonnee.sgbd.SGBD;

public class Connexion implements ConnexionInterface{
	
	protected String utilisateur;
	protected String motdepasse;
	protected String link_properties;
	private SGBD sgbd;
	
	public Connexion(SGBD sgbd) throws RemoteException {
		super();
		this.sgbd=sgbd;
	}
	
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		int n = 0;
		ResultSet rs = sgbd.executeSelect("select count(*) from utilisateurs where login='"+ utilisateur +"' and motDePasse='" + mdp + "'");
		if (rs.next()) n = rs.getInt(1);
		sgbd.closeReq(rs);
		if(n == 1) return true;
		return false;
	}
}
