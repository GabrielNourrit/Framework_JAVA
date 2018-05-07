package rmiserveurtest.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fichier.Fichier;

public class testFichier {

	private Fichier fichier;
	
	@Before
	public void init(){
		fichier = new Fichier(1,"monNom","toto/test/lolo");
	}
	
	@Test
	public void test_getFileLink() {
		assertEquals(fichier.getFileLink(), "toto/test/lolo");
	}
	
	@Test
	public void test_getidFic(){
		assertEquals(fichier.getidFic(),1);
	}

	@Test
	public void test_getNom(){
		assertEquals(fichier.getNom(),"monNom");
	}
	
	@Test
	public void test_toString(){
		assertEquals(fichier.toString(),"monNom");
	}
}
