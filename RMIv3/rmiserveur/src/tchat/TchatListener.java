package tchat;

import java.rmi.Remote;

public interface TchatListener extends Remote {
	public void nouveauMessage(String message, Integer groupe);
}
