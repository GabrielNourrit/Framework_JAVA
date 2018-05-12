package mail;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 447192624987987345L;
	private String login;
	private String nom;
	private String prenom;
	private String type;
	private String mdp;

	/**
	 * Construction del'utilisateur en recuperant le login, le nom, le prenom et le type
	 * On aura donc la creation d'une personne comportant ses informations
	 * @param _login de l'utilisateur
	 * @param _nom de l'utilisateur
	 * @param _prenom de l'utilisateur
	 * @param _type de l'utilisateur
	 */
	public Utilisateur(String _login, String _nom, String _prenom, String _type) {
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.type = _type;
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
	public String getType(){
		return type;
	}
	
	/**
     * Fonction pour retourner une liste d'information
     * contenant "login nom prenom type"
     * @return login nom prenom type de l'utilisateur
     */
	public String toString(){
		return login+" " +nom+" "+prenom+" "+type;
	}
	
	/**
     * Fonction pour retourner une liste d'information
     * contenant "prenom nom #login"
     * @return prenom nom #login de l'utilisateur
     */
	public String contact(){
		return prenom+" "+nom+" #"+login;
	}
}
