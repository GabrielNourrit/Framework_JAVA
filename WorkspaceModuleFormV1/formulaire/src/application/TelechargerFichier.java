package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TelechargerFichier extends Formulaire{
	private Group form;
	private Button button;
	private Label label;
	private ListView<String> list; 
	
public TelechargerFichier(Group pere, int X, int Y) {
	genererSousComposant();
	ecouteurDefaultAction();
	layoutDefaultParametre();
	this.form.getChildren().addAll(button, label,list);
	this.getStyleForm().setLayoutX(X);
	this.getStyleForm().setLayoutY(Y);
	pere.getChildren().add(this.getStyleForm());
}
	
	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		button = new Button("Download");
		label = new Label("Filename");
		form = new Group();
		list = new ListView();
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		ObservableList<String> items =FXCollections.observableArrayList (  
			    "Single", "Double", "Suite", "Family App"); 
		list.setItems(items);
		button.setOnAction(event -> {
			System.out.println("Super!");
		});
	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub

		list.setPrefWidth(200);  
		list.setPrefHeight(300); 
		this.button.setLayoutX(200);
		this.button.setLayoutY(50);
		this.label.setLayoutX(200);
		this.label.setLayoutY(20);
		this.list.setLayoutX(-50);
		this.list.setLayoutY(-100);
		
	}
	public Group getStyleForm(){
		return this.form;
	}
}
