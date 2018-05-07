package tchat;

import java.rmi.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import util.ManipulationFichier;

public class Tchat implements TchatInterface  {
	static final long serialVersionUID = 42L;
	private Map<String ,Vector <TchatListener>> list = new HashMap<>();
	private Map<String ,StringBuffer> historique = new HashMap<>();
	private String path;

	public Tchat(List<String> fichier, String path)  throws RemoteException { 
		super();
		this.path=path;
		for (String s : fichier) {
			String c = path+"/"+s;
			System.out.println(c);
			historique.put(s, ManipulationFichier.chargerFichierTchat(c));
			//historique.put(s, ManipulationFichier.chargerFichierTchat("ressources/tchat2.txt"));
			list.put(s, new Vector <TchatListener> ());
		}

	}

	public synchronized String getHistorique(String groupe) throws RemoteException {
		return historique.get(groupe).toString();
	}

	public synchronized void envoyerMessage(String s, String groupe) throws RemoteException {
		StringBuffer newvaleur = historique.get(groupe).append(s);
		historique.put(groupe, newvaleur);
		ManipulationFichier.sauverFichier(path+groupe, s);
		notifyListeners(s,groupe);
	}	

	public synchronized void addTchatListener (TchatListener listener, String groupe) throws java.rmi.RemoteException {
		System.out.println("adding listener -"+listener);
		Vector<TchatListener> newlistener = list.get(groupe);
		newlistener.add(listener);
		list.put(groupe,newlistener);
		//list.add(listener); 
	}

	public synchronized void removeTchatListener (TchatListener listener, String groupe) throws java.rmi.RemoteException {
		System.out.println("removing listener -"+listener);
		list.get(groupe).remove(listener);
		//list.remove(listener); 
	}

	private void notifyListeners(String message, String groupe) {	
		Vector <TchatListener> v = list.get(groupe);
		for (Enumeration<TchatListener> e = v.elements(); e.hasMoreElements();) { 
			TchatListener listener = (TchatListener) e.nextElement();
			try {
				listener.nouveauMessage(message, groupe);
			} catch(Exception re) {
				re.printStackTrace();
				System.out.println("removing listener -"+listener);
				v.remove(listener); 
			} 
		} 
	}
}
