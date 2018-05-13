package sondage;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.sgbd.SGBD;
import oracle.net.aso.s;
import tchat.TchatInterface;
import tchat.TchatListener;
import util.Utilisateur;

public class Sondage extends UnicastRemoteObject  implements SondageInterface{
	private SGBD sgbd;
	private Map<Integer ,Vector <SondageListener>> list = new HashMap<>();
	private Vector <SondageListener> listNew =  new Vector <SondageListener> ();
	private Map<Integer ,String> resultat = new HashMap<>();

	public Sondage(SGBD sgbd) throws ClassNotFoundException, RemoteException, SQLException{
		this.sgbd = sgbd;
		resultat = sgbd.getAllSondage();
		for (Entry<Integer, String> t : resultat.entrySet())
			list.put(t.getKey(), new Vector <SondageListener> ());
	}

	@Override
	public boolean creerSondage(String owner, String question, String reponses,boolean multiple, String date) throws RemoteException {
		boolean ok = false;
		try {
			int mult;
			if (multiple)  {mult = 1;} else mult = 0;
			int i = sgbd.ajouterSondage(owner, question, reponses, mult, date);
			ok = true;
			resultat.put(i, reponses);
			list.put(i, new Vector <SondageListener> ());
			notifyListenersNew(new SondageObj(i, owner,question,reponses,multiple,date,0));
		}catch(Exception e) {
			e.printStackTrace();
		}

		return ok;
	}

	@Override
	public synchronized boolean updateSondage(String actor,int id, ArrayList<String> ret) throws RemoteException {
		boolean ok = false;
		String[] str = resultat.get(id).split(";");
		String res = "";
		for (int i=0,j=1; j<=str.length;i+=2,j+=2) {
			if (ret.contains(str[i])) res+= str[i] + ";" + (Integer.parseInt(str[j])+1) + ";";
			else  res+= str[i] + ";" + str[j] + ";";
		}
		try {
			sgbd.modifierVotes(actor,id,res);
			resultat.put(id,res);
			notifyListeners(res, id);
			ok = true;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}

	@Override
	public List<SondageObj> getSondageFait(Utilisateur owner) throws RemoteException {
		try {
			return sgbd.getSondage(owner,1);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;	
	}


	@Override
	public List<SondageObj> getSondageNew(Utilisateur owner) throws RemoteException {
		try {
			return sgbd.getSondage(owner,0);		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public synchronized void addSondageListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException {
		Vector<SondageListener> newlistener = list.get(sondage);
		newlistener.add(listener);
		list.put(sondage,newlistener);
	}

	@Override
	public synchronized void removeSondageListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException {
		list.get(sondage).remove(listener);
	}
	
	@Override
	public synchronized void addSondageListenerNewSondage (SondageListener listener) throws java.rmi.RemoteException {
		listNew.add(listener);
	}

	@Override
	public synchronized void removeSondageListenerNewSondage  (SondageListener listener) throws java.rmi.RemoteException {
		listNew.remove(listener);
	}

	/**
	 * Methode notification d'un nouveau vote
	 * @param message le nouveau vote
	 * @param sondage le numero du sondage
	 */
	private void notifyListeners(String message, Integer sondage) {	
		Vector <SondageListener> v = list.get(sondage);
		for (Enumeration<SondageListener> e = v.elements(); e.hasMoreElements();) { 
			SondageListener listener = (SondageListener) e.nextElement();
			try {
				listener.nouveauVote(message, sondage);
			} catch(Exception re) {
				re.printStackTrace();
				v.remove(listener); 
			} 
		}
	}
	
	/**
	 * Methode de notification d'un nouveau sondage
	 * @param s le nouveau sondage
	 */
	private void notifyListenersNew(SondageObj s) {
		for (Enumeration<SondageListener> e = listNew.elements(); e.hasMoreElements();) { 
			SondageListener listener = (SondageListener) e.nextElement();
			try {
				listener.nouveauSondage(s);
			} catch(Exception re) {
				re.printStackTrace();
				listNew.remove(listener); 
			} 
		}
	}
}
