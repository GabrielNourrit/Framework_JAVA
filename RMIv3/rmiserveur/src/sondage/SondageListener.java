package sondage;

import java.rmi.Remote;

public interface SondageListener extends Remote {
	public void nouveauVote(String resultat, Integer sondage);
	public void nouveauSondage(SondageObj sondage);
}
