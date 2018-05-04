package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;

public interface GestionFichierInterface extends Remote {
	public String upload(String nom, byte[] contenu) throws RemoteException, ClassNotFoundException, SQLException;
	public byte[] download(String nom) throws ClassNotFoundException, SQLException, IOException;
	public Fichier[] recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException;
	public Fichier recupererFichier(String contenu) throws RemoteException, ClassNotFoundException, SQLException;
}
