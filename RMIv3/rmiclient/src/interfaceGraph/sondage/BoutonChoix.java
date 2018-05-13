package interfaceGraph.sondage;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

public class BoutonChoix extends HBox{
	private Label nom;
	private RadioButton bool;
	private boolean isTrue;
	protected HBox compoBC;
	
	BoutonChoix(String nom){
		this.nom = new Label(nom);
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	/**
	 * genere tout les sous Composant
	 */
	protected void genererSousComposant(){
		this.bool = new RadioButton();
		this.compoBC = new HBox();
		this.isTrue=false;
	}
	
	/**
	 * definit tout les Actions-Listeners du formulaire
	 */
	protected void ecouteurDefaultAction() {
		this.bool.setOnAction(event ->{
			this.isTrue = !(this.isTrue);
		});
	}
	
	/**
	 * definit le style par defaut du formulaire
	 */
	protected void layoutDefaultParametre() {
		compoBC.getChildren().addAll(nom,bool);
		compoBC.setMaxSize(120, 100);
		compoBC.setSpacing(3);
		compoBC.setAlignment(Pos.CENTER);
		this.getChildren().add(compoBC);
	}
	
	/**
	 * retourne si le bouton est coche ou non
	 * @return true si le bouton est coche false sinon
	 */
	public boolean isChecked() {
		return isTrue;
	}
}
