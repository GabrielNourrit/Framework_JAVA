package fichier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.remote.rmi.RMIServer;

import BaseDeDonnee.bd.Connexionsgbd;
import BaseDeDonnee.sgbd.SGBD;
import connexion.ConnexionInterface;


public class GestionFichier implements GestionFichierInterface {

	private SGBD sgbd;
	
	public GestionFichier (SGBD sgbd) throws RemoteException {  
		super();
		this.sgbd=sgbd;
	}
	
	public String enregisterFichier(String nom, byte[] contenu) throws RemoteException, ClassNotFoundException, SQLException {
		 try{
	            Files.write(Paths.get(nom),contenu);
	        } catch (IOException ex) {
	            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
	            return "Non reçu";
	        }
	        return "Bien reçu";
	}
	
	public byte[] sauvegarderFichier(String nom) throws ClassNotFoundException, SQLException, IOException {
		return Files.readAllBytes(Paths.get(nom)); 
	}

	@Override
	public Fichier[] recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException {
		String c = "";
		Integer i = 0;	
		ResultSet rs = sgbd.executeSelect("select idMes, contenue from messages");
		List<Fichier> fs = new ArrayList<Fichier>();
		Fichier f;
		while (rs.next()) {
			i = rs.getInt("idMes");
			c = rs.getString("contenue");
			f = new Fichier(i,c);
			fs.add(f);
		}
		Fichier[] fichiers = fs.toArray(new Fichier[fs.size()]);
		fichiers = (Fichier[]) fs.toArray();
		return fichiers;
	}
	
	@Override
	public Fichier recupererFichier(String contenu) throws RemoteException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int i=-1;
		ResultSet rs = sgbd.executeSelect("select idMes,contenue from Messages join utilisateurs on loginExpediteur = login where contenue = '"+ contenu +"'");
		if (rs.next()) i = rs.getInt("idMes");
		Fichier f = new Fichier(i,contenu);
		return f;
	}
	
}
