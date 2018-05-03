package BaseDeDonnee.sgbd;


import java.rmi.*;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.connexion.ConnexionInterface;
import tchat.TchatInterface;

public interface SGBDInterface extends Remote, MethodeServeur {
	
	public ConnexionInterface nouvelleConnexion(String utilisateur, String motdepasse) throws RemoteException, ClassNotFoundException, SQLException;
}
