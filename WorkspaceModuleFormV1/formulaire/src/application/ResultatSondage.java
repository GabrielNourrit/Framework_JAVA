package application;

import java.util.ArrayList;

public class ResultatSondage extends Composition{
//private itemResultat i;//un label, une barre et le nombre de vote associé
private VBox l_item;
	private Label nbVote;
private Label jourRestant;
//other
private int total;
private ArrayList<Integer> dataS;
	
	/**
	 * on travail avec des chaines de caractères de type : "14;13;12;11"
	 * @param s la chaine de caractere provenant de la bdd à parser
	 * @return un tableau d'entier facile à exploiter !
	 */
	private ArrayList<Integer> parser(String s){
		String[] res = s.split(";");
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		for(String nombre : res) {
			ret.add(Integer.parseInt(nombre));
		}
		
		return ret;
	}
	
	public ResultatSondage{
		/*récupération des infos via le serveur*/
		//TO-DO
		String informations="2;4;2;3";
		
		
		
		/*Traitement*/
		this.dataS=parser(informations);
		this.total=0;
		for(int i : dataS){
			this.total +=i;
		}
		genererSousComposant();
	}
	
	@Override
	protected void genererSousComposant() {
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		
	}

	@Override
	protected void layoutDefaultParametre() {
		
	}

}
