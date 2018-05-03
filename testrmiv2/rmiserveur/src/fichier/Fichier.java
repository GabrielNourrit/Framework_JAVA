package fichier;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.remote.rmi.RMIServer;

import BD.Connexionsgbd;
import connexion.MethodeServeur;

public class Fichier extends MethodeServeur implements FichierInterface{

	public Fichier (String url) throws RemoteException {  
		super(url);
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
}
