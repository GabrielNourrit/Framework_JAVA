package interfaceGraph;

import java.io.File;
import java.io.IOException;
import java.rmi.registry.Registry;

import fichier.GestionFichierInterface;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.TransformerFichier;

public class PoseFichier extends VBox {
	private VBox form;
	private FileChooser filechooser;
	private Button button;
	private Label label;
	private Stage stage;
	
	public PoseFichier() {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}


	protected void genererSousComposant() {
		button = new Button("upload");
		label = new Label();
		form = new VBox();	
	}

	protected void ecouteurDefaultAction() {
		button.setOnAction(event -> {
			filechooser = new FileChooser();
			File chosenFile = filechooser.showOpenDialog(stage);
			if(chosenFile != null){
				System.out.println(chosenFile.getAbsolutePath());
				label.setText(chosenFile.getAbsolutePath());
            }else {
                label.setText("please choose a file!");
            }
			try {				
				byte[] b = TransformerFichier.fileToByte(chosenFile.getAbsolutePath());
				Registry registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
				GestionFichierInterface connex = (GestionFichierInterface) registry.lookup("Fichier");
				connex.upload(chosenFile.getName(), b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//String filename = filechooser.getTitle();
		});
	}

	protected void layoutDefaultParametre() {
		this.form.getChildren().addAll(button, label);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}
	
	public VBox getStyleForm(){
		return this.form;
	}
}
