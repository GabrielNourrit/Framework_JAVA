package rmiserveurtest.object;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import util.Groupe;
import util.Utilisateur;

public class testUtilisateur {

	private List<Groupe> groupes;
	private Utilisateur utilSimple;
	private Utilisateur utilComplet;
	
	private final String LOGIN_C = "loginComplet";
	private final String NOM_C = "nomComplet";
	private final String PRENOM_C = "prenomComplet";
	private final String TYPE_C = "typeComplet";
	
	private final String LOGIN_S = "loginSimple";
	
	@Before
	public void init(){
		groupes = new ArrayList<>();
		groupes.add(new Groupe(1,"groupe1"));
		groupes.add(new Groupe(2,"groupe2"));
		utilSimple = new Utilisateur(LOGIN_S);
		//utilComplet = new Utilisateur(LOGIN_C,NOM_C,PRENOM_C,TYPE_C,groupes);
	}
	
	@Test
	public void test_Constructeur(){
		assertNotNull(utilSimple);
		assertNotNull(utilComplet);
	}
	
	@Test
	public void test_getMdp_setMdp() {	
		assertNull(utilSimple.getMdp());
		assertNull(utilComplet.getMdp());
		
		utilSimple.setMdp("toto");
		utilComplet.setMdp("complet");
		
		assertEquals(utilSimple.getMdp(),"toto");
		assertEquals(utilComplet.getMdp(),"complet");
	}
	
	@Test
	public void test_getLogin(){
		assertEquals(utilSimple.getLogin(),LOGIN_S);
		assertEquals(utilComplet.getLogin(),LOGIN_C);
	}
	
	@Test
	public void test_getNom(){
		assertNull(utilSimple.getNom());
		assertEquals(utilComplet.getNom(),NOM_C);
	}
	
	@Test
	public void test_getPrenom(){
		assertNull(utilSimple.getPrenom());
		assertEquals(utilComplet.getPrenom(),PRENOM_C);
	}
	
	@Test
	public void test_getType(){
		assertNull(utilSimple.getType());
		assertEquals(utilComplet.getType(),TYPE_C);
	}
	
	@Test
	public void test_toString(){
		assertEquals(utilSimple.toString(),LOGIN_S);
		assertEquals(utilComplet.toString(),LOGIN_C);
	}
	
	@Test
	public void test_getGroupe(){
		List<Groupe> array = Arrays.asList(new Groupe(1,"groupe1")
										  ,new Groupe(2,"groupe2"));
		assertEquals(utilComplet.getGroupe().size(),array.size());
		for(int i = 0; i<utilComplet.getGroupe().size();i++ ){
			assertEquals(utilComplet.getGroupe().get(i).toString(),array.get(i).toString());
		}
		
	}
	
	
	
}
