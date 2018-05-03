package connexion;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BD.Connexionsgbd;

public class Utilisateurs extends MethodeServeur  implements UtilisateursInterface {
	
	public Utilisateurs(String n) throws RemoteException {
		super(n);
		
	}

	public List<Utilisateur> getUsers() throws RemoteException, ClassNotFoundException, SQLException {
		List<Utilisateur> lesUser = new ArrayList<>();
		Connexionsgbd csgbd = new Connexionsgbd();
		Connection conn = csgbd.openConnexionsgbd();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from utilisateurs");
		while (rs.next()) {
			//old
			//Utilisateur user = new Utilisateur(rs.getString(4), rs.getString(2), rs.getString(3),rs.getString(5));
			
			//new
			Utilisateur user = new Utilisateur(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(5));
			
			lesUser.add(user);
		}
		csgbd.closeConnexionsgbd(conn);
		return lesUser;
	}
	
	

}
