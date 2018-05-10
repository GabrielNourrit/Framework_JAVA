package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.TypesInterface;
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
	private GroupesInterface connexGroupe;
	private TypesInterface connexType;
	
	public Utilisateur(String _login) {
		login = _login;
	}
	
	public Utilisateur(String _login, String _nom, String _prenom, Type _type) {		
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

	public Type getType(){
		return type;
	}
	
	public List<Groupe> getGroupe() throws Exception {
		connexGroupe = new Connectable<GroupesInterface>().connexion("Groupes");
		return connexGroupe.getGroupeLogin(login);
	}
	
	
	public void addGroupe(Groupe _groupe) {
		groupe.add(_groupe);
	}
	
	public String toString(){
		return login;
	}
	

	public void setDroits(List<Droit> _droits) {
		if (_droits != null) {
			droits= _droits;
		}
	}
	
	public List<Droit> getDroits(){
		return droits;
	}
	
	public boolean hasRight(String droit) throws Exception {
		connexType = new Connectable<TypesInterface>().connexion("Types");
		droits = connexType.getDroit(type.getIdType());
		for (Droit d : droits) {
			if (d.getId().equals(droit)) return true;
		}
		return false;
	}
	
	public void setGroup(List<Groupe> _groupes) {
		if (_groupes != null) {
			groupe = _groupes;
		}
	}
	
	public String contact(){
		return prenom+" "+nom+" #"+login;
	}
}