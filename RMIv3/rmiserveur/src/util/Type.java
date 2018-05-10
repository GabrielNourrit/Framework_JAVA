package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Type implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4368137371471541699L;
	private int idType;
	private String libelle;
	private List<Droit> droits;
	
	public Type(int _idType, String _libelle) {
		idType = _idType;
		libelle = _libelle;
		droits = new ArrayList<Droit>();
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
	
	public List<String> getDroits(){
		
		return null;
	}
	
	public String toString() {
		return libelle;
	}
}
