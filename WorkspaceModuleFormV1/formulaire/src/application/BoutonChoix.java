package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;

public class BoutonChoix extends HBox{
	private Label nom;
	private RadioButton bool;
	private Boolean isTrue;
	protected HBox compoBC;
	
	BoutonChoix(String nom){
		this.nom = new Label(nom);
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	protected void genererSousComposant(){
		this.bool = new RadioButton();
		this.compoBC = new HBox();
		this.isTrue=false;
	}
	
	protected void ecouteurDefaultAction() {
		this.bool.setOnAction(event ->{
			this.isTrue = !(this.isTrue);
		});
	}
	
	protected void layoutDefaultParametre() {
		compoBC.getChildren().addAll(nom,bool);
		compoBC.setMaxSize(120, 100);
		compoBC.setSpacing(3);
		compoBC.setAlignment(Pos.CENTER);
		this.getChildren().add(compoBC);
	}
	
	public Boolean checked() {
		return new Boolean(isTrue);
	}
}
