package mail;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class MelCell implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
		private String date;
	    private String objet;
	    private String chemin;
	    private String expediteur;
	 
	    public MelCell(int id, String date, String chemin, String expediteur, String objet) {
	    	this.id =id;
	        this.date = date;
	        this.objet = objet;
	        this.chemin = chemin;
	        this.expediteur = expediteur;
	    }
	 
	    public String getDate() {
	        return date;
	    }
	    public String getObjet() {
	        return objet;
	    }
	    public String getChemin() {
	        return chemin;
	    }
	    public String getExpediteur() {
	        return expediteur;
	    }


}