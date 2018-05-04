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
	
	public String upload(String nom, byte[] contenu, String l) throws RemoteException, ClassNotFoundException, SQLException {
		 try{
	            Files.write(Paths.get(nom),contenu);
	            sgbd.ajouterFichier(nom, l);
	        } catch (IOException ex) {
	            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
	            return "Non recu";
	        }
	        return "Bien recu";
	}
	
	public byte[] download(int id) throws ClassNotFoundException, SQLException, IOException {
		Fichier f = sgbd.getUrlFichier(id);
		return Files.readAllBytes(Paths.get(f.getFileLink()+"/"+f.getNom())); 
	}

	@Override
	public List<Fichier> recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException {
		return sgbd.getFichiers();
	}
	
	@Override
	public Fichier recupererFichier(int id) throws RemoteException, ClassNotFoundException, SQLException {
		/*int i=-1;
		ResultSet rs = sgbd.executeSelect("select idMes,contenue from Messages join utilisateurs on loginExpediteur = login where contenue = '"+ nom +"'");
		if (rs.next()) i = rs.getInt("idMes");
		Fichier f = new Fichier(i,nom,"");
		return f;*/
		return null;
	}
	
}
