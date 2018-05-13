package util;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import groupes.GroupesInterface;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 447192624987987345L;
	private String login;
	private String nom;
	private String prenom;
	private Type type;
	private String mdp;
	private List<Groupe> groupe;
	private List<Droit> droits;
	private GroupesInterface connex;
	private Registry registry;
	
	/**
	 * Construction del'utilisateur en recuperant le login
	 * @param _login de l'utilisateur
	 */
	public Utilisateur(String _login) {
		login = _login;
	}
	
	/**
	 * Construction del'utilisateur en recuperant le login, le nom, le prenom et le type
	 * On aura donc la creation d'une personne comportant ses informations
	 * Le groupe et les droits de l'utilisateur sont une liste vide
	 * @param _login de l'utilisateur
	 * @param _nom de l'utilisateur
	 * @param _prenom de l'utilisateur
	 * @param _type de l'utilisateur
	 * @throws RemoteException
	 */
	public Utilisateur(String _login, String _nom, String _prenom, Type _type) throws RemoteException {		
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.type = _type;
		groupe = new ArrayList<>();
		droits = new ArrayList<>();
	}
	
	/**
	 * On recupere un mot de passe
	 * @param _mdp de l'utilisateur
	 */
	public void setMdp(String _mdp) {
		mdp= _mdp;
	}
	
	/**
     * Fonction pour retourner le mot de passe
     * @return mdp de l'utilisateur
     */
	public String getMdp() {
		return mdp;
	}
	
	/**
     * Fonction pour retourner le login
     * @return login de l'utilisateur
     */
	public String getLogin() {
		return login;
	}
	
	/**
	* Fonction pour retourner le nom
	* @return nom de l'utilisateur
	*/
	public String getNom(){
		return nom;
	}
	
	/**
     * Fonction pour retourner le prenom
     * @return prenom de l'utilisateur
     */
	public String getPrenom(){
		return prenom;
	}

	/**
     * Fonction pour retourner le type
     * @return type de l'utilisateur
     */
	public Type getType(){
		return type;
	}
	
	/**
	 * Recupere le groupe dont l'utilisateur fais partis
	 * @return
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Groupe> getGroupe() throws AccessException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (GroupesInterface) registry.lookup("Groupes");	
		return connex.getGroupeLogin(login);
	}
	
	/** Ajout dans un groupe un nouveau groupe
	 * @param _groupe de l'utilisateur
	 */
	public void addGroupe(Groupe _groupe) {
		groupe.add(_groupe);
	}
	
	/**
	 * Fonction pour retourner l'objet sous forme de string
     * @return login de l'utilisateur
	 */
	public String toString(){
		return login+" " +nom+" "+prenom+" "+type;
	}
	
	/**
	 * Ajout d'un nouveau droit a l'utilisateur
	 * @param _droits de l'utilisateur
	 */
	public void setDroits(List<Droit> _droits) {
		if (_droits != null) {
			droits= _droits;
		}
	}
	
	/**
	 * Fonction qui retourne le droit de l'utilisateur
	 * @return droits de l'utilisateur
	 */
	public List<Droit> getDroits(){
		return droits;
	}
	
	/**
	 * 
	 * @param droit
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public boolean hasRight(String droit) {
		return droits.contains(droit);
	}
	
	/**
	 * Ajout d'un nouveau groupe a l'utilisateur
	 * @param _groupes de l'utilisateur
	 */
	public void setGroup(List<Groupe> _groupes) {
		if (_groupes != null) {
			groupe = _groupes;
		}
	}
	
	/**
     * Fonction pour retourner une liste d'information
     * contenant "prenom nom #login"
     * @return prenom nom #login
     */
	public String contact(){
		return prenom+" "+nom+" #"+login;
	}
}
