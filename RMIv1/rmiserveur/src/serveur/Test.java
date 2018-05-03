package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import connexion.Connexion;
import connexion.MethodeServeur;
import util.ManipulationFichier;

public class Test {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		// TODO Auto-generated method stub
		ArrayList<MethodeServeur> l = new ArrayList<>();
		l.add(new Connexion("Connexion"));
		Serveur s = new Serveur(l);
		/*ManipulationFichier.sauverFichier("fic1.txt", "HELLO§",true);
		ManipulationFichier.sauverFichier("fic1.txt", "Jule§",true);
		ManipulationFichier.sauverFichier("fic1.txt", "ficue§",true);
		ManipulationFichier.sauverFichier("fic1.txt", "popo§",true);
		ManipulationFichier.sauverFichier("fic1.txt", "tolouctou§",true);
		ManipulationFichier.sauverFichier("fic1.txt", "boloyagnis§",true);
		
		ManipulationFichier.refreshFichier("fic1.txt", 10);*/
	}

}
