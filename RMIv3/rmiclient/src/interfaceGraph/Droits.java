package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;

import BaseDeDonnee.gestionUtilisateur.TypesInterface;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Type;

public class Droits extends Formulaire {
	
	private ChoiceBox<Type> typeComboBox;
	private HBox formCreate;
	private HBox formSelect;
	private VBox form;
	private Button buttonCreate;
	private Button buttonDelete;
	private Button buttonValidate;
	protected TextField newType;

	public Droits() {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
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
			//Si le champ text est vide alors la personne de peut pas cr�er de nouveau types
			if(Type.length() == 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attention !");
				alert.setHeaderText(null);
				alert.setContentText("Fail");
				alert.showAndWait();
			}
			else {
				//apelle fonction cr�ation type
				TypesInterface connex;
				Registry registry;
				try {
					registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
					connex = (TypesInterface) registry.lookup("Types");
					connex.addType(Type);
					refreshChoiceBox();
				} catch (RemoteException | NotBoundException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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

	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		//requete pour recup�rer tout les type d�j� existant dans une liste
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
		buttonCreate = new Button("cr�er");
		buttonDelete = new Button("supprimer");
		buttonValidate = new Button("modifier");
		form = new VBox();
		formCreate = new HBox();
		formSelect = new HBox();
		typeComboBox = new ChoiceBox<>();
		refreshChoiceBox();
	}
	
	public void refreshChoiceBox() {
		TypesInterface connex;
		Registry registry;
		try {
			registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
			connex = (TypesInterface) registry.lookup("Types");
			ArrayList<Type> lesTypes = (ArrayList<Type>) connex.getAllTypes();
			typeComboBox.setItems(FXCollections.observableArrayList(lesTypes));
		} catch (RemoteException | NotBoundException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
