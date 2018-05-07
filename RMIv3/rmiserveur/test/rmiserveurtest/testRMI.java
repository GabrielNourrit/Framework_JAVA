package rmiserveurtest;

import static org.junit.Assert.*;

import java.rmi.Remote;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class testRMI {

	protected final TestRegistry registry = new TestRegistry(); 
	
	@Before
	public void init(){
		registry.clear();
	}
	
	@Test
	public void testLookup() throws Exception {
		String name = "testLookup";
		Remote rem = Mockito.mock(Remote.class);
		registry.put(name,rem);
		Remote res = registry.lookup(name); 
		assertSame("Erreur :", rem,res);
	}

	@Test 
	 public void testUnbind() throws Exception { 
	 	String name = "testUnbind"; 
	 	Remote rem = Mockito.mock(Remote.class); 
	 	registry.put(name, rem); 
	 	registry.unbind(name);
	 	assertFalse(registry.containsKey(name));
		assertFalse(registry.containsValue(rem));
	 } 
	  
	 @Test 
	 public void testBind() throws Exception { 
		 String name = "testBind"; 
		 Remote rem = Mockito.mock(Remote.class); 
		 registry.bind(name, rem);
		 assertTrue(registry.containsKey(name));
		 assertTrue(registry.containsValue(rem));
	 } 
	  
	 @Test 
	 public void testRebind() throws Exception { 
		 String name = "testRebind"; 
		 Remote rem = Mockito.mock(Remote.class); 
		 registry.rebind(name, rem); 
		 assertTrue(registry.containsKey(name));
		 assertTrue(registry.containsValue(rem));
	 } 
}
