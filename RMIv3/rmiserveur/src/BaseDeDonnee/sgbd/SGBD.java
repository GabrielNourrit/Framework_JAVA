package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BaseDeDonnee.connexion.ConnexionBase;

public abstract class SGBD {
	
	protected Connection conn;
	protected Statement stmt;
	
	protected SGBD() throws RemoteException {
		this.creeSGBD();
	}

	protected abstract ConnexionBase creeSGBD() throws RemoteException;
	
	public abstract ResultSet executeSelect(String requete) throws SQLException, ClassNotFoundException, RemoteException;
	
	public abstract void closeReq(ResultSet rs) throws SQLException; 
}
