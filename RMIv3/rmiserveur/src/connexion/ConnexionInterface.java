package connexion;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import BaseDeDonnee.MethodeServeur;
import fichier.Fichier;
import fichier.GestionFichier;

public interface ConnexionInterface extends Remote,MethodeServeur{
	public boolean verifierMdp(String utilisateur, String mdp) throws RemoteException, ClassNotFoundException, SQLException;
}
