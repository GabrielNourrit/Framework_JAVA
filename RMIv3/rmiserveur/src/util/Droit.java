package util;

import java.io.Serializable;

public class Droit implements Serializable{
	private static final long serialVersionUID = -5082621310223039635L;
	private String libelle;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public Droit(String _id, String _libelle) {
		libelle=_libelle;
		id=_id;
	}
}
