package fichier;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BaseDeDonnee.bd.Connexionsgbd;

public class Fichier implements Serializable {
	private int idMes;
	private String contenu;

	public Fichier(int i, String s) {
		this.idMes = i;
		this.contenu = s;
		
	}
	public String getFileLink() {
		return this.contenu;
		
	}
	public int getidMes() {
		return idMes;	
	}
}
