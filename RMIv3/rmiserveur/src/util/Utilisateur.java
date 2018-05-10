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
	/**
	 * 
	 */
	private String login;
	private String nom;
	private String prenom;
	private Type type;
	private String mdp;
	private List<Groupe> groupe;
	private List<Droit> droits;
	private GroupesInterface connex;
	private Registry registry;
	
	public Utilisateur(String _login) {
		login = _login;
	}
	
	public Utilisateur(String _login, String _nom, String _prenom, Type _type) throws RemoteException {		
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.type = _type;
		groupe = new ArrayList<>();
		droits = new ArrayList<>();
	}
	
	public void setMdp(String _mdp) {
		mdp= _mdp;
	}
	
	/**
     * Fonction pour retourner le mot de passe
     * @return mdp
     */
	public String getMdp() {
		return mdp;
	}
	
	/**
     * Fonction pour retourner le login
     * @return login
     */
	public String getLogin() {
		return login;
	}
	
	/**
     * Fonction pour retourner le nom
     * @return nom
     */
	public String getNom(){
		return nom;
	}
	
	/**
     * Fonction pour retourner le prenom
     * @return prenom
     */
	public String getPrenom(){
		return prenom;
	}

	/**
     * Fonction pour retourner le type d'utilisateur
     * @return type
     */
	public Type getType(){
		return type;
	}
	
	public List<Groupe> getGroupe() throws AccessException, RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (GroupesInterface) registry.lookup("Groupes");	
		return connex.getGroupeLogin(login);
	}
	
	public void addGroupe(Groupe _groupe) {
		groupe.add(_groupe);
	}
	
	/**
     * Fonction pour retourner une liste d'information
     * contenant "login nom prenom type"
     * @return login nom prenom type
     */
	public String toString(){
		return login+" " +nom+" "+prenom+" "+type;
	}
	

	public void setDroits(List<Droit> _droits) {
		if (_droits != null) {
			droits= _droits;
		}
	}
	
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
	 * Methode pour mettre a jour les groupes
	 * @param _groupes une liste de groupe
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
