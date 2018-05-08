package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Utilisateur;

public interface MelInterface extends Remote{

	public List<Utilisateur> getAllUsers() throws RemoteException, ClassNotFoundException, SQLException;
	public void saveMessage(String u, String receveur, String message,String objet) throws RemoteException, ClassNotFoundException, SQLException;
	public String chargerMessage(String chemin) throws RemoteException;
	public List<MelCell> chargerMails(String receveur) throws ClassNotFoundException, RemoteException, SQLException;
	public void supprMail(int id, String chemin) throws RemoteException,ClassNotFoundException, SQLException;
}
