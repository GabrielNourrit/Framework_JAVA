package mail;

import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Utilisateur;
import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.bd.Connexionsgbd;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDOracle;
import fichier.Fichier;
import util.ManipulationFichier;

public class Mel implements MelInterface{

	private SGBD sgbd;
	
	public Mel (SGBD _sgbd) throws RemoteException {  
		sgbd = _sgbd;
	}

	@Override
	public List<Utilisateur> getAllUsers() throws RemoteException, ClassNotFoundException, SQLException {
		/*ArrayList<Utilisateur>retour = new ArrayList<Utilisateur>();
		//chargement dans la bdd :
		Utilisateur u1 = new Utilisateur("guevarat","guevara","thomas","connard",null);
		Utilisateur u2 = new Utilisateur("nourritg","nourrit","gabriel","Pute",null);*/
		return sgbd.getUsers();
		/*retour.add(u1);
		retour.add(u2);
		
		return retour;*/
	}


	@Override
	public void saveMessage(String u, String receveur, String message,String objet) throws RemoteException, ClassNotFoundException, SQLException{
		
		/*
		 * Récupéré de la base de donnée l'id du message
		 * 
		 */
		int nextval = sgbd.getNextvalMail();
		
		/* -------------------------------------------------*/
		
		
		
		File nouveau = new File("ressources/stockage/"+u);
		if(!nouveau.exists()){
			nouveau.mkdirs();
		}
		
		String path = "ressources/stockage/" + u+"/"+ nextval;
		new ManipulationFichier().sauverFichier(path, message);
		sgbd.ajouterMail(path,u,receveur,objet);
	}
	
	
	public String chargerMessage(String chemin) {
		return ManipulationFichier.chargerFichierTchat(chemin).toString();
	}
	
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMails(receveur);
	}

	
	/*public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		int n = 0;
		
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
