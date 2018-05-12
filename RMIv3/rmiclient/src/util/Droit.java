package util;

import java.io.Serializable;

public class Droit implements Serializable{

	private static final long serialVersionUID = -5082621310223039635L;
	private String libelle;
	private String id;
	
	/**
	 * Fonction qui permet de recuperer l'identifiant du droit
	 * @return id du droit
	 */
	public String getId() {
		return id;
	}

	/**
	 * Fonction qui permet d'incrementer les droit d'un identifiant
	 * @param id du droit
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Fonction qui permet de recuperer le libelle du droit
	 * @return libelle du droit
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Fonction qui permet d'incrementer les droit d'un libelle
	 * @param libelle du droit
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Constructeur des droits
	 * @param _libelle du droit
	 * @param _id du droit
	 */
	public Droit(String _libelle, String _id) {
		libelle=_libelle;
		id=_id;
	}
	
	public String toString() {
		return id;
	}
}
