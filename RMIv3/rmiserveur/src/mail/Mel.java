package mail;

import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import util.Utilisateur;
import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.bd.Connexionsgbd;
import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDOracle;
import fichier.Fichier;
import parametrage.PropertiesServeur;
import tchat.TchatListener;
import util.ManipulationFichier;

public class Mel implements MelInterface{

	private SGBD sgbd;
	private String chemin = PropertiesServeur.getStockageMail() +"/";
	private Map<String ,MelListener> listRecu = new HashMap<>();
	private Map<String ,MelListener> listEnvoye = new HashMap<>();
	
	public Mel (SGBD _sgbd) throws RemoteException {  
		sgbd = _sgbd;
	}

	@Override
	public List<Utilisateur> getAllUsers() throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getUsers();
	}


	@Override
	public void saveMessage(String u, String receveur, String message,String objet) throws RemoteException, ClassNotFoundException, SQLException{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		int nextval = sgbd.ajouterMail(chemin,u,receveur,objet);	
		String path = chemin +"/"+ nextval;
		ManipulationFichier.sauverFichier(path, message);
		notifyListenersRecu(new MelCell(nextval,dateFormat.format(date),u,objet), receveur);
		notifyListenersEnvoye(new MelCell(nextval,dateFormat.format(date),receveur,objet), u);
	}
	
	
	public String chargerMessage(String exp) {
		return ManipulationFichier.chargerFichierTchat(this.chemin+exp).toString();
	}
	
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMails(receveur);
	}

	public List<MelCell> chargerMailsExp(String expediteur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMailsExp(expediteur);
	}
	
	public void supprMailExp(int id) throws RemoteException, ClassNotFoundException, SQLException {
		String etat = sgbd.etatMail(id);
		if (etat.equals("SUPRE")) {
			new File(this.chemin+"/"+id).delete();
			sgbd.supprMail(id);
		}
		else if (etat.equals("VAL")) {
			sgbd.modifEtatMail(id, "SUPEN");
		}
	}
	
	public void supprMailRec(int id) throws RemoteException, ClassNotFoundException, SQLException {
		String etat = sgbd.etatMail(id);
		if (etat.equals("SUPEN")) {
			new File(this.chemin+"/"+id).delete();
			sgbd.supprMail(id);
		}
		else if (etat.equals("VAL")) {
			sgbd.modifEtatMail(id, "SUPRE");
		}
	}
	
	public synchronized void addMailRecuListener (MelListener listener, String login) throws java.rmi.RemoteException {
		System.out.println("adding listener recu -"+listener);
		listRecu.put(login,listener);
	}
	
	public synchronized void addMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException {
		System.out.println("adding listener envoye -"+listener);
		listEnvoye.put(login,listener);
	}

	public synchronized void removeMailRecuListener (MelListener listener, String login) throws java.rmi.RemoteException {
		System.out.println("removing listener  -"+listener);
		listRecu.remove(login);
	}
	
	public synchronized void removeMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException {
		System.out.println("removing listener -"+listener);
		listEnvoye.remove(login);
	}
	
	private void notifyListenersEnvoye(MelCell message, String login) {	
		try {
			listEnvoye.get(login).nouveauMailEnvoye(message);
		} catch(Exception re) {
			re.printStackTrace();
			System.out.println("removing listener -"+login);
			listEnvoye.remove(login); 
		} 
	}
	
	private void notifyListenersRecu(MelCell message, String login) {
		try {
			System.out.println(login);
			listRecu.get(login).nouveauMailRecu(message);
		} catch(Exception re) {
			re.printStackTrace();
			//System.out.println("removing listener -"+login);
			//listRecu.remove(login); 
		} 
	}
		
}
