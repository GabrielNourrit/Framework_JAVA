package rmiserveurtest;

import java.rmi.AccessException; 
import java.rmi.AlreadyBoundException; 
import java.rmi.NotBoundException; 
import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.rmi.registry.Registry; 
import java.util.Collection; 
import java.util.HashMap; 

public class TestRegistry extends HashMap<String,Remote> implements Registry { 
	 
	 public TestRegistry () { 
	  super(); 
	 } 
	 
	 public Remote lookup(String name) throws RemoteException, 
	   NotBoundException, AccessException { 
	  // TODO Auto-generated method stub 
	  return get(name); 
	 } 
	 
	 public void bind(String name, Remote obj) throws RemoteException, AlreadyBoundException, AccessException { 
	  if (containsKey(name)) { 
	   throw new AlreadyBoundException(name); 
	  } 
	   
	  put(name, obj); 
	 } 
	 
	 public void unbind(String name) throws RemoteException, NotBoundException, AccessException { 
	  Remote rem=remove(name); 
	  if (rem == null) { 
	   throw new NotBoundException(name); 
	  } 
	 } 
	 
	 public void rebind(String name, Remote obj) throws RemoteException, AccessException { 
	  put(name, obj); 
	 } 
	 
	 public String[] list() throws RemoteException, AccessException { 
	  Collection<String> keys=keySet(); 
	  if (keys.size() <= 0) { 
	   return new String[0]; 
	  } else { 
	   return keys.toArray(new String[keys.size()]); 
	  } 
	 } 
}
