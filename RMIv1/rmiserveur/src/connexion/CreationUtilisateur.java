package connexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import BD.Connexionsgbd;

public class CreationUtilisateur {
	
	/**
	 * Cr�ation d'un utilisateur avec les deux informations principales et obligatoires (login et mot de passe et type).
	 * @param login
	 * @param mdp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,String type) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into utilisateurs (login,motDePasse,type) values ('"+login+"','"+mdp+"','"+type+"');");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	/**
	 * Cr�ation d'un utilisateur avec les infos principale + nom et pr�nom
	 * @param login
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param type
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,String nom,String prenom,String type) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into utilisateurs (login,nom,prenom,motDePasse,type) values ('"+login+"','"+nom+"','"+prenom+"','"+mdp+"','"+type+"');");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	public void modifMdp(String mdp, String loginUse) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update utilisateurs set MotDePasse ='"+mdp+"' where login ='"+loginUse+"';");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	public void modifNom(String nom, String loginUse) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update utilisateurs set nom ='"+nom+"' where login ='"+loginUse+"';");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	public void modifPrenom(String prenom, String loginUse) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update utilisateurs set prenom ='"+prenom+"' where login ='"+loginUse+"';");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	public void modifType(String type, String loginUse) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update utilisateurs set type ='"+type+"' where login ='"+loginUse+"';");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
	public void supprUse (String user) throws ClassNotFoundException, SQLException {
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from utilisateurs where login = '"+user+"';");
		stmt.close();
		csgbd.closeConnexionsgbd(conn);
	}
	
}
