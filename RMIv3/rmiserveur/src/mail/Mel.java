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
import parametrage.PropertiesServeur;
import util.ManipulationFichier;

public class Mel implements MelInterface{

	private SGBD sgbd;
	private String chemin = PropertiesServeur.getStockageMail() +"/";
	
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
		int nextval = sgbd.ajouterMail(chemin+u,u,receveur,objet);
		
		/* -------------------------------------------------*/
		
		File nouveau = new File(chemin+u);
		if(!nouveau.exists()){
			nouveau.mkdirs();
		}
		
		String path = chemin + u+"/"+ nextval;
		ManipulationFichier.sauverFichier(path, message);
		
	}
	
	
	public String chargerMessage(String exp) {
		return ManipulationFichier.chargerFichierTchat(this.chemin+exp).toString();
	}
	
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMails(receveur);
	}

	public void supprMail(int id, String exp) throws RemoteException, ClassNotFoundException, SQLException {
		System.out.println(this.chemin+exp+"/"+id);
		new File(this.chemin+exp+"/"+id).delete();
		sgbd.supprMail(id);
	}
}
