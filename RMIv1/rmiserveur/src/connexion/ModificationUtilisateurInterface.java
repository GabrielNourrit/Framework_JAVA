package connexion;

import java.rmi.Remote;

public interface ModificationUtilisateurInterface extends Remote {
	public boolean ModifierUtilisateur(Utilisateur uOld, Utilisateur uNew);
	
	public boolean SupprimerUtilisateur(Utilisateur utilisateur);
}
