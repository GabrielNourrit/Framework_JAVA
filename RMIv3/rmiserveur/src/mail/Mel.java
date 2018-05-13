package mail;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Utilisateur;
import BaseDeDonnee.sgbd.SGBD;
import parametrage.PropertiesServeur;
import util.ManipulationFichier;

public class Mel extends UnicastRemoteObject implements MelInterface{

	private static final long serialVersionUID = -1388677820018450837L;
	private SGBD sgbd;
	private String chemin = PropertiesServeur.getStockageMail() +"/";
	private Map<String ,MelListener> listRecu = new HashMap<>();
	private Map<String ,MelListener> listEnvoye = new HashMap<>();
	
	/**
	 * On recupere le sgbd du mail
	 * @param _sgbd
	 * @throws RemoteException
	 */
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
	
	
	/**
	 * Recuperation des messages des tchats dans les fichiers
	 * @param exp renseigne le login de l'expediteur
	 */
	public String chargerMessage(String exp) {
		return ManipulationFichier.chargerFichierTchat(this.chemin+exp).toString();
	}
	
	/**
	 * Recuperation de la liste de mail re�u selon un utilisateur
	 * @param receveur renseigne le login du receveur
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMails(receveur);
	}

	/**
	 * Recuperation de la liste de mail emis selon un utilisateur
	 * param receveur
	 * @param receveur le login du receveur
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMailsExp(String expediteur) throws ClassNotFoundException, RemoteException, SQLException {
		return sgbd.chargerMailsExp(expediteur);
	}
	
	/**
	 * Suppression de chemin pour atteindre un mail que l'on a envoyer
	 * Il n'apparaitras plus dans la liste des mails envoyer
	 * @param id l'identifiant du mail
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
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
	
	/**
	 * Suppression de chemin pour atteindre un mail que l'on a re�u
	 * Il n'apparaitras plus dans la liste des mails re�u
	 * @param id l'identifiant du mail
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
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
		listRecu.put(login,listener);
	}
	
	public synchronized void addMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException {
		listEnvoye.put(login,listener);
	}

	public synchronized void removeMailRecuListener (MelListener listener, String login) throws java.rmi.RemoteException {
		listRecu.remove(login);
	}
	
	public synchronized void removeMailEnvoyeListener (MelListener listener, String login) throws java.rmi.RemoteException {
		listEnvoye.remove(login);
	}
	
	private void notifyListenersEnvoye(MelCell message, String login) {	
		try {
			listEnvoye.get(login).nouveauMailEnvoye(message);
		} catch(Exception re) {
			//re.printStackTrace();
			listEnvoye.remove(login); 
		} 
	}
	
	private void notifyListenersRecu(MelCell message, String login) {
		try {
			listRecu.get(login).nouveauMailRecu(message);
		} catch(Exception re) {
			//re.printStackTrace();
		} 
	}
		
}
