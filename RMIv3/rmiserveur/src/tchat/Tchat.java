package tchat;

import java.io.File;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import parametrage.PropertiesServeur;
import util.Groupe;
import util.ManipulationFichier;

public class Tchat extends UnicastRemoteObject implements TchatInterface  {
	static final long serialVersionUID = 42L;
	private  Map<Integer ,Vector <TchatListener>> list = new HashMap<>();
	private  Map<Integer ,StringBuffer> historique = new HashMap<>();
	private  String path;

	public Tchat(List<Groupe> groups)  throws RemoteException { 
		super();
		this.path=PropertiesServeur.getStockageTchat();
		for (Groupe s : groups) {
			String c = path+"/"+s.getidGr();
			historique.put(s.getidGr(), ManipulationFichier.chargerFichierTchat(c));
			list.put(s.getidGr(), new Vector <TchatListener> ());
		}
	}

	@Override
	public synchronized String getHistorique(Integer groupe) throws RemoteException {
		return historique.get(groupe).toString();
	}

	@Override
	public synchronized void envoyerMessage(String s, Integer groupe) throws RemoteException {
		StringBuffer newvaleur = historique.get(groupe).append(s);
		historique.put(groupe, newvaleur);
		ManipulationFichier.sauverFichier(path+"/"+groupe, s);
		notifyListeners(s,groupe);
	}	

	@Override
	public synchronized void addTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException {
		Vector<TchatListener> newlistener = list.get(groupe);
		newlistener.add(listener);
		list.put(groupe,newlistener);
	}

	@Override
	public synchronized void removeTchatListener (TchatListener listener, Integer groupe) throws java.rmi.RemoteException {
		list.get(groupe).remove(listener);
	}
	
	@Override
	public void ajouterGroupeTchat(Integer idGr) throws java.rmi.RemoteException {
		list.put(idGr, new Vector <TchatListener> ());
		ManipulationFichier.sauverFichier(path+"/"+idGr, "");
		historique.put(idGr, new StringBuffer(""));
	}

	/**
	 * Methode de notification aux listener
	 * @param message le nouveau message
	 * @param groupe le groupe auquel le message est destine
	 */
	private void notifyListeners(String message, Integer groupe) {	
		Vector <TchatListener> v = list.get(groupe);
		for (Enumeration<TchatListener> e = v.elements(); e.hasMoreElements();) { 
			TchatListener listener = (TchatListener) e.nextElement();
			try {
				listener.nouveauMessage(message, groupe);
			} catch(Exception re) {
				re.printStackTrace();
				v.remove(listener); 
			} 
		} 
	}

}
