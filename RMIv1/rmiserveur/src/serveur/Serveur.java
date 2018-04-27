package serveur;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

import connexion.Connexion;
 
public class Serveur {
	   public static void main (String[] argv) {
		   try {
			   java.rmi.registry.LocateRegistry.createRegistry(1099);
			   Addition Hello = new Addition();		
			   Connexion c = new Connexion();
			   Naming.rebind("rmi://localhost/ABC", Hello);
			   Naming.rebind("rmi://localhost/c", c);
			   System.out.println("Addition Server is ready.");
			   }catch (Exception e) {
				   System.out.println("Addition Server failed: " + e);
				}
		   }
}
