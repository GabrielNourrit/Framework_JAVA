package connexion;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class OperationUtilisateur implements OperationUtilisateurInterface {

	@Override
	public boolean ModifierUtilisateur(Utilisateur uOld, Utilisateur uNew) throws RemoteException, ClassNotFoundException, SQLException {
		
		return false;
	}

	@Override
	public boolean SupprimerUtilisateur(String login)throws RemoteException, ClassNotFoundException, SQLException {
		boolean succes = true;
		try {
			CreationUtilisateur.supprUse(login);
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			succes = false;
		}
		return succes;
	}

	@Override
	public boolean AjouterUtilisateur(Utilisateur utilisateur)throws RemoteException, ClassNotFoundException, SQLException {
		boolean succes = true;
		try {
			CreationUtilisateur.creaUse(utilisateur.getLogin(), utilisateur.getMdp(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getType());
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			succes = false;
		}
		return succes;
	}
}
