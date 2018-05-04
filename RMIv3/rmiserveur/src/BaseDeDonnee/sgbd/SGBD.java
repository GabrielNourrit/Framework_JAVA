package BaseDeDonnee.sgbd;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import BaseDeDonnee.connexion.ConnexionBase;
import util.Utilisateur;

public abstract class SGBD {
	
	protected Connection conn;
	protected Statement stmt;
	
	protected SGBD() throws RemoteException {
		this.creeSGBD();
	}

	protected abstract ConnexionBase creeSGBD() throws RemoteException;
	
	
	
	public boolean verifierMdp(String login, String mdp) throws ClassNotFoundException, RemoteException, SQLException {
		return false;
	}
	
	/**
	 * Cr�ation d'un utilisateur avec les deux informations principales et obligatoires (login et mot de passe et type).
	 * @param login
	 * @param mdp
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,String type) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Cr�ation d'un utilisateur avec les infos principale + nom et pr�nom
	 * @param login
	 * @param mdp
	 * @param nom
	 * @param prenom
	 * @param type
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void creaUse (String login,String mdp,String nom,String prenom,String type) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de rentrer un utilisateur puis un attribut que l'on veut modifier et enfin la valeur que l'attribut doit prendre
	 * @param loginUse
	 * @param attribut
	 * @param valeur
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void modifAttriUser(String loginUse, String attribut,String valeur) throws ClassNotFoundException, SQLException {
	}
	/**
	 * Fonction permettant de modifier le mot de passe d'un utulisateur donn�e
	 * @param mdp
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifMdp(String mdp, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le nom d'un utilisateur donn�e
	 * @param nom
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifNom(String nom, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le prenom d'un utilisateur donn�e
	 * @param prenom
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifPrenom(String prenom, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier le type d'un utilisateur donn�e
	 * @param type
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifType(String type, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Fonction permettant de modifier la description d'un utilisateur donn�e
	 * @param description
	 * @param loginUse
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void modifDescri(String description, String loginUse) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Permet de supprimer un utilisateur donn�e
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprUse (String user) throws ClassNotFoundException, SQLException {
	}
	
	/**
	 * Permet de changer l'etat d'un utilisateur en supprimer
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void supprUseEtat (String user) throws ClassNotFoundException, SQLException {
	}
	
	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException{ return null; }
}
