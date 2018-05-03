package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ManipulationFichier {
	
	public static StringBuffer chargerFichierTchat(String fichier) {
		StringBuffer s = new StringBuffer("");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			String line;
			while ((line = br.readLine()) != null) {
			  s.append(line);
			}
			br.close();          
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(s);
		return s;
	}

	
	public static void sauverFichier(String fichier, String texte, boolean b) {
		try {
			FileWriter fw = new FileWriter(new File(fichier),b);
			fw.write(texte);	
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void refreshFichier(String fichier, int nbLineSauv) {
		String s = chargerFichierTchat(fichier).toString();
		String[] r = s.split("§");
		if (nbLineSauv < r.length) {
			for (int j=r.length-nbLineSauv; j<r.length; j++) {
				sauverFichier(fichier,r[j]+"§",false);
			}
		}
	}
}