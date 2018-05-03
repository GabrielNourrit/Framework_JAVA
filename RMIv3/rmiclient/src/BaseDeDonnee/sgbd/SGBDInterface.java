package BaseDeDonnee.sgbd;


import java.rmi.*;
import java.sql.SQLException;

import BaseDeDonnee.connexion.ConnexionInterface;

public interface SGBDInterface extends Remote{
	
	public ConnexionInterface nouvelleConnexion(String utilisateur, String motdepasse) throws RemoteException, ClassNotFoundException, SQLException;

}
