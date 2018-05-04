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
import BaseDeDonnee.connexion.ConnexionMySQL;
import fichier.Fichier;
import util.Utilisateur;


public class SGBDMySQL extends SGBD {
	
	public SGBDMySQL() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_MYSQL = "ressources/BDMySQL.properties";

	@Override
	protected ConnexionBase creeSGBD() throws RemoteException {
		return new ConnexionMySQL(LINK_SETTING_MYSQL);
	}

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
	
	public int executeUpdate(String requete) throws SQLException, ClassNotFoundException {
		Connexionsgbd csgbd = new Connexionsgbd(LINK_SETTING_MYSQL);
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
		rs.close();
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
	
	public void ajouterFichier(String n,String l) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (fichiers_id.nextval,'"+n+"',sysdate,'ressources','"+l+"',1)");
	}
	
	public void ajouterMail(String path, String expediteur, String receveur) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into Mails(idMai,dateArrive,url,etat,loginExpediteur,loginReceveur) values (mails_id.nextval,sysdate,'"+path+"','VAL','"+expediteur+"','"+receveur+"')");
	}
	
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		List<Utilisateur> lesUser = new ArrayList<>();
		ResultSet rs = executeSelect("select * from utilisateurs where etat ='VALID'");
		while (rs.next()) {
			Utilisateur user = new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(5));
			lesUser.add(user);
		}
		rs.close();
		return lesUser;
	}
	
	public List<Fichier> getFichiers() throws ClassNotFoundException, RemoteException, SQLException {
		List<Fichier> fs = new ArrayList<>();
		Fichier f = null;
		int i;
		String n, u;
		ResultSet rs = executeSelect("select idFic,nom,url from fichiers");
		while (rs.next()) {
			i = rs.getInt("idFic");
			n = rs.getString("nom");
			u = rs.getString("url");
			System.out.println(n);
			f = new Fichier(i,n,u);
			fs.add(f);
		}
		rs.close();
		return fs;
	}
	
	public Fichier getUrlFichier(int id) throws ClassNotFoundException, RemoteException, SQLException {
		String url, nom;
		Fichier f = null;
		ResultSet rs = executeSelect("select nom,url from fichiers where idFic="+id);
		if (rs.next()) {
			nom = rs.getString("nom");
			url = rs.getString("url");
			f = new Fichier(id,nom,url);
		}
		rs.close();
		return f;
	}
	
	public int getNextvalMail() throws ClassNotFoundException, RemoteException, SQLException {
		int i = -1;
		ResultSet rs = executeSelect("select max(idmai) from mails");
		if (rs.next()) i = rs.getInt("idmai")+1;
		return i;
	}
}
