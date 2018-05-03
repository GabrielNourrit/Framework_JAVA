package parametrage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesServeur {
	
	private static final String URI_PROPERTIES_FILE = "ressources/Serveur.properties";
	private static PropertiesServeur singleton = null;
	private static Properties prop;
	
	/**
	 * Valeurs utilisables par l'application
	 */
	private static String portServeur;
	private static String TypeSGBD;
	
	
	
	/**
	 * Constructeur local
	 */
	private PropertiesServeur(){
		load();
	}
	
	/**
	 * Lisent les param�tres du fichier et enrichissent les attributs
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
		TypeSGBD = prop.getProperty("type.sgbd.serveur");
	}
	
	/**
	 * M�thode dui appel le construteur si il n'est pas construit
	 */
	private static void initialiseProperties(){
		if(singleton == null){
			new PropertiesServeur();
		}
	}
	
	/* Methodes visibles du dehort */
	
	/**
	 * Le SGBD d�finit dans les param�tres
	 * @return String
	 */
	public static String getTypeSGBD(){
		initialiseProperties();
		return TypeSGBD;
	}
	
	/**
	 * Le num�ro de port utilis� par le serveur
	 * @return Interger
	 */
	public static Integer getPortServeur() throws NumberFormatException{
		initialiseProperties();
		return Integer.parseInt(portServeur);
	}
	
	/**
	 * Recharge les param�tres
	 */
	public static void reLoad(){
		singleton = null;
		initialiseProperties();
	}
}
