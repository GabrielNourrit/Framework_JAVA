package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import BaseDeDonnee.bd.Connexionsgbd;
import BaseDeDonnee.connexion.ConnexionBase;
import BaseDeDonnee.connexion.ConnexionOracle;
import fichier.Fichier;
import mail.MelCell;
import sondage.SondageObj;
import util.Droit;
import util.Groupe;
import util.Type;
import util.Utilisateur;


public class SGBDOracle extends SGBD {

	private int j = -1;
	private int t = -1;
	private int idGr;
	private int f;
	
	public SGBDOracle() throws RemoteException {
		super();
		i = -1;
		idGr= -1;
		f = -1;
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
		ResultSet rs = executeSelect("select motDePasse from utilisateurs where login='"+ login +"'");
		if (rs.next()) {
			mdpCrypt = rs.getString(1);
			if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		}
		closeReq(rs);
		//if(BCrypt.checkpw(mdp, mdpCrypt)) return true;
		return false;
	}

	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		ResultSet rs = executeSelect("select login, nom, prenom, idType, libelle from utilisateurs natural join types where login='"+ login +"'");
		if (rs.next()) {
			List<Groupe> l = getGroupeUtilisateur(login);
			Utilisateur user =  new Utilisateur(login,rs.getString(2),rs.getString(3),new Type(rs.getInt(4),rs.getString(5)));
			user.setGroup(l);
			user.setDroits(getDroits(rs.getInt(4)));
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
		executeUpdate("insert into faitPartieGroupe (login, idGr, dateEntree) values ('"+login+"',1, SYSDATE)");
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

	public int ajouterFichier(String n,String l,int id) throws ClassNotFoundException, SQLException, RemoteException {
		if (f==-1) f = getNextvalFichier();
		f++;
		executeUpdate("insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (fichiers_id.nextval,'"+n+"',sysdate,'ressources/stockage/fichier','"+l+"',"+id+")");
		return f;
	}

	public synchronized int ajouterMail(String path, String expediteur, String receveur, String objet) throws ClassNotFoundException, SQLException, RemoteException {
		if (i==-1) i = getNextvalMail();
		i++;
		executeUpdate("insert into Mails(idMai,dateArrive,url,etat,loginExpediteur,loginReceveur,objet) values (mails_id.nextval,sysdate,'"+path+"','VAL','"+expediteur+"','"+receveur+"','"+objet +"')");
		return i;
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
		ResultSet rs = executeSelect("select libelle from groupes where idgr="+id);

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
		int i = 0;
		ResultSet rs = executeSelect("select max(idmai) from mails");
		if (rs.next()) i = rs.getInt(1);
		closeReq(rs);
		return i;
	}
	
	public int getNextvalFichier() throws ClassNotFoundException, RemoteException, SQLException {
		int i = 0;
		ResultSet rs = executeSelect("select max(idfic) from fichiers");
		if (rs.next()) i = rs.getInt(1);
		closeReq(rs);
		return i;
	}
	
	public int getNextvalSondage() throws ClassNotFoundException, RemoteException, SQLException {
		int j = 0;
		ResultSet rs = executeSelect("select max(idson) from sondage");
		if (rs.next()) j = rs.getInt(1);
		closeReq(rs);
		return j;
	}
	
	public int getNextvalType() throws ClassNotFoundException, RemoteException, SQLException {
		int t = 0;
		ResultSet rs = executeSelect("select max(idtype) from types");
		if (rs.next()) t = rs.getInt(1);
		rs.close();
		return t;
	}
	public int getNextvalGroupe() throws ClassNotFoundException, RemoteException, SQLException {
		int i = -1;
		ResultSet rs = executeSelect("select max(idGr) from groupes");
		if (rs.next()) i = rs.getInt(1);
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

	public synchronized int ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException { 
		if (idGr == -1) {
			idGr = getNextvalGroupe();
		}
		idGr++;
		executeUpdate("insert into groupes (idGr, libelle) values (groupes_id.NEXTVAL ,'"+groupe+"')");
		
		if (lstUser != null ) {
			for (String u: lstUser) {
				executeUpdate("insert into faitPartieGroupe (login, idGr, dateEntree) VALUES ('"+u+"',"+idGr+",SYSDATE)");
			}
		
		}
		return idGr;
	}

	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("delete from faitPartieGroupe where idGr="+idGr);
		executeUpdate("delete from groupes where idGr="+idGr);
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
	
	public List<Droit> getAllDroitNotInType(int idType) throws RemoteException, ClassNotFoundException, SQLException {
		List<Droit> droit = new ArrayList<>();
		ResultSet rs = executeSelect("select * from droits  where idd not in (select idd from possede where idtype="+idType+")");
		while (rs.next()) {
			droit.add(new Droit(rs.getString(1), rs.getString(2)));
		}
		closeReq(rs);
		return droit;
	}
	
	public List<Droit> getAllDroitInType(int idType) throws RemoteException, ClassNotFoundException, SQLException {
		List<Droit> droit = new ArrayList<>();
		ResultSet rs = executeSelect("select * from droits  where idd in (select idd from possede where idtype="+idType+")");
		while (rs.next()) {
			droit.add(new Droit(rs.getString(1), rs.getString(2)));
		}
		closeReq(rs);
		return droit;
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

	public void ajouterType(String type, List<String> l) throws RemoteException, ClassNotFoundException, SQLException {
		if (t == -1) t=getNextvalType();
		t++;
		executeUpdate("insert into types (idType, libelle) values (types_id.NEXTVAL,'"+ type+"')");
		for (String d : l) {
			executeUpdate("insert into possede (idD,idType) values ('"+d+"','"+t+"')");
		}
		
	}
	
	public void modifierType(Type type, List<String> l) throws ClassNotFoundException, SQLException {
		executeUpdate("delete from possede where idType='"+type.getIdType()+"'");
		executeUpdate("update types set libelle='"+type.getLibelle()+"' where idType="+type.getIdType());
		for (String d : l) {
			executeUpdate("insert into possede (idD,idType) values ('"+d+"','"+type.getIdType()+"')");
		}
		
	}
	
	public List<Droit> getAllDroit() throws ClassNotFoundException, SQLException, RemoteException {
		List<Droit> l = new ArrayList<Droit>();
		ResultSet rs = executeSelect("select * from droits");
		while (rs.next()) {
			l.add(new Droit(rs.getString(1), rs.getString(2)));
		}
		return l;
	}

	public List<MelCell> chargerMails(String rec) throws ClassNotFoundException, RemoteException, SQLException {
		List<MelCell> fs = new ArrayList<>();
		MelCell m = null;
		ResultSet rs = executeSelect("select idMai,dateArrive,loginExpediteur,objet from mails where loginReceveur='"+rec+"' and (etat='VAL' or etat='SUPEN')");
		while (rs.next()) {
			m = new MelCell(rs.getInt(1),rs.getDate(2).toString(),rs.getString(3),rs.getString(4));
			fs.add(m);
		}
		closeReq(rs);
		return fs;
	}


	public List<MelCell> chargerMailsExp(String exp) throws ClassNotFoundException, RemoteException, SQLException {
		List<MelCell> fs = new ArrayList<>();
		MelCell m = null;
		ResultSet rs = executeSelect("select idMai,dateArrive,loginReceveur,objet from mails where loginExpediteur='"+exp+"' and (etat='VAL' or etat='SUPRE')");
		while (rs.next()) {
			m = new MelCell(rs.getInt(1),rs.getDate(2).toString(),rs.getString(3),rs.getString(4));
			fs.add(m);
		}
		closeReq(rs);
		return fs;
	}
	
	public void majMail(int id, String etat) throws ClassNotFoundException, SQLException {
		executeUpdate("update mails set etat='"+etat+"' where idMai="+id);
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
		if (rs != null) {
			while(rs.next()) {
				Droit d = new Droit(rs.getString(1), rs.getString(2));
				droits.add(d);
			}
			closeReq(rs);
		}
		
		return droits; 
	}
	
	public synchronized int ajouterSondage(String owner, String question, String reponses,int multiple, String date) throws RemoteException, ClassNotFoundException, SQLException{
		if (j == -1) j = getNextvalSondage();
		j++;
		executeUpdate("insert into sondage(idSon,libelle,dateDebut,dateFin,resultat,login,multiple,total) values ( sondage_id.nextval,'"+question+"',SYSDATE,TO_DATE('"+date+"','yyyy-mm-dd'),'"+reponses+"','"+owner+"',"+multiple+",0)");
		return j;
	}
	

	public List<SondageObj> getSondage(Utilisateur owner, int fait) {
		List<SondageObj> sf = new ArrayList<SondageObj>();
		try {
			ResultSet rs = null;
			if(fait==1) {
			   rs = executeSelect("select * from sondage where datefin>sysdate and idSon in (select idSon from vote where login='"+owner.getLogin()+"')");
			}else {
			   rs = executeSelect("select * from sondage where datefin>sysdate and idSon not in (select distinct idSon from vote where login='"+owner.getLogin()+"')");
			}
			while (rs.next()) {
				boolean multiple;
				if (rs.getInt("multiple")==1) {multiple = true;} else multiple=false; 
				sf.add(new SondageObj(rs.getInt("idSon"), rs.getString("login"), rs.getString("libelle"), rs.getString("resultat"), multiple, rs.getDate("dateFin").toString(), rs.getInt("total")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sf;
		}
	
	public Map<Integer,String> getAllSondage() throws ClassNotFoundException, RemoteException, SQLException {
		Map<Integer,String> sondage = new HashMap<>();
		ResultSet rs = executeSelect("select idSon,resultat from sondage");
		while (rs.next()) {
			sondage.put(rs.getInt(1),rs.getString(2));
		}		
		return sondage;
	}
	
	public void modifierVotes(String actor, int id, String resultat,int nbVote) throws RemoteException, ClassNotFoundException, SQLException {
		executeUpdate("update sondage set resultat ='"+resultat+"' where idSon="+id);
		executeUpdate("insert into vote values ("+id+",'"+actor+"',"+nbVote+",SYSDATE)");
	}
}