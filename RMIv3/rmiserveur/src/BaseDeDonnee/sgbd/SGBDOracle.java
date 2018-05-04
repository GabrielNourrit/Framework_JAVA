package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import BaseDeDonnee.bd.Connexionsgbd;
import BaseDeDonnee.connexion.ConnexionBase;
import BaseDeDonnee.connexion.ConnexionOracle;
import util.Utilisateur;


public class SGBDOracle extends SGBD {
	
	public SGBDOracle() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_ORACLE = "ressources/BDOracle.properties";
	
	@Override
	protected ConnexionBase creeSGBD() throws RemoteException {
		return new ConnexionOracle(LINK_SETTING_ORACLE);
	}

	
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
	
	public int executeUpdate(String requete) throws SQLException, ClassNotFoundException {
		Connexionsgbd csgbd = new Connexionsgbd(LINK_SETTING_ORACLE);
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int r = stmt.executeUpdate(requete);
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
		return r;
	}


	public void closeReq(ResultSet rs) throws SQLException {
		conn.close();
		stmt.close();
		rs.close();
	}
	
	
	public boolean verifierMdp(String login, String mdp) throws SQLException, ClassNotFoundException, RemoteException {
		String mdpCrypt="";
		ResultSet rs = executeSelect("select motDePasse from utilisateurs where login='"+ login +"'");
		if (rs.next()) mdpCrypt = rs.getString(1);
		if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		return false;
	}
	
	public void creaUse (String login,String mdp,String type) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into utilisateurs (login,motDePasse,type,etat) values ('"+login+"','"+mdp+"','"+type+"','VALID')");
	}
	
	public void creaUse (String login,String mdp,String nom,String prenom,String type) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into utilisateurs (login,nom,prenom,motDePasse,type,etat) values ('"+login+"','"+nom+"','"+prenom+"','"+mdp+"','"+type+"','VALID')");
	}
	
	public void modifAttriUser(String loginUse, String attribut,String valeur) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set "+attribut+" = '"+valeur+"' where login ='"+loginUse+"'");
	}
	public void modifMdp(String mdp, String loginUse) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set MotDePasse ='"+mdp+"' where login ='"+loginUse+"'");
	}
	
	public void modifNom(String nom, String loginUse) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set nom ='"+nom+"' where login ='"+loginUse+"'");
	}
	
	public void modifPrenom(String prenom, String loginUse) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set prenom ='"+prenom+"' where login ='"+loginUse+"'");
	}
	
	public void modifType(String type, String loginUse) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set type ='"+type+"' where login ='"+loginUse+"'");
	}
	
	public void modifDescri(String description, String loginUse) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set description ='"+description+"' where login ='"+loginUse+"'");
	}
	
	public void supprUse (String user) throws ClassNotFoundException, SQLException {
		executeUpdate("delete from utilisateurs where login = '"+user+"'");
	}
	
	public void supprUseEtat (String user) throws ClassNotFoundException, SQLException {
		executeUpdate("update utilisateurs set etat ='SUPPR' where login ='"+user+"'");
	}
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		List<Utilisateur> lesUser = new ArrayList<>();
		ResultSet rs = executeSelect("select * from utilisateurs where etat ='VALID'");
		while (rs.next()) {
			Utilisateur user = new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(5));
			lesUser.add(user);
		}
		return lesUser;
	}
}
