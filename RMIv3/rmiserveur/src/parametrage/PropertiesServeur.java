package parametrage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesServeur {
	
	private static final String URI_PROPERTIES_FILE = "ressources/parametre_Serveur/Serveur.properties";
	private static PropertiesServeur singleton = null;
	private static Properties prop;
	
	/**
	 * Valeurs utilisables par l'application
	 */
	private static String portServeur;
	private static String typeSGBD;
	private static String stockageTchat;
	private static String stockageFichiers;
	private static String stockageMail;
	
	
	/**
	 * Constructeur local
	 */
	private PropertiesServeur(){
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
		portServeur = prop.getProperty("numero.port");
		typeSGBD = prop.getProperty("type.sgbd.serveur");
		stockageTchat = prop.getProperty("stockage.tchat");
		stockageFichiers = prop.getProperty("stockage.fichiers");
		stockageMail = prop.getProperty("stockage.mail");
	}
	
	/**
	 * Méthode dui appel le construteur si il n'est pas construit
	 */
	private static void initialiseProperties(){
		if(singleton == null){
			singleton = new PropertiesServeur();
		}
	}
	
	/* Methodes visibles du dehort */
	
	/**
	 * La localisation de l'adresse de stockage des mails
	 * @return String
	 */
	public static String getStockageMail(){
		initialiseProperties();
		return stockageMail;
	}
	
	/**
	 * La localisation de l'adresse de stockage des fichiers
	 * @return String
	 */
	public static String getStockageFichiers(){
		initialiseProperties();
		return stockageFichiers;
	}
	
	/**
	 * La localisation de l'adresse de stockage des tchats
	 * @return String
	 */
	public static String getStockageTchat(){
		initialiseProperties();
		return stockageTchat;
	}
	/**
	 * Le SGBD définit dans les paramètres
	 * @return String
	 */
	public static String getTypeSGBD(){
		initialiseProperties();
		return typeSGBD;
	}
	
	/**
	 * Le numéro de port utilisé par le serveur
	 * @return Interger
	 */
	public static int getPortServeur() throws NumberFormatException{
		initialiseProperties();
		return Integer.parseInt(portServeur);
	}
	
	/**
	 * Recharge les paramètres
	 */
	public static void reLoad(){
		singleton = null;
		initialiseProperties();
	}
}
