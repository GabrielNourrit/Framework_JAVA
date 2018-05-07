package rmiserveurtest.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fichier.Groupe;


public class testGroupe {

	private Groupe groupe;
	private String libelle = "monLibelle";
	private int numeroGroupe = 123;
	
	@Before
	public void init(){
		groupe = new Groupe(numeroGroupe,libelle);
	}
	
	@Test
	public void test_Constructeur(){
		assertNotNull(groupe);
	}
	
	@Test
	public void test_getIdGr() {
		assertEquals(groupe.getIdGr(), numeroGroupe);
		assertNotEquals(groupe.getIdGr(), 152);
	}
	
	@Test
	public void test_toString() {
		assertEquals(groupe.toString(), libelle);
		assertNotEquals(groupe.toString(), "toto");
	}
}
