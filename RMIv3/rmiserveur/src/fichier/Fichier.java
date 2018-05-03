package fichier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.remote.rmi.RMIServer;


public class Fichier implements FichierInterface{

	public Fichier () throws RemoteException {  
		super();
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
