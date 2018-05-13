package interfaceGraph.sondage;

import interfaceGraph.Composition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Fenetre;
import util.Utilisateur;

public class SondageBouton extends Composition{
	//private ProgressBar p;
	private Button creerSondage;
	private Utilisateur u;
	
	public SondageBouton(Utilisateur u){
		this.u = u;
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	@Override
	protected void genererSousComposant() {
		this.comp = new VBox();
		this.creerSondage=new Button("Sondage");
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.creerSondage.setOnAction(event ->{
		
						ScrollPane sp = new ScrollPane();
						
						VBox sc = new SondageCreation(u);
						
						
						Stage nouveauStage = new Stage();
						//nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						sc.setAlignment(Pos.CENTER);
						sp.setContent(sc);
						sp.setFitToWidth(true);
						sp.setFitToHeight(true);
						Scene scene = new Scene(sp, 300, 250);
						Fenetre.paramStage(nouveauStage, scene);
						nouveauStage.show();

		});
	}

	@Override
	protected void layoutDefaultParametre() {
		comp.getChildren().add(creerSondage);
		comp.setMaxSize(120, 100);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(comp);	
	}
}
