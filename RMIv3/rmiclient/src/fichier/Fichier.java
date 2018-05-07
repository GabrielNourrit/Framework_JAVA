package fichier;

import java.io.Serializable;

public class Fichier implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idFic;
	private String nom;
	private String url;

	public Fichier(int i, String n, String s) {
		this.idFic = i;
		this.nom = n;
		this.url = s;
		
	}
	public String getFileLink() {
		return this.url;
		
	}
	public int getidFic() {
		return idFic;	
	}
	
	public String getNom() {
		return nom;
	}
	public String toString() {
		return nom;
	}
}
