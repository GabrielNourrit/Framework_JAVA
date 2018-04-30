package application;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
public class PoseFichier extends Formulaire{
	private Group form;
	private FileChooser filechooser;
	private Button button;
	private Label label;
	private Stage stage;
	
	public PoseFichier(Group pere, int X, int Y) {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
		this.form.getChildren().addAll(button, label);
		this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);
		pere.getChildren().add(this.getStyleForm());

	}


	@SuppressWarnings("deprecation")
	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		button = new Button("upload");
		label = new Label();
		form = new Group();
		
		
		
		
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub 
		button.setOnAction(event -> {
			filechooser = new FileChooser();
			File chosenFile = filechooser.showOpenDialog(stage);
			if(chosenFile != null){
				System.out.println(chosenFile.getAbsolutePath());
				label.setText(chosenFile.getAbsolutePath());
            }else {
                label.setText("please choose a file!");
            }
			String filename = filechooser.getTitle();
		});
	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		button.setLayoutX(100);
		label.setLayoutY(60);
	}
	
	public Group getStyleForm(){
		return this.form;
	}
}
