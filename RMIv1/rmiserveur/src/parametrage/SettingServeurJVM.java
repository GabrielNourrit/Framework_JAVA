package parametrage;

import java.rmi.RMISecurityManager;
/**
 * Classe de paramètrage de la Java Virtial Machine du Serveur
 */
public class SettingServeurJVM {
	/**
	 * Localisation des propriétés de sécurité définit
	 */
	private final static String SECURITY_POLICY = "ressources/security.policy";
	/**
	 * Localisation des points classes utilisée pour l'annuaire
	 */
	private final static String RMI_SEVEUR_CODEBASE = "file:${workspace_loc}/rmiserveur/bin/" ;
	
	/**
	 * Constructeur est non instanciable
	 */
	private SettingServeurJVM(){}
	
	/**
	 * Définition des propriétés utiles à la JVM
	 * @use java.security.policy
	 * @use java.rmi.server.codebase
	 */
	public static void configureProperty(){
		System.setProperty("java.security.policy",SECURITY_POLICY);
		System.setProperty("java.rmi.server.codebase",RMI_SEVEUR_CODEBASE);
	}
	
	/**
	 * Utilisation du SecurityManager
	 * @use RMISecurityManager
	 */
	public static void useSecurityManager(){
		if (System.getSecurityManager() == null) {
		   System.setSecurityManager(new RMISecurityManager());
	    }
	}
}
