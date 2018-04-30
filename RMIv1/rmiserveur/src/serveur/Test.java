package serveur;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import connexion.Connexion;
import connexion.MethodeServeur;

public class Test {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		// TODO Auto-generated method stub
		ArrayList<MethodeServeur> l = new ArrayList<>();
		l.add(new Connexion("Connexion"));
		Serveur s = new Serveur(l);
	}

}
