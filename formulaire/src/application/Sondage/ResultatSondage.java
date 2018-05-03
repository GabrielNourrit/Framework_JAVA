package application.Sondage;

import java.awt.Label;
import java.util.ArrayList;

import application.Composition;
import javafx.scene.layout.VBox;

public class ResultatSondage extends Composition{
//private itemResultat i;//un label, une barre et le nombre de vote associ�
private VBox l_item;
private Label nbVote;
private Label jourRestant;
//other
private int total;
private ArrayList<Integer> dataS;
	
	/**
	 * on travail avec des chaines de caract�res de type : "14;13;12;11"
	 * @param s la chaine de caractere provenant de la bdd � parser
	 * @return un tableau d'entier facile � exploiter !
	 */
	private ArrayList<Integer> parser(String s){
		String[] res = s.split(";");
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for(String nombre : res) {
			ret.add(Integer.parseInt(nombre));
		}
		
		return ret;
	}
	
	/*public ResultatSondage{
		//r�cup�ration des infos via le serveur
		//TO-DO
		String informations="2;4;2;3";
		
		
		
		//Traitement
		this.dataS=parser(informations);
		this.total=0;
		for(int i : dataS){
			this.total +=i;
		}
		genererSousComposant();
	}*/
	
	@Override
	protected void genererSousComposant() {
		this.nbVote = new Label(total+"0");
	}

	@Override
	protected void ecouteurDefaultAction() {
		
	}

	@Override
	protected void layoutDefaultParametre() {
		
	}

}