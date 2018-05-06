package tchat;

import java.rmi.*;
import util.ManipulationFichier;
 
public class Tchat implements TchatInterface  {
 
	public StringBuffer mess = new StringBuffer("");
	public StringBuffer historique = new StringBuffer("");
	private int i = 0;
 
	public Tchat()  throws RemoteException { 
		super();
		StringBuffer s = ManipulationFichier.chargerFichierTchat("ressources/tchat2.txt");
		historique=s;
	}
 
	public synchronized String getHistorique() throws RemoteException {
		return historique.toString();
	}
	public synchronized String getMessage() throws RemoteException {
		return mess.toString();
	}
	
	public synchronized void envoyerMessage(String s) throws RemoteException {
		if (i < 5) {
			mess.append(s);
			i++;
		}
		else {
			historique.append(mess);
			mess = new StringBuffer(s);
			i=0;
		}		
		ManipulationFichier.sauverFichier("ressources/tchat2.txt", s);
	}
}
