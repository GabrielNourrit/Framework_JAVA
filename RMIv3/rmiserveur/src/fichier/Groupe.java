package fichier;

import java.io.Serializable;

public class Groupe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGr;
	private String libelle;
	
	public Groupe(int i, String l) {
		idGr=i;
		libelle=l;
	}
	
	public int getIdGr() {
		return idGr;
	}
	
	public String toString() {
		return libelle;
	}
}
