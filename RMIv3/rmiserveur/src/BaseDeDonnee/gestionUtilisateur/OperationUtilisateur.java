package BaseDeDonnee.gestionUtilisateur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import BaseDeDonnee.sgbd.SGBD;
import util.Utilisateur;

public class OperationUtilisateur extends UnicastRemoteObject  implements OperationUtilisateurInterface {

	private SGBD sgbd;
	
	public OperationUtilisateur(SGBD _sgbd) throws RemoteException {
		super();
		sgbd = _sgbd;
	}
	
	@Override
	public boolean ModifierUtilisateur(Utilisateur uOld, Utilisateur uNew) throws RemoteException, ClassNotFoundException, SQLException {
		if (!(uOld.getNom().equals(uNew.getNom()))) {
			sgbd.modifNom( uNew.getNom(), uNew.getLogin());
		}
		if (!(uOld.getPrenom().equals(uNew.getPrenom()))) {
			sgbd.modifPrenom( uNew.getPrenom(), uNew.getLogin());
		}
		return true;
	}

	@Override
	public boolean SupprimerUtilisateur(String login)throws RemoteException, ClassNotFoundException, SQLException {
		boolean succes = true;
		try {
			sgbd.supprUseEtat(login);
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
			sgbd.creaUse(utilisateur.getLogin(), utilisateur.getMdp(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getType());
		} catch (ClassNotFoundException | SQLException e) {
			//e.printStackTrace();
			succes = false;
		}
		return succes;
	}

	@Override
	public void ModifierMdpUtilisateur(String login, String mdp)
			throws RemoteException, ClassNotFoundException, SQLException {
		sgbd.modifMdp(mdp, login);
	}
}
