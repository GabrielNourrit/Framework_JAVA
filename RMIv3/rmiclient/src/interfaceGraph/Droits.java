package interfaceGraph;

import java.awt.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Droits extends VBox {
	
	private ComboBox typeComboBox;
	private HBox formCreate;
	private HBox formSelect;
	private VBox form;
	private Button buttonCreate;
	private Button buttonDelete;
	private Button buttonValidate;
	protected TextField newType;

	public Droits() {
		iniComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	protected void iniComposant() {
		typeComboBox = new ComboBox();
		//requete pour recupérer tout les type déjà existant dans une liste
		//parcourir la liste pour integrer a chaque fois l'element a la combo box
		/*List l = new List();
		l.add("option 1");
		l.add("option 2");
		l.add("option 3");
		for(String str:l)
		{
			typeComboBox.getItems().add(str);
		}*/
	
		newType = new TextField();
		buttonCreate = new Button("créer");
		buttonDelete = new Button("supprimer");
		buttonValidate = new Button("modifier");
		form = new VBox();
		formCreate = new HBox();
		formSelect = new HBox();
	}
	
	protected void layoutDefaultParametre() {
		formCreate.getChildren().addAll(newType,buttonCreate);
		formSelect.getChildren().addAll(typeComboBox,buttonDelete);
		form.getChildren().addAll(formCreate,formSelect,buttonValidate);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}

	public VBox getStyleForm(){
		return this.form;
		
	}
	
	protected void ecouteurDefaultAction() {
		buttonCreate.setOnAction(event -> {
			String Type = newType.getText();
			//Si le champ text est vide alors la personne de peut pas créer de nouveau types
			if(Type.length() == 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attention !");
				alert.setHeaderText(null);
				alert.setContentText("Fail");
				alert.showAndWait();
			}
			else {
				//apelle fonction création type
			}
			
		});
		buttonDelete.setOnAction(event -> {
			String Type = typeComboBox.getValue().toString();
			//si aucun des types n'est selectionner alors on ne peut pas le supprimer
			if(Type.equals("")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attention !");
				alert.setHeaderText(null);
				alert.setContentText("Fail");
				alert.showAndWait();
			}
			else {
				//apelle fonction suppresion type
			}
		});
		buttonValidate.setOnAction(event -> {
			
		});
		
	}
	
	
	
	
}
