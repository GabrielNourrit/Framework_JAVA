package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @description : Permet de creer un objet SGBD permettant le lien avec notre BDD
 */	
public class Connexionsgbd {
	private static final String configurationFile = "src/BD.properties";
	private final String jdbcDriver;
	private final String dbUrl;
	private final String username;
	private final String password;
	private final DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);


    /**
     * @description : Permet de creer un objet SGBD permettant le lien avec notre BDD
     */	
	public Connexionsgbd() throws ClassNotFoundException{
                        //recupération des propriétés paramétrées
			this.jdbcDriver= dap.getJdbcDriver();
			this.dbUrl= dap.getDatabaseUrl();
			username= dap.getUsername();
			this.password= dap.getPassword();
			// Chargement des drivers de la base
			Class.forName(jdbcDriver) ;
			
	}

    /**
     * @description : Permet d'ouvrir une connexion au SGBD
     * @return : une connexion au SGBD
     */
	public Connection openConnexionsgbd() throws SQLException {
		Connection conn = DriverManager.getConnection(dbUrl, username, password);
		return conn;
	}

    /**
     * @param conn : Une connexion ouverte au SGBD que l'on souhaite fermer 
     * @description : Permet de fermer une connexion au SGBD
     */
	public static void closeConnexionsgbd(Connection conn) throws SQLException {
		SQLWarningsExceptions.printWarnings(conn);
		conn.close() ;
	}
}