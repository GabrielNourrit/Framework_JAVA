package util;

import java.io.Serializable;

import java.util.List;

import fichier.Groupe;

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
	private List<Groupe> groupe;
	
	/*public Utilisateur(String _login) {
		login = _login;
	}*/
	
	public Utilisateur(String _login, String _nom, String _prenom, String _type, List<Groupe> g) {		
		this.login = _login;
		this.nom = _nom;
		this.prenom = _prenom;
		this.type = _type;
		this.groupe = g;
	}
	
	public void setMdp(String _mdp) {
		mdp= _mdp;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}

	public String getType(){
		return type;
	}
	
	public List<Groupe> getGroupe() {
		return groupe;
	}
	
	public String toString(){
		return login+" " +nom+" "+prenom+" "+type;
	}
}