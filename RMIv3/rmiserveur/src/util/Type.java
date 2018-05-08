package util;

import java.io.Serializable;

public class Type implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4368137371471541699L;
	private int idType;
	private String libelle;
	
	public Type(int _idType, String _libelle) {
		idType = _idType;
		libelle = _libelle;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String toString() {
		return libelle;
	}
}
