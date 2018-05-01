package tchat;

import java.rmi.*;

import connexion.MethodeServeur;
import util.ManipulationFichier;
 
public class Tchat extends MethodeServeur implements TchatInterface  {
 
	public String name;
	public TchatInterface client=null;
	public StringBuffer mess = new StringBuffer("");
	public StringBuffer historique = new StringBuffer("");
	private int i = 0;
 
	public Tchat(String n)  throws RemoteException { 
		super(n);
		this.name=n;
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
