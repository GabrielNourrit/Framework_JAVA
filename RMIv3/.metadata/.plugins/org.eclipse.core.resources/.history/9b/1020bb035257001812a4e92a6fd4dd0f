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
import mail.MelCell;
import sondage.SondageObj;
import util.Droit;
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
	 * Constructeur proteger
	 * @throws RemoteException
	 */
	protected SGBD() throws RemoteException {
		this.creeSGBD();
	}
	
	/**
	 * Creer une instance de connexion qui sera utiliser pour les requetes SQL
	 * @return ConnexionBase
	 * @throws RemoteException
	 */
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
	
	/**
	 * Affiche les bases de donnees disponible
	 * @return List<String>
	 */
	public static List<String> printListBaseNom(){
		List<String> list = new ArrayList<>();
		for(Entry<String, SGBD> e : mesBases.entrySet()){
			list.add(e.getKey());
		}
		return list;
	}
	
	/**
	 * Ajoute un  nouveau fichier dans la base de donnees
	 * @param n : nom
	 * @param l : Expediteur
	 * @param id : Receveur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int ajouterFichier(String n,String l,int id) throws ClassNotFoundException, SQLException, RemoteException {
		return -1;
	}
	
	/**
	 * Permet de selectionner une base de donnees paris celle presente dans la list
	 * @default la base de donnees selectionner sera ORACLE
	 * @param baseDeDonnees
	 * @return SGBD
	 * @throws RemoteException
	 */
	public static SGBD determine(String baseDeDonnees) throws RemoteException{
		return mesBases.getOrDefault(baseDeDonnees.toUpperCase(), mesBases.get(TYPE_DEFAULT_SGBD));
	}
	
	/**
	 * Permet d'identification d'une personne
	 * @param login : identifiant
	 * @param mdp : mot de passe
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public boolean verifierMdp(String login, String mdp) throws ClassNotFoundException, RemoteException, SQLException {
		return false;
	}
	
	public Utilisateur getUse(String login) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Creation d'un utilisateur avec les deux informations principales et obligatoires (login et mot de passe et type).
	 * @param login de l'utilisateur
	 * @param mdp de l'utilisateur
	 * @param type de l'utilisateur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,Type type) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Renvoie un utilisateur en lui passant son login
	 * @param login
	 * @return Utilisateur
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMailsExp(String exp) throws ClassNotFoundException, RemoteException, SQLException{
		return null;
	}
	/**
	 * Creation d'un utilisateur avec les infos principale + nom et prenom
	 * @param login de l'utilisateur
	 * @param mdp de l'utilisateur
	 * @param nom de l'utilisateur
	 * @param prenom de l'utilisateur
	 * @param type de l'utilisateur
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,String nom,String prenom,Type type) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de rentrer un utilisateur puis un attribut que l'on veut modifier et enfin la valeur que l'attribut doit prendre
	 * @param loginUse de l'utilisateur
	 * @param attribut de l'utilisateur
	 * @param valeur de l'utilisateur
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
	
	/**
	 * Renvoie la liste des utilisateurs
	 * @return List<Utilisateur>
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException{ return null; }
	
	/**
	 * Renvoie la liste des fichiers
	 * @return List<Fichier>
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Fichier> getFichiers() throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Renvoie le fichier qui correspond a l'identifiant donner
	 * @param id
	 * @return Fichier
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public Fichier getUrlFichier(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Renvoie la valeur maximum du sondage
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public int getNextvalMail() throws ClassNotFoundException, RemoteException, SQLException {
		return 0;
	}
	
	/**
	 * Renvoie les fichiers qui corespond a l'identifiant d'un groupe
	 * @param id
	 * @return List<Fichier>
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Fichier> getFichiersGroupe(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Renvoie les groupes qui corespond a l'utilisateur
	 * @param l : login
	 * @return List<Groupe>
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Groupe> getGroupeUtilisateur(String l) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * renvoie le libelle d'un groupe
	 * @param id : numero de groupe
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public String getLibelleGroup(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Methode qui est proteger au niveau acces.
	 * Ajoute un mail dans la base de donnees
	 * @param path
	 * @param expediteur
	 * @param receveur
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public synchronized int ajouterMail(String path, String expediteur, String receveur) throws ClassNotFoundException, SQLException, RemoteException {
		return 0;
	}
	
	/**
	 * Renvoie tous les groupes present dans la base de donnees
	 * @return List<Groupe>
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Groupe> getGroupes()  throws RemoteException, ClassNotFoundException, SQLException {
		return null;
	}
	
	/**
	 * Ajoute des goupe dans la base de donnees
	 * @param groupe : nom du groupe
	 * @param lstUser : les utilisateurs du groupe
	 * @return int
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int ajouterGroupe(String groupe, List<String> lstUser) throws RemoteException, ClassNotFoundException, SQLException { return -1;}
	
	/**
	 * Supprime un groupe
	 * @param idGr
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void suprimerGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException { }
	
	/**
	 * Renvoie tous les login que possede le groupe
	 * @param idGr : numero de groupe
	 * @return List<String>
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> getAllLoginGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {	return null;}
	
	/**
	 * Renvoie une liste de tous les logins qui ne font partie d'aucun groupe
	 * @param idGr
	 * @return
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> getAllLoginNotInGroupe(int idGr) throws RemoteException, ClassNotFoundException, SQLException {return null;}
	
	/**
	 * Ajoute un utilisateur a un groupe
	 * @param idGr
	 * @param login
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void ajouterUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	/**
	 * Supprime un utilisateur a un groupe
	 * @param idGr
	 * @param login
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprimerUtilisateur(int idGr, String login) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	/**
	 * Renvoie une liste de tous les types qui existent
	 * @return
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Type> getAllType()  throws RemoteException, ClassNotFoundException, SQLException { return null; }

	/**
	 * Ajoute un nouveau type dans la base de donnees
	 * @param type
	 * @param l
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void ajouterType(String type, List<String> l) throws RemoteException, ClassNotFoundException, SQLException {
	}
	
	/**
	 * Permet de charger les mails d'un utilisateur
	 * @param rec
	 * @return List<MelCell>
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<MelCell> chargerMails(String rec) throws ClassNotFoundException, RemoteException, SQLException{
		return null;
	}
	
	/**
	 * Supprime un mail
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprMail(int id) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Methode qui est proteger au niveau acces.
	 * Ajoute un mail dans la base de donnees
	 * @param path
	 * @param expediteur
	 * @param receveur
	 * @param objet
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public synchronized int ajouterMail(String path, String expediteur, String receveur, String objet) throws ClassNotFoundException, SQLException, RemoteException {
		return -1;
	}
	
	/**
	 * Permet de connaitre l'etat d'un mail
	 * @param id
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public String etatMail(int id) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Renvoie les droit d'un type
	 * @param type
	 * @return List<Droit>
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public List<Droit> getDroits(int type) throws ClassNotFoundException, RemoteException, SQLException {
		return null;
	}
	
	/**
	 * Changer l'etat d'un mail
	 * @param id
	 * @param newEtat
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifEtatMail(int id, String newEtat) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Creer un nouveau sondage
	 * @param owner
	 * @param question
	 * @param reponses
	 * @param multiple
	 * @param date
	 * @return int
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int ajouterSondage(String owner, String question, String reponses,int multiple, String date) throws RemoteException, ClassNotFoundException, SQLException{
		return -1;
	}

	/**
	 * Renvoie les droit d'un type
	 * @param idType
	 * @return List<Droit>
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Droit> getAllDroitInType(int idType) throws RemoteException, ClassNotFoundException, SQLException {
		return null;
	}
	
	/**
	 * Renvoie les doits que 'on n'a pas
	 * @param idType
	 * @return List<Droit>
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Droit> getAllDroitNotInType(int idType) throws RemoteException, ClassNotFoundException, SQLException {
		return null;
	}
	
	/**
	 * Modification des votes du sondage
	 * @param actor
	 * @param id
	 * @param resultat
	 * @throws RemoteException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifierVotes(String actor, int id, String resultat) throws RemoteException, ClassNotFoundException, SQLException {}

	/**
	 * Renvoie la liste des sondage de l'utilisateur
	 * @param owner
	 * @param fait
	 * @return List<SondageObj>
	 */
	public List<SondageObj> getSondage(Utilisateur owner, int fait) {return null;}
	
	/**
	 * Rnvoie tous les sondages realiser
	 * @return
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 * @throws SQLException
	 */
	public Map<Integer,String> getAllSondage() throws ClassNotFoundException, RemoteException, SQLException {return null;}
	
	/**
	 * On modifie les doits pour un type
	 * @param type
	 * @param l
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifierType(Type type, List<String> l) throws ClassNotFoundException, SQLException {}
	
	/**
	 * renvoie tous les droits possiblent
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws RemoteException
	 */
	public List<Droit> getAllDroit() throws ClassNotFoundException, SQLException, RemoteException {
		return null;
	}
}
