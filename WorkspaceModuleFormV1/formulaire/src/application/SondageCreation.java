package application;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SondageCreation extends Composition{
	private Label titre1;
	private Label titre2;
	private Label titre3;
	private TextField question;
	private VBox choix;
	private Button nouveau;
	private BoutonChoix multiple;
	private DatePicker date;
	private Group questionC;
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
		this.choix = new VBox();
		this.multiple = new BoutonChoix("multiple ");
		this.titre3 = new Label("Date de cloture :");
		this.date = new DatePicker();
		this.ok = new Button("ok");
		this.questionC = new Group();
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.nouveau.setOnAction(event ->{
			this.choix.getChildren().add(new TextField());
});
	}

	@Override
	protected void layoutDefaultParametre() {
		this.question.setLayoutX(70);
		this.titre1.setLayoutY(4);
		questionC.getChildren().addAll(titre1,question);
		
		form.getChildren().addAll(questionC,titre2,choix,nouveau,multiple,titre3,date,ok);
		form.setMaxSize(120, 100);
		form.setSpacing(5);
		this.getChildren().add(form);	
	}

}
