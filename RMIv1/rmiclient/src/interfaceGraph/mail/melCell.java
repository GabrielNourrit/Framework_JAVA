package interfaceGraph.mail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class melCell {
	    private String date;
	    private String objet;
	    private String mail;
	 
	    public melCell(String date, String objet, String mail) {
	        this.date = date;
	        this.objet = objet;
	        this.mail = mail;
	    }
	 
	    public String getDate() {
	        return date;
	    }
	    public String getObjet() {
	        return objet;
	    }
	    public String getMail() {
	        return mail;
	    }
	    public void setDate(String date) {
	        this.date = date;
	    }
	    public void setObjet(String obj) {
	        this.objet = obj;
	    }
	    public void setMail(String mail) {
	        this.mail = mail;
	    }
}