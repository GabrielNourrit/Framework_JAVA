package tchat;

import java.io.File;
import java.rmi.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import parametrage.PropertiesServeur;
import util.ManipulationFichier;

public class Tchat implements TchatInterface  {
	static final long serialVersionUID = 42L;
	private Map<Integer ,Vector <TchatListener>> list = new HashMap<>();
	private Map<Integer ,StringBuffer> historique = new HashMap<>();
	private String path;

	public Tchat(List<Integer> fichier)  throws RemoteException { 
		super();
		this.path=PropertiesServeur.getStockageTchat();
		for (Integer s : fichier) {
			String c = path+"/"+s;
			historique.put(s, ManipulationFichier.chargerFichierTchat(c));
			list.put(s, new Vector <TchatListener> ());
		}
	}

	public synchronized String getHistorique(Integer groupe) throws RemoteException {
		return historique.get(groupe).toString();
	}

	public synchronized void envoyerMessage(String s, Integer groupe) throws RemoteException {
		StringBuffer newvaleur = historique.get(groupe).append(s);
		historique.put(groupe, newvaleur);
		ManipulationFichier.sauverFichier(path+"/"+groupe, s);
		notifyListeners(s,groupe);
	}	

	public synchronized void addTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException {
		System.out.println("adding listener -"+listener);
		Vector<TchatListener> newlistener = list.get(groupe);
		System.out.println(newlistener);
		newlistener.add(listener);
		list.put(groupe,newlistener);
	}

	public synchronized void removeTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException {
		System.out.println("removing listener -"+listener);
		list.get(groupe).remove(listener);
	}
	
	public void ajouterGroupeTchat(Integer idGr) {
		new File(path+idGr);
		historique.put(idGr, new StringBuffer(""));
	}

	private void notifyListeners(String message, Integer groupe) {	
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
