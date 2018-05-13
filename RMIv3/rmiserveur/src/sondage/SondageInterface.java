package sondage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.MethodeServeur;
import util.Utilisateur;

public interface SondageInterface extends Remote,  MethodeServeur {
	/**
	 * Methode pour creer un sondage
	 * @param owner le createur
	 * @param question la question du sondage
	 * @param reponses les reponses au sondage de la forme rep1;nbvote1;rep2;nbvote2...
	 * @param multiple si le sondage est a choix multiple
	 * @param date la date du sondage
	 * @return un booleen
	 * @throws RemoteException
	 */
	public boolean creerSondage(String owner, String question, String reponses,boolean multiple, String date) throws RemoteException;
	
	/**
	 * Recupere tous les sondages fait par un utilisateur
	 * @param owner l'utilisateur
	 * @return une liste de sondage
	 * @throws RemoteException
	 */
	public List<SondageObj> getSondageFait(Utilisateur owner) throws RemoteException;
	
	/**
	 * Recupere tous les sondages non fait par un utilisateur
	 * @param owner l'utilisateur
	 * @return une liste de sondage
	 * @throws RemoteException
	 */
	public List<SondageObj> getSondageNew(Utilisateur owner) throws RemoteException;
	
	/**
	 * Modification d'un sondage
	 * @param actor
	 * @param id
	 * @param ret
	 * @return
	 * @throws RemoteException
	 */
	public boolean updateSondage(String actor,int id, ArrayList<String> ret) throws RemoteException;
	
	/**
	 * ajouter un listener sur un sondage
	 * @param listener le listener
	 * @param sondage l'id du sondage
	 * @throws java.rmi.RemoteException
	 */
	public void addSondageListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException;
	
	/**
	 * supprimer un listener sur un sondage
	 * @param listener le listener
	 * @param sondage l'id du sondage
	 * @throws java.rmi.RemoteException
	 */
	public void removeSondageListener (SondageListener listener, Integer sondage) throws java.rmi.RemoteException;
	
	/**
	 * ajouter un listener sur les nouveaux sondages
	 * @param listener le listener
	 * @throws java.rmi.RemoteException
	 */
	public void addSondageListenerNewSondage (SondageListener listener) throws java.rmi.RemoteException;
	
	/**
	 * supprimer un listener sur les nouveaux sondages
	 * @param listener le listener
	 * @throws java.rmi.RemoteException
	 */
	public void removeSondageListenerNewSondage  (SondageListener listener) throws java.rmi.RemoteException;
}
