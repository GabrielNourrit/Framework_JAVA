package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import BaseDeDonnee.connexion.Connexion;
import BaseDeDonnee.connexion.ConnexionInterface;

public abstract class SGBD implements SGBDInterface{

	protected SGBD() throws RemoteException {
		super();
	}

	protected abstract Connexion creeSGBD(String utilisateur, String motdepasse) throws RemoteException;
	
	public ConnexionInterface nouvelleConnexion(String utilisateur, String motdepasse) throws RemoteException{
		ConnexionInterface connexion = this.creeSGBD(utilisateur, motdepasse);
		return connexion;
	}
}
