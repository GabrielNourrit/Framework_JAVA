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
	private static String logoImage;
	private static String cssDefaut;
	private static String localisationCss;
	private static int port;
	
	
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
		adressServeur = prop.getProperty("adresse.serveur.defaut");
		logoImage = prop.getProperty("logo.application");
		cssDefaut = prop.getProperty("default.css");
		localisationCss = prop.getProperty("localisation.css.dossiers");
		try{
			port = Integer.parseInt(prop.getProperty("port.utilise"));
		}catch(NumberFormatException e){
			// Le port par defaut
			port = 1099;
		}
		
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
	 * L'url de l'localisation du dossier fichier css
	 * @return String
	 */
	public static String getLocalisationCss(){
		initialiseProperties();
		return localisationCss;
	}
	
	/**
	 * le Css par defaut
	 * @return String
	 */
	public static String getCssDefaut(){
		initialiseProperties();
		return cssDefaut;
	}
	
	/**
	 * Le numero de port
	 * @return String
	 */
	public static int getNumeroPort(){
		initialiseProperties();
		return port;
	}
	
	/**
	 * Le logo de l'application
	 * @return String
	 */
	public static String getLogoImage(){
		initialiseProperties();
		return logoImage;
	}
	
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
