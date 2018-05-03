package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;

public interface FichierInterface extends Remote,MethodeServeur {
	public String enregisterFichier(String nom, byte[] contenu) throws RemoteException, ClassNotFoundException, SQLException;
	public byte[] sauvegarderFichier(String nom) throws ClassNotFoundException, SQLException, IOException;
}
