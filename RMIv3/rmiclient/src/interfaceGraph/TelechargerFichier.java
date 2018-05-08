package interfaceGraph;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fichier.Fichier;
import fichier.GestionFichierInterface;
import util.Groupe;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import util.TransformerFichier;
import util.Utilisateur;

public class TelechargerFichier extends VBox {
	protected HBox form;
	protected VBox vb1;
	protected VBox vb2;
	protected Button button;
	protected Label label;
	private Stage stage;
	protected ListView<Fichier> list;
	private Registry registry;
	private GestionFichierInterface connex;
	private Utilisateur u;
	protected ChoiceBox<Groupe> cbgroupe;
	private List<Fichier> fs;
	private ObservableList<Fichier> items;
	protected DirectoryChooser directorychooser;

	public TelechargerFichier(Utilisateur utilisateur) throws RemoteException, ClassNotFoundException, NotBoundException, SQLException {
		this.u=utilisateur;
		registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (GestionFichierInterface) registry.lookup("Fichier");
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurChoixGroupe();
		ecouteurDefaultAction();	
	}

	protected void genererSousComposant() throws ClassNotFoundException, RemoteException, SQLException {
		button = new Button("Download");
		label = new Label("Filename");
		form = new HBox();
		vb1 = new VBox();
		vb2 = new VBox();
		list = new ListView<Fichier>();
		try {
			cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(u.getGroupe()));
			System.out.println(u.getGroupe());
		} catch (RemoteException | ClassNotFoundException | NotBoundException | SQLException e) {
			//cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(new ArrayList<Groupe>()));
			e.printStackTrace();
		}
		cbgroupe.getSelectionModel().select(0);
		System.out.println(cbgroupe.getSelectionModel().getSelectedItem().getidGr());
		fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getidGr());
	}

	
	private void ecouteurChoixGroupe() {
		cbgroupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<? super Number>) (ov, value, new_value) -> {
			try {
				items.removeAll(fs);
				cbgroupe.getSelectionModel().select((int) new_value);
				fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getidGr());
				items.addAll(fs);
				System.out.println(fs);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Erreur");
				alert.showAndWait();
			}
		});
	}
	
	protected void ecouteurDefaultAction() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		button.setOnAction(event -> {
			Fichier f = list.getSelectionModel().getSelectedItem();
			directorychooser = new DirectoryChooser();
			File selectedDirectory = directorychooser.showDialog(stage);
			if(selectedDirectory == null){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez choisir un dossier");
				alert.showAndWait();
			}
			else{
				try {
					TransformerFichier.byteToFile(connex.download(f.getidFic()), selectedDirectory.getAbsolutePath()+"/"+f.getNom());//f.getNom());
				} catch (Exception e) {
					e.printStackTrace();
				}
			     System.out.println(selectedDirectory.getAbsolutePath());
			}
			
		});
	}

	protected void layoutDefaultParametre() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		items = FXCollections.observableArrayList (fs); 
		list.setItems(items);
		vb1.getChildren().add(list);
		vb2.getChildren().addAll(cbgroupe,button,label);
		vb1.setAlignment(Pos.CENTER);
		vb2.setAlignment(Pos.CENTER);
		form.getChildren().addAll(vb1,vb2);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);

	}
	public HBox getStyleForm(){
		return this.form;
	}
}
