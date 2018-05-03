package BaseDeDonnee.bd;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @description : Permet de creer un objet SGBD permettant le lien avec notre BDD
 */	
public class Connexionsgbd {
	private Properties prop;
	private String jdbcDriver;
	private String dbUrl;
	private String username;
	private String password;


    /**
     * @description : Permet de creer un objet SGBD permettant le lien avec notre BDD
     */	
	public Connexionsgbd(String configurationFile) throws ClassNotFoundException{
             //recupération des propriétés paramétrées
			databaseAccessProperties(configurationFile);
			// Chargement des drivers de la base
			Class.forName(jdbcDriver) ;		
	}
	
	/**
     * @description : Permet de parser le fichier BD.properties afin d'en extraire les informations
     * @param propertiesFile : le fichier de propriétés à analyser
	 * @return 
     * @require FileNotFoundException : exist(propertiesFile)
     * @require IOException : Nous devons avoir accès à la lecture du fichier de propriétés
     */
	private void databaseAccessProperties(String propertiesFile) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(propertiesFile));
		} catch (FileNotFoundException e) {
			System.err.println( "FileNotFoundException: " + e.getMessage()) ;
			e.printStackTrace();
			return;
		} catch (IOException e) {
			System.err.println( "IOException: " + e.getMessage()) ;
			e.printStackTrace();
			return;
		}
		jdbcDriver = prop.getProperty("jdbc.driver");
        dbUrl = prop.getProperty("database.url");
        username = prop.getProperty("database.username");
        password = prop.getProperty("database.password");
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