package mail;

import java.rmi.Remote;
import java.util.ArrayList;

import interfaceGraph.mail.melCell;

public interface MelInterface extends Remote{

	public ArrayList<melCell> melUnUser(int id);
	
}
