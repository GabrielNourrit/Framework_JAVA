package fichier;

import java.io.IOException;
import java.rmi.*;
import java.sql.SQLException;
import java.util.List;

import BaseDeDonnee.MethodeServeur;
import util.Groupe;

public interface GestionFichierInterface extends Remote,MethodeServeur {
	public boolean upload(String nom, byte[] contenu, String l, int id) throws RemoteException, ClassNotFoundException, SQLException;
	public byte[] download(int id) throws ClassNotFoundException, SQLException, IOException;
	public List<Fichier> recupererTousFichiers() throws RemoteException, ClassNotFoundException, SQLException;
	public List<Fichier> recupererFichierGroupe(int id) throws ClassNotFoundException, RemoteException, SQLException;
	public List<Groupe> recupererGroupe(String l) throws ClassNotFoundException, RemoteException, SQLException;
	public void ajouterGroupeFichier(Integer idGr) throws java.rmi.RemoteException;
	public void addGroupListener (FichierListener listener, Integer groupe) throws java.rmi.RemoteException;
	public void removeGroupListener (FichierListener listener, Integer groupe) throws java.rmi.RemoteException;
}
