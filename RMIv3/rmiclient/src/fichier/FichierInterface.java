package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;

public interface FichierInterface extends Remote {
	public String enregisterFichier(String nom, byte[] contenu) throws RemoteException, ClassNotFoundException, SQLException;
	public byte[] sauvegarderFichier(String nom) throws ClassNotFoundException, SQLException, IOException;
}
