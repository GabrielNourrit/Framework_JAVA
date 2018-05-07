package rmiserveurtest.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fichier.Groupe;


public class testGroupe {

	private Groupe groupe;
	@Before
	public void init(){
		groupe = new Groupe(123,"monLibelle");
	}
	
	@Test
	public void test_getIdGr() {
		assertEquals(groupe.getIdGr(), 123);
		assertNotEquals(groupe.getIdGr(), 152);
	}
	
	@Test
	public void test_toString() {
		assertEquals(groupe.toString(), "monLibelle");
		assertNotEquals(groupe.toString(), "toto");
	}
}
