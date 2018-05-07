package rmiserveurtest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import BaseDeDonnee.sgbd.SGBD;
import BaseDeDonnee.sgbd.SGBDMySQL;
import BaseDeDonnee.sgbd.SGBDOracle;
import parametrage.PropertiesServeur;

public class StructSGBD {
	
	@Test
	public void testMapSGBD()throws Exception{
		Class<SGBDOracle> oracle = SGBDOracle.class;
		Class<SGBDMySQL> mysql = SGBDMySQL.class;
		assertEquals(oracle,SGBD.determine("").getClass());
		assertEquals(oracle,SGBD.determine("oRacle").getClass());
		
		assertEquals(oracle,SGBD.determine("mysql").getClass());		
		assertNotEquals(mysql, SGBD.determine("mysql").getClass());
		
		SGBD.addTypeSGBD("mysql", new SGBDMySQL());
		
		assertNotEquals(oracle, SGBD.determine("mysql").getClass());
		assertEquals(mysql,SGBD.determine("mysqL").getClass());
	}
	
	@Test
	public void lectureDocProperties() throws Exception {
		assertEquals("Oracle",PropertiesServeur.getTypeSGBD());
	}
	
	@Test
	public void testIdentiqueTAB(){
		List<String> arrayTestTRUE = Arrays.asList("MYSQL", "ORACLE");
		List<String> arrayPlus1 = Arrays.asList("MYSQL", "ORACLE", "ORACLE");
		List<String> arrayMoins1 = Arrays.asList("ORACLE");
		List<String> arrayVide = Arrays.asList();
		
		List<String> listeBases = SGBD.printListBaseNom();
		
		assertThat(listeBases, CoreMatchers.is(arrayTestTRUE));
		assertThat(listeBases, CoreMatchers.not(arrayPlus1));
		assertThat(listeBases, CoreMatchers.not(arrayMoins1));
		assertThat(listeBases, CoreMatchers.not(arrayVide));
		assertThat(listeBases, CoreMatchers.hasItems("MYSQL"));
		assertThat(listeBases, CoreMatchers.hasItems("ORACLE"));
		assertEquals(listeBases.size(), 2);
	}

}
