package interfaceGraph;

import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;

import fichier.GestionFichierInterface;
import util.Groupe;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.TransformerFichier;
import util.Utilisateur;

public class PoseFichier extends VBox {
	protected VBox form;
	protected FileChooser filechooser;
	protected Button button;
	protected Label label;
	private Stage stage;
	private Utilisateur u;
	protected ChoiceBox<Groupe> cbgroupe;
	private GestionFichierInterface connex;
	
	public PoseFichier(Utilisateur u) throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		this.u=u;
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (GestionFichierInterface) registry.lookup("Fichier");
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}


	protected void genererSousComposant() throws ClassNotFoundException, RemoteException, SQLException {
		button = new Button("upload");
		label = new Label();
		try {
			cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(u.getGroupe()));
		} catch (RemoteException | ClassNotFoundException | NotBoundException | SQLException e) {
			cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(new ArrayList<Groupe>()));
		}
		form = new VBox();	
	}

	protected void ecouteurDefaultAction() {
		button.setOnAction(event -> {
			filechooser = new FileChooser();
			File chosenFile = filechooser.showOpenDialog(stage);
			if(chosenFile != null){
				label.setText(chosenFile.getAbsolutePath());
            }else {
                label.setText("please choose a file!");
            }
			try {				
				byte[] b = TransformerFichier.fileToByte(chosenFile.getAbsolutePath());
				connex.upload(chosenFile.getName(),b,u.getLogin(),cbgroupe.getSelectionModel().getSelectedItem().getidGr());
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Erreur");
				alert.showAndWait();
			}
		});
	}

	protected void layoutDefaultParametre() {
		cbgroupe.getSelectionModel().select(0);
		this.form.getChildren().addAll(cbgroupe,button, label);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}
	
	public VBox getStyleForm(){
		return this.form;
	}
}
