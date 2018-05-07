package rmiserveurtest.object;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fichier.Fichier;

public class testFichier {

	private Fichier fichier;
	
	private String cheminFile = "toto/test/lolo";
	private String nomFile = "monNom";
	private int numeroFile = 1;
	
	@Before
	public void init(){
		fichier = new Fichier(numeroFile,nomFile,cheminFile);
	}
	
	@Test
	public void test_Constructeur(){
		assertNotNull(fichier);
	}
	
	@Test
	public void test_getFileLink() {
		assertEquals(fichier.getFileLink(), cheminFile);
		assertNotEquals(fichier.getFileLink(), "lala/toto");
	}
	
	@Test
	public void test_getidFic(){
		assertEquals(fichier.getidFic(),numeroFile);
		assertNotEquals(fichier.getidFic(),5);
	}

	@Test
	public void test_getNom(){
		assertEquals(fichier.getNom(),nomFile);
		assertNotEquals(fichier.getNom(), "fauxNom");
	}
	
	@Test
	public void test_toString(){
		assertEquals(fichier.toString(),nomFile);
		assertNotEquals(fichier.toString(),"fauxNom");
	}
}
