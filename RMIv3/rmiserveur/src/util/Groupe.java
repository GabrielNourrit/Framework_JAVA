package util;

import java.io.Serializable;

public class Groupe implements Serializable {
	
	private static final long serialVersionUID = 6292373950158732516L;
	private int i;
	private String l;
	
	/**
	 * Constructeur du groupe
	 * @param idGr du groupe
	 * @param libelle du groupe
	 */
	public Groupe(int idGr, String libelle) {
		this.i = idGr;
		this.l = libelle;
	}
	
	/**
	 * Methode permettant de retourner l'identifiant du groupe
	 * @return i l'identifiant du groupe
	 */
	public int getidGr() {
		return i;
	}
	
	/**
	 * Methode permettant de retourner le libelle du groupe
	 * @return l le libelle du groupe
	 */
	public String getLibelle() {
		return l;
	}
	
	/**
	 * Methode qui retourne en string le libelle
	 * return l le libelle du groupe
	 */
	public String toString() {
		return l;
	}
}
