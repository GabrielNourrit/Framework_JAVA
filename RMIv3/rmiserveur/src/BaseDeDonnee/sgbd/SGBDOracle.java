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
import fichier.Fichier;
import fichier.Groupe;
import mail.MelCell;
import util.Utilisateur;


public class SGBDOracle extends SGBD {
	
	public SGBDOracle() throws RemoteException {
		super();
	}

	private static final String LINK_SETTING_ORACLE = "ressources/bdd/BDOracle.properties";
	
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
		if (rs.next()) {
			mdpCrypt = rs.getString(1);
			if (mdp.equals(mdpCrypt)) return true;
		}
		rs.close();
		//if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		return false;
	}
	
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		ResultSet rs = executeSelect("select login, nom, prenom, idtype from utilisateurs where login='"+ login +"'");
		if (rs.next()) {
			List<Groupe> l = getGroupeUtilisateur(login);
			return new Utilisateur(login,rs.getString(2),rs.getString(3),rs.getString(4),l);
		}
		return null;
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
	
	public void ajouterFichier(String n,String l,int id) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (fichiers_id.nextval,'"+n+"',sysdate,'ressources','"+l+"',"+id+")");
	}
	
	public synchronized int ajouterMail(String path, String expediteur, String receveur) throws ClassNotFoundException, SQLException, RemoteException {
		int i = -1;
		ResultSet rs = executeSelect("select max(idmai) from mails");
		if (rs.next()) i = rs.getInt("idmai")+1;
		return i;
	public void ajouterMail(String path, String expediteur, String receveur, String objet) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into Mails(idMai,dateArrive,url,etat,loginExpediteur,loginReceveur,objet) values (mails_id.nextval,sysdate,'"+path+"','VAL','"+expediteur+"','"+receveur+"','"+objet +"')");
	}
	
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		List<Utilisateur> lesUser = new ArrayList<>();
		String login;
		ResultSet rs = executeSelect("select * from utilisateurs where etat ='VALID'");
		while (rs.next()) {
			login = rs.getString(1);
			Utilisateur user = new Utilisateur(login, rs.getString(2),rs.getString(3),rs.getString(5), getGroupeUtilisateur(login));
			lesUser.add(user);
		}
		rs.close();
		return lesUser;
	}
	
	public List<Groupe> getGroupeUtilisateur(String l) throws ClassNotFoundException, RemoteException, SQLException {
		List<Groupe> groupes = new ArrayList<>();
		int id;
		ResultSet rs = executeSelect("select idgr from faitpartiegroupe where login='"+l+"'");
		while (rs.next()) {
			id = rs.getInt(1);
			Groupe g = new Groupe(id, getLibelleGroup(id));
			groupes.add(g);
		}
		rs.close();
		return groupes;
	}
	
	public String getLibelleGroup(int id) throws ClassNotFoundException, RemoteException, SQLException {
		String libelle="";
		ResultSet rs = executeSelect("select libelle from groupes where idgr="+id);

		if (rs.next()) libelle = rs.getString(1);
		rs.close();
		return libelle;
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
			f = new Fichier(i,n,u);
			fs.add(f);
		}
		rs.close();
		return fs;
	}
	
	public List<Fichier> getFichiersGroupe(int id) throws ClassNotFoundException, RemoteException, SQLException {
		List<Fichier> fs = new ArrayList<>();
		Fichier f = null;
		int i;
		String n, u;
		ResultSet rs = executeSelect("select idFic,nom,url from fichiers where idReceveur="+id);
		while (rs.next()) {
			i = rs.getInt("idFic");
			n = rs.getString("nom");
			u = rs.getString("url");
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
	
	public List<MelCell> chargerMails(String rec) throws ClassNotFoundException, RemoteException, SQLException {
		List<MelCell> fs = new ArrayList<>();
		MelCell m = null;
		ResultSet rs = executeSelect("select idMai,dateArrive,url,loginExpediteur,objet from mails where loginReceveur='"+rec+"' and etat='VAL'");
		while (rs.next()) {
			m = new MelCell(rs.getInt(1),rs.getDate(2).toString(),rs.getString(3),rs.getString(4),rs.getString(5));
			fs.add(m);
		}
		rs.close();
		return fs;
	}
	
	/*public Fichier getFichier(int idFic) {
		int i=-1;
		ResultSet rs = executeSelect("select ");
		if (rs.next()) i = rs.getInt("idMes");
		Fichier f = new Fichier(i,nom,"");
		return f;
	}*/
}
