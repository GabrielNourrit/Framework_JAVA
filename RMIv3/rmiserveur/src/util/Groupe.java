package util;

import java.io.Serializable;

public class Groupe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6292373950158732516L;
	private int i;
	private String l;
	
	public Groupe(int idGr, String libelle) {
		this.i = idGr;
		this.l = libelle;
	}
	
	public int getidGr() {
		return i;
	}
	public String getLibelle() {
		return l;
	}
	
	public String toString() {
		return l;
	}
}
