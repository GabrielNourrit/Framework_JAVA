package mail;

import java.rmi.Remote;
import java.util.ArrayList;

import BaseDeDonnee.MethodeServeur;

public interface MelInterface extends Remote, MethodeServeur{

	public ArrayList<melCell> melUnUser(int id);
	
}
