package BD;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
* @description : Permet de parser le fichier BD.properties afin d'en extraire les informations
*/
public class DatabaseAccessProperties {
	private Properties prop = new Properties();
	private String jdbcDriver;
	private String dbUrl;
	private String username, password;
    /**
     * @description : Permet de parser le fichier BD.properties afin d'en extraire les informations
     * @param propertiesFile : le fichier de propriétés à analyser
     * @require FileNotFoundException : exist(propertiesFile)
     * @require IOException : Nous devons avoir accès à la lecture du fichier de propriétés
     */
	public DatabaseAccessProperties(String propertiesFile) {
		try {
			prop = new Properties();
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
	
	public String getJdbcDriver() { return jdbcDriver; }
	public String getDatabaseUrl() { return dbUrl; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
}