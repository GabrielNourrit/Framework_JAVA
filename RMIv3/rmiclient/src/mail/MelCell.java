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
	 
	    public String getDate() {
	        return date;
	    }
	    public String getObjet() {
	        return objet;
	    }
	    public String getExpediteur() {
	        return expediteur;
	    }
	    public int getId() {
	    	return id;
	    }
}