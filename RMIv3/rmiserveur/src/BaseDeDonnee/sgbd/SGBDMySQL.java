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
import mail.MelCell;
import sondage.SondageObj;
import util.Droit;
import util.Groupe;
import util.Type;
import util.Utilisateur;


public class SGBDMySQL extends SGBD {
	
	private static int i = -1;
	
	public SGBDMySQL() throws RemoteException, ClassNotFoundException, SQLException {
		super();
		
	}

	protected static final String LINK_SETTING_MYSQL = "ressources/bdd/BDMySQL.properties";

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
		conn.close();
		return r;
	}

	public void closeReq(ResultSet rs) throws SQLException {
		conn.close();
		stmt.close();
		rs.close();
	}
	
	public boolean verifierMdp(String login, String mdp) throws SQLException, ClassNotFoundException, RemoteException {
		String mdpCrypt="";
		ResultSet rs = executeSelect("select motDePasse from utilisateurs where login='"+ login +"' and etat='VALID'");
		if (rs.next()) mdpCrypt = rs.getString(1);
		closeReq(rs);
		if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		return false;
	}
	
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		ResultSet rs = executeSelect("select login, nom, prenom, idtype, libelle from utilisateurs natural join types where login='"+ login +"'");
		if (rs.next()) {
			Utilisateur user =  new Utilisateur(login,rs.getString(2),rs.getString(3),new Type(rs.getInt(4),rs.getString(5)));
			return user;
		}
		closeReq(rs);
		return null;
	}
	
	public void creaUse (String login,String mdp,Type type) throws ClassNotFoundException, SQLException {
		executeUpdate("insert into utilisateurs (login,motDePasse,idType,etat) values ('"+login+"','"+mdp+"','"+type.getIdType()+"','VALID')");
	}
	
	public void creaUse (String login,String mdp,String nom,String prenom,Type type) throws ClassNotFoundException, SQLException {	
		executeUpdate("insert into utilisateurs (login,nom,prenom,motDePasse,idType,etat) values ('"+login+"','"+nom+"','"+prenom+"','"+mdp+"','"+type.getIdType()+"','VALID')");
		executeUpdate("insert into faitPartieGroupe (login, idGr, dateEntree) values ("+login+",1, '12-SEP-2017');");
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
	
	public synchronized int ajouterMail(String path, String expediteur, String receveur, String objet) throws ClassNotFoundException, SQLException, RemoteException {
		if (i==-1) i = getNextvalMail(); 
		executeUpdate("insert into Mails(idMai,dateArrive,url,etat,loginExpediteur,loginReceveur,objet) values (mails_id.nextval,sysdate,'"+path+"','VAL','"+expediteur+"','"+receveur+"','"+objet +"')");
		return i++;
	}
	
	public void supprMail(int id) throws ClassNotFoundException, SQLException {
		executeUpdate("delete from mails where idmai="+id);
	}
	
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		List<Utilisateur> lesUser = new ArrayList<>();
		ResultSet rs = executeSelect("select login, nom, prenom,dateNaissance, description, idType, libelle from utilisateurs natural join Types where etat ='VALID'");
		while (rs.next()) {
			Utilisateur user = new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3),new Type(rs.getInt(5),rs.getString(6)));
			lesUser.add(user);
		}
		closeReq(rs);
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
		closeReq(rs);
		return groupes;
	}
	
	public String getLibelleGroup(int id) throws ClassNotFoundException, RemoteException, SQLException {
		String libelle="";
		ResultSet rs = executeSelect("select libelle from groupe where idgr="+id);
		if (rs.next()) libelle = rs.getString(1);
		closeReq(rs);
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
		closeReq(rs);
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
		closeReq(rs);
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
		closeReq(rs);
		return f;
	}
	
	public int getNextvalMail() throws ClassNotFoundException, RemoteException, SQLException {
		int i = -1;
		ResultSet rs = executeSelect("select max(idmai) from mails");
		if (rs.next()) i = rs.getInt("idmai");
		closeReq(rs);
		return i;
	}
	
	public List<Groupe> getGroupes()  throws RemoteException, ClassNotFoundException, SQLException {
		List<Groupe> lesGroupes = new ArrayList<>();
		ResultSet rs = executeSelect("select * from groupes");
		while (rs.next()) {
			Groupe g = new Groupe(rs.getInt(1), rs.getString(2));
			lesGroupes.add(g);
		}
		closeReq(rs);
		return lesGroupes;
	}
	
	public int getNextvalGroupe() throws ClassNotFoundException, RemoteException, SQLException {
		int i = -1;
		ResultSet rs = executeSelect("select max(idGr) from groupess");
		if (rs.next()) i = rs.getInt(1);
		closeReq(rs);
		return i;
	}
	
	public int ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException { 
		int idGr = getNextvalGroupe();
		executeUpdate("insert into groupes (idGr, libelle) values (groupes_id.NEXTVAL ,'"+groupe+"')");
		
		if (lstUser != null ) {
			for (String u: lstUser) {
				executeUpdate("insert into faitPartieGroupe (login, idGr, dateEntree) VALUES ('"+u+"',"+idGr+",SYSDATE)");
			}
		
		}
		return i;
	}
	
	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("delete from groupe where idGr="+idGr);
	}
	
	public List<String> getAllLoginGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		List<String> utilisateurs = new ArrayList<>();
		ResultSet rs = executeSelect("select login from faitPartieGroupe where idGr ="+idGr);
		while (rs.next()) {
			String login = rs.getString(1);
			utilisateurs.add(login);
		}
		closeReq(rs);
		return utilisateurs;
	}
	
	public List<String> getAllLoginNotInGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		List<String> utilisateurs = new ArrayList<>();
		ResultSet rs = executeSelect("select login from utilisateurs where etat ='VALID' minus select login from faitPartieGroupe where idGr ="+idGr);
		while (rs.next()) {
			String login = rs.getString(1);
			utilisateurs.add(login);
		}
		closeReq(rs);
		return utilisateurs;
	}
	
	public void ajouterUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("insert into faitPartieGroupe (login, idGr, dateEntree) VALUES ('"+login+"',"+idGr+",SYSDATE)");
	}
	
	public void supprimerUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("delete from faitPartieGroupe where idGr="+idGr +" and login ='"+login+"'");
	}
	
	public List<Type> getAllType()  throws RemoteException, ClassNotFoundException, SQLException { 
		List<Type> types = new ArrayList<>();
		ResultSet rs = executeSelect("select * from types");
		while (rs.next()) {
			Type type = new Type(rs.getInt(1), rs.getString(2));
			types.add(type);
		}
		closeReq(rs);
		return types; 
	}
	
	public void ajouterType(String type) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("insert into types (idType, libelle) values (types_id.NEXTVAL,'"+ type+"')");
	}
	
	public List<MelCell> chargerMails(String rec) throws ClassNotFoundException, RemoteException, SQLException {
		List<MelCell> fs = new ArrayList<>();
		MelCell m = null;
		ResultSet rs = executeSelect("select idMai,dateArrive,loginExpediteur,objet from mails where loginReceveur='"+rec+"' and etat='VAL'");
		while (rs.next()) {
			m = new MelCell(rs.getInt(1),rs.getDate(2).toString(),rs.getString(3),rs.getString(4));
			fs.add(m);
		}
		closeReq(rs);
		return fs;
	}
	
	public String etatMail(int id) throws ClassNotFoundException, RemoteException, SQLException {
		String etat="";
		ResultSet rs = executeSelect("select etat from mails where idmai="+id);
		if (rs.next()) etat=rs.getString(1);
		closeReq(rs);
		return etat;
	}
	
	public void modifEtatMail(int id, String newEtat) throws ClassNotFoundException, SQLException {
		executeUpdate("update mails set etat='"+newEtat+"' where idmai="+id);
	}
	public List<Droit> getDroits(int type) throws ClassNotFoundException, RemoteException, SQLException {
		List<Droit> droits = new ArrayList<>();
		ResultSet rs = executeSelect("select idD, libelle from droits natural join possede where idType ="+type);
		while(rs.next()) {
			Droit d = new Droit(rs.getString(1), rs.getString(2));
			droits.add(d);
		}
		return droits; 
	}
	
	public int ajouterSondage(String owner, String question, String reponses,int multiple, String date) throws RemoteException, ClassNotFoundException, SQLException{
		executeUpdate("insert into sondage(libelle,dateDebut,dateFin,resultat,login,multiple,fait,total) values ('"+question+"',SYSDATE,STR_TO_DATE('"+date+"','%y-%d-%Y'),'"+reponses+"','"+owner+"',"+multiple+",0,0)");
		return 0;
	}
	

	public List<SondageObj> getSondage(Utilisateur owner, int fait) {
		List<SondageObj> sf = new ArrayList<SondageObj>();
		try {
			ResultSet rs = null;
			if(fait==1) {
			   rs = executeSelect("select * from sondage where idSon in (select distinct idSon from vote)");
			}else {
			   rs = executeSelect("select * from sondage where idSon not in (select distinct idSon from vote)");
			}
			while (rs.next()) {
				boolean multiple;
				System.out.println("CC");
				if (rs.getInt("multiple")==1) {multiple = true;} else multiple=false; 
				sf.add(new SondageObj(rs.getInt("idSon"), rs.getString("login"), rs.getString("libelle"), rs.getString("resultat"), multiple, rs.getDate("dateFin").toString(), rs.getInt("total")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sf;
		}
	
	
	
	public void modifierVotes(String actor, int id, String resultat) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("update sondage set resultat ='"+resultat+"' where idSon="+id);
		executeUpdate("insert into vote values ("+id+",'"+actor+"',SYSDATE)");
	}
}
