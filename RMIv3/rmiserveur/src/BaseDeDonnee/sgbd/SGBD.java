package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import BaseDeDonnee.connexion.ConnexionBase;
import fichier.Fichier;
import fichier.Groupe;
import mail.MelCell;
import util.Groupe;
import util.Type;
import util.Utilisateur;

public abstract class SGBD {
	
	protected Connection conn;
	protected Statement stmt;
	protected static int i;
	
	private static final String TYPE_DEFAULT_SGBD = "ORACLE";
	
	private static final Map<String, SGBD> mesBases;
	static {
        Map<String, SGBD> aMap = new HashMap<>();
        try {
        	aMap.put(TYPE_DEFAULT_SGBD,new SGBDOracle());
		} catch (RemoteException e) {
			System.err.println("Erreur d'initialisation :" + e.getMessage());
		}
        mesBases = aMap;
    }
	
	/**
	 * Constructeur prot�ger
	 * @throws RemoteException
	 */
	protected SGBD() throws RemoteException {
		this.creeSGBD();
	}

	protected abstract ConnexionBase creeSGBD() throws RemoteException;
	
	/**
	 * Recherche das la liste si il existe deja
	 * 	- si oui alors rien n'est fait
	 *  - si non on ajout dans la liste
	 * @param nomBase
	 * @param sgbd
	 */
	public static void addTypeSGBD(String nomBase, SGBD sgbd){
		String nom = nomBase.toUpperCase();
		if(!mesBases.containsKey(nom)){
			mesBases.put(nom,sgbd);
		}
	}
	
	public static List<String> printListBaseNom(){
		List<String> list = new ArrayList<>();
		for(Entry<String, SGBD> e : mesBases.entrySet()){
			list.add(e.getKey());
		}
		return list;
	}
	
	
	public void ajouterFichier(String n,String l,int id) throws ClassNotFoundException, SQLException {
	}
	
	public static SGBD determine(String baseDeDonnees) throws RemoteException{
		return mesBases.getOrDefault(baseDeDonnees.toUpperCase(), mesBases.get(TYPE_DEFAULT_SGBD));
	}
	
	public boolean verifierMdp(String login, String mdp) throws ClassNotFoundException, RemoteException, SQLException {
		return false;
	}
	
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Cr�ation d'un utilisateur avec les deux informations principales et obligatoires (login et mot de passe et type).
	 * @param login
	 * @param mdp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,Type type) throws ClassNotFoundException, SQLException {
	}
	
	public List<MelCell> chargerMailsExp(String exp) throws ClassNotFoundException, RemoteException, SQLException{
		return null;
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
	public void creaUse (String login,String mdp,String nom,String prenom,Type type) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de rentrer un utilisateur puis un attribut que l'on veut modifier et enfin la valeur que l'attribut doit prendre
	 * @param loginUse
	 * @param attribut
	 * @param valeur
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void modifAttriUser(String loginUse, String attribut,String valeur) throws ClassNotFoundException, SQLException {
	}
	/**
	 * Fonction permettant de modifier le mot de passe d'un utulisateur donn�e
	 * @param mdp
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifMdp(String mdp, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le nom d'un utilisateur donn�e
	 * @param nom
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifNom(String nom, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le prenom d'un utilisateur donn�e
	 * @param prenom
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifPrenom(String prenom, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le type d'un utilisateur donn�e
	 * @param type
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifType(String type, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier la description d'un utilisateur donn�e
	 * @param description
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifDescri(String description, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Permet de supprimer un utilisateur donn�e
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprUse (String user) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Permet de changer l'etat d'un utilisateur en supprimer
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprUseEtat (String user) throws ClassNotFoundException, SQLException {
	}
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException{ return null; }
	
	public List<Fichier> getFichiers() throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	public Fichier getUrlFichier(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	public void ajouterFichier(String n,String l) throws ClassNotFoundException, SQLException {
	}
	
	
	public int getNextvalMail() throws ClassNotFoundException, RemoteException, SQLException {
		return 0;
	}
	public List<Fichier> getFichiersGroupe(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	public List<Groupe> getGroupeUtilisateur(String l) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	public String getLibelleGroup(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	public synchronized int ajouterMail(String path, String expediteur, String receveur) throws ClassNotFoundException, SQLException, RemoteException {
		return 0;
	}
	
	public List<Groupe> getGroupes()  throws RemoteException, ClassNotFoundException, SQLException {
		return null;
	}
	
	public void ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException { }
	
	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException { }
	
	public List<String> getAllLoginGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {	return null;}
	
	public List<String> getAllLoginNotInGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {return null;}
	
	public void ajouterUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	public void supprimerUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	public List<Type> getAllType()  throws RemoteException, ClassNotFoundException, SQLException { return null; }

	public void ajouterType(String type) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	public List<MelCell> chargerMails(String rec) throws ClassNotFoundException, RemoteException, SQLException{
		return null;
	}
	
	public void supprMail(int id) throws ClassNotFoundException, SQLException {
	}
	
	public synchronized int ajouterMail(String path, String expediteur, String receveur, String objet) throws ClassNotFoundException, SQLException, RemoteException {
		return -1;
	}
}
