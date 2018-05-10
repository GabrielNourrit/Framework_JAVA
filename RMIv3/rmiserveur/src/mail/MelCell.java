package mail;

import java.io.Serializable;

public class MelCell implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
	private String objet;
	private String expediteur;

	public MelCell(int id, String date, String expediteur, String objet) {
		this.id =id;
		this.date = date;
		this.objet = objet;
		this.expediteur = expediteur;
	}

	/**
     * Fonction pour retourner l'identifiant
     * @return id
     */
	public int getId(){
		return id;
	}	
	
	/**
     * Fonction pour retourner la date
     * @return date
     */
	public String getDate() {
		return date;
	}
	
	/**
     * Fonction pour retourner l'objet
     * @return objet
     */
	public String getObjet() {
		return objet;
	}
	
	/**
     * Fonction pour retourner l'expediteur
     * @return expediteur
     */
	public String getExpediteur() {
		return expediteur;
	}
}