package interfaceGraph;

import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import fichier.GestionFichierInterface;
import fichier.Groupe;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.TransformerFichier;
import util.Utilisateur;

public class PoseFichier extends VBox {
	private VBox form;
	private FileChooser filechooser;
	private Button button;
	private Label label;
	private Stage stage;
	private Utilisateur u;
	private ChoiceBox<Groupe> cbgroupe;
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
		cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(u.getGroupe()));
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
				connex.upload(chosenFile.getName(),b,u.getLogin(),cbgroupe.getSelectionModel().getSelectedItem().getIdGr());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//String filename = filechooser.getTitle();
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
