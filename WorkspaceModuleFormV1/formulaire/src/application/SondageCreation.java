package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SondageCreation extends Composition{
	private Label titre1;
	private Label titre2;
	private TextField question;
	private ArrayList<TextField> choix;
	private Button nouveau;
	private BoutonChoix multiple;
	private DatePicker date;
	private Button ok;

	public SondageCreation() {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	@Override
	protected void genererSousComposant() {
		this.form = new VBox();
		this.titre1 = new Label("Question :");
		this.question = new TextField();
		this.titre2 = new Label("Réponses :");
		this.nouveau = new Button("ajouter choix");
		this.choix = new ArrayList<TextField>();
		this.multiple = new BoutonChoix("multiple ?");
		this.date = new DatePicker();
		this.ok = new Button("ok");
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.nouveau.setOnAction(event ->{
			TextField a = new TextField();
			this.choix.add(a);
			this.form.getChildren().removeAll(nouveau,multiple,date,ok);
			this.form.getChildren().addAll(a,nouveau,multiple,date,ok);
});
	}

	@Override
	protected void layoutDefaultParametre() {
		form.getChildren().addAll(titre1,question,titre2,nouveau,multiple,date,ok);
		for(TextField a : choix) {
		form.getChildren().add(a);
		}
		
		form.setMaxSize(120, 100);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);	
	}

}
