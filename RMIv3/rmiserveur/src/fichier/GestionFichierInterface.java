package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.MethodeServeur;

public interface GestionFichierInterface extends Remote,MethodeServeur {
	public String upload(String nom, byte[] contenu, String l) throws RemoteException, ClassNotFoundException, SQLException;
	public byte[] download(int id) throws ClassNotFoundException, SQLException, IOException;
	public List<Fichier> recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException;
	public Fichier recupererFichier(int id) throws RemoteException, ClassNotFoundException, SQLException;
}
