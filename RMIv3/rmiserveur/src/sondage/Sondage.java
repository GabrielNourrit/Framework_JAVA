package sondage;

import java.io.File;
import java.rmi.RemoteException;
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

public class Sondage  implements SondageInterface{
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

	//int id, String owner, boolean fait, String question, String reponses,boolean multiple, String date, int total
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
			System.out.println(i);
			notifyListenersNew(new SondageObj(i, owner,question,reponses,multiple,date,0));
		}catch(Exception e) {
			e.printStackTrace();
		}

		return ok;
	}

	public synchronized boolean updateSondage(String actor,int id, ArrayList<String> ret) throws RemoteException {
		System.out.println(id);
		boolean ok = false;
		System.out.println(resultat.get(id));
		String[] str = resultat.get(id).split(";");
		String res = "";
		for (int i=0,j=1; j<=str.length;i+=2,j+=2) {
			if (ret.contains(str[i])) res+= str[i] + ";" + (Integer.parseInt(str[j])+1) + ";";
			else  res+= str[i] + ";" + str[j] + ";";
		}
		try {
			System.out.println(actor + " " + id);
			sgbd.modifierVotes(actor,id,res);
			resultat.put(id,res);
			notifyListeners(res, id);
			ok = true;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(res);
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
			System.out.println( sgbd.getSondage(owner,0));
			return sgbd.getSondage(owner,0);		
		}catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public synchronized void addTchatListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException {
		Vector<SondageListener> newlistener = list.get(sondage);
		newlistener.add(listener);
		list.put(sondage,newlistener);
	}

	public synchronized void removeTchatListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException {
		list.get(sondage).remove(listener);
	}
	
	public synchronized void addTchatListenerNewSondage (SondageListener listener) throws java.rmi.RemoteException {
		listNew.add(listener);
	}

	public synchronized void removeTchatListenerNewSondage  (SondageListener listener) throws java.rmi.RemoteException {
		listNew.remove(listener);
	}

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
