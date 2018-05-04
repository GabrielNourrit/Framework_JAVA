package mail;

import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import BaseDeDonnee.MethodeServeur;
import BaseDeDonnee.bd.Connexionsgbd;
import fichier.Fichier;
import util.ManipulationFichier;

public class Mel implements MelInterface{

	public Mel (String url) throws RemoteException {  
		super();
	}


	@Override
	public void test() throws RemoteException{
		
		System.out.println("on est en finale !!");
		
	}

	@Override
	public String test2() throws RemoteException{
		
		return "on est en finale !!";
		
	}

	@Override
	public ArrayList<Utilisateur> getAllUsers() throws RemoteException {
		ArrayList<Utilisateur>retour = new ArrayList<Utilisateur>();
		//chargement dans la bdd :
		Utilisateur u1 = new Utilisateur("guevarat","guevara","thomas","connard");
		Utilisateur u2 = new Utilisateur("nourritg","nourrit","gabriel","Pute");
		
		retour.add(u1);
		retour.add(u2);
		
		return retour;
	}


	@Override
	public void saveMessage(Utilisateur u, String message) throws RemoteException{
		
		/*
		 * Récupéré de la base de donnée l'id du message
		 * 
		 */
		int nextval = 4;
		
		/* -------------------------------------------------*/
		
		
		
		File JulesLaPuteAliasChefDeProjet = new File("stockage/"+u.getLogin());
		if(!JulesLaPuteAliasChefDeProjet.exists()){
				JulesLaPuteAliasChefDeProjet.mkdirs();
		}
		String path = "stockage/" + u.getLogin()+"/"+ nextval;
		new ManipulationFichier().sauverFichier(path, message);
	}
	
	
	
	
	
	
	/*public boolean verifierMdp(String login, String mdp) throws RemoteException, ClassNotFoundException, SQLException {
		int n = 0;
		
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from utilisateurs where login='"+ login +"' and motDePasse='" + mdp + "'");
		if (rs.next()) n = rs.getInt(1);
		System.out.println(n);
		csgbd.closeConnexionsgbd(conn);
		if(n == 1) return true;
		return false;
	}*/
}
