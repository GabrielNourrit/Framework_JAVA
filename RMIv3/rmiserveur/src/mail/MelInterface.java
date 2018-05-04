package mail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import BaseDeDonnee.MethodeServeur;

public interface MelInterface extends Remote, MethodeServeur{

	public void test() throws RemoteException;
}
