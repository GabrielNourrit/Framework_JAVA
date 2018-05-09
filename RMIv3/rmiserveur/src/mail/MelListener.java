package mail;

import java.rmi.Remote;

public interface MelListener extends Remote {
	public void nouveauMailRecu(MelCell message);
	public void nouveauMailEnvoye(MelCell message);
}
