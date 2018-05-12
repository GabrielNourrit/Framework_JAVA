package util;

import java.io.Serializable;

public class Type implements Serializable {
	
	private static final long serialVersionUID = 4368137371471541699L;
	private int idType;
	private String libelle;
	
	/**
	 * Constructeur du type
	 * @param _idType du type
	 * @param _libelle du type
	 */
	public Type(int _idType, String _libelle) {
		idType = _idType;
		libelle = _libelle;
	}

	/**
	 * Methode permettant de retourner l'identifiant du type
	 * @return idType du type
	 */
	public int getIdType() {
		return idType;
	}

	/**
	 * Methode permettant d'ajouter un identifiant au type 
	 * @param idType du type
	 */
	public void setIdType(int idType) {
		this.idType = idType;
	}

	/**
	 * Methode permettant de retourner le libelle du type
	 * @param libelle du type
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Methode permettant d'ajouter un libelle au type
	 * @param libelle du type
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Methode qui retourne en string le libelle
	 * return libelle du type
	 */
	public String toString() {
		return libelle;
	}
}
