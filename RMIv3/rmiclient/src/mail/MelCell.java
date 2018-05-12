package mail;

import java.io.Serializable;

public class MelCell implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
		private String date;
	    private String objet;
	    private String expediteur;
	 
	    /**
	     * Information qui concerne les mails que l'on envoi ou recoi
	     * on recupère les informations suivante:
	     * @param id reference le numero d'identifiant du mail
	     * @param date reference la date du mail
	     * @param expediteur reference l'expediteur du mail
	     * @param objet reference l'objet du mail
	     */
	    public MelCell(int id, String date, String expediteur, String objet) {
	    	this.id =id;
	        this.date = date;
	        this.objet = objet;
	        this.expediteur = expediteur;
	    }
	 
	    /**
	     * Fonction pour retourner la date
	     * @return date du mail envoyer
	     */
	    public String getDate() {
	        return date;
	    }
	    
	    /**
	     * Fonction pour retourner l'objet
	     * @return objet du mail envoyer
	     */
	    public String getObjet() {
	        return objet;
	    }
	    
	    /**
	     * Fonction pour retourner l'expediteur
	     * @return expediteur du mail envoyer
	     */
	    public String getExpediteur() {
	        return expediteur;
	    }
	    
	    /**
	     * Fonction pour retourner l'identifiant
	     * @return id du mail envoyer
	     */
	    public int getId() {
	    	return id;
	    }
}