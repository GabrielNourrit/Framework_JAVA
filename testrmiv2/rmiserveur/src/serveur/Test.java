package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import connexion.Connexion;
import connexion.MethodeServeur;
import fichier.Fichier;
import parametrage.SettingServeurJVM;
import tchat.Tchat;

public class Test {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		SettingServeurJVM.configureProperty();
		SettingServeurJVM.useSecurityManager();
		ArrayList<MethodeServeur> l = new ArrayList<>();
		l.add(new Connexion("Connexion"));
		l.add(new Tchat("Tchat"));
		l.add(new Fichier("Fichier"));
		new Serveur(l);
	}
}
