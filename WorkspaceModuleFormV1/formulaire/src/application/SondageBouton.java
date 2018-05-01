package application;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SondageBouton extends Composition{
	//private ProgressBar p;
	private Button creerSondage;
	
	
	public SondageBouton(){
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	@Override
	protected void genererSousComposant() {
		this.form = new VBox();
		this.creerSondage=new Button("Sondage");
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.creerSondage.setOnAction(event ->{
		
						ScrollPane sp = new ScrollPane();
						SondageCreation sc = new SondageCreation();
						Stage nouveauStage;
						nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						sp.setContent(sc);
						sc.setAlignment(Pos.CENTER);
						sp.setFitToWidth(true);
						sp.setFitToHeight(true);
						Scene scene = new Scene(sp, 200, 250);
						nouveauStage.setScene(scene);

		});
	}

	@Override
	protected void layoutDefaultParametre() {
		form.getChildren().add(creerSondage);
		form.setMaxSize(120, 100);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);	
	}
}