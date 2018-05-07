package parametrage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesClient {
	
	private static final String URI_PROPERTIES_FILE = "ressources/parametre_Client/Client.properties";
	private static PropertiesClient singleton = null;
	private static Properties prop;
	
	/**
	 * Valeurs utilisables par l'application
	 */
	private static String adressServeur;
	
	
	
	/**
	 * Constructeur local
	 */
	private PropertiesClient(){
		load();
	}
	
	/**
	 * Lisent les paramètres du fichier et enrichissent les attributs
	 */
	private static void load(){
		prop = new Properties();
		try {
			prop.load(new FileInputStream(URI_PROPERTIES_FILE));
		} catch (IOException e) {
			System.err.println( "IOException: " + e.getMessage()) ;
			e.printStackTrace();
		}
		adressServeur = prop.getProperty("adress.server");
	}
	
	/**
	 * Méthode dui appel le construteur si il n'est pas construit
	 */
	private static void initialiseProperties(){
		if(singleton == null){
			singleton = new PropertiesClient();
		}
	}
	
	/* Methodes visibles du dehort */
	
	/**
	 * L'ardesse du serveur
	 * @return String
	 */
	public static String getAdresseServeur(){
		initialiseProperties();
		return adressServeur;
	}
	
	/**
	 * Recharge les paramètres
	 */
	public static void reLoad(){
		singleton = null;
		initialiseProperties();
	}
}
