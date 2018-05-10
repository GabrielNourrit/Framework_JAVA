package mail;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 447192624987987345L;
	/**
	 * 
	 */
	private String login;
	private String nom;
	private String prenom;
	private String type;
	private String mdp;

	public Utilisateur(String _login, String _nom, String _prenom, String _type) {
		
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.type = _type;
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
     * Fonction pour retourner le type
     * @return type
     */
	public String getType(){
		return type;
	}
	
	/**
     * Fonction pour retourner une liste d'information
     * contenant "login nom prenom type"
     * @return login nom prenom type
     */
	public String toString(){
		return login+" " +nom+" "+prenom+" "+type;
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
