package fichier;

import java.io.Serializable;

public class Fichier implements Serializable {

	private static final long serialVersionUID = -8820169256695910647L;
	private int idFic;
	private String nom;
	private String url;

	/**
	 * Constructeur de la formation du fichier
	 * il prend comme information:
	 * @param i l'identifiant du fichier
	 * @param n le nom du fichier
	 * @param s le liens du fichier
	 */
	public Fichier(int i, String n, String s) {
		this.idFic = i;
		this.nom = n;
		this.url = s;
		
	}
	
	/**
	 * Methode qui retourne le liens du fichier
	 * @return url
	 */
	public String getFileLink() {
		return this.url;
	}
	
	/**
	 * Methode qui retourne l'identifiant du fichier
	 * @return idFic du fichier
	 */
	public int getidFic() {
		return idFic;	
	}
	
	/**
	 * Methode qui retourne le nom du fichier
	 * @return nom du fichier
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Methode qui retourne en string le nom du fichier
	 * @return nom du fichier en string
	 */
	public String toString() {
		return nom;
	}
}
