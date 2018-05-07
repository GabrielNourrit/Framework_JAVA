package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import fichier.Fichier;
import fichier.GestionFichierInterface;
import fichier.Groupe;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.TransformerFichier;
import util.Utilisateur;

public class TelechargerFichier extends VBox {
	private HBox form;
	private VBox vb1;
	private VBox vb2;
	private Button button;
	private Label label;
	private ListView<Fichier> list;
	private Registry registry;
	private GestionFichierInterface connex;
	private Utilisateur u;
	private ChoiceBox<Groupe> cbgroupe;
	private List<Fichier> fs;
	private ObservableList<Fichier> items;

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
		cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(u.getGroupe()));
		cbgroupe.getSelectionModel().select(0);
		System.out.println(cbgroupe.getSelectionModel().getSelectedItem().getIdGr());
		fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getIdGr());
	}

	
	private void ecouteurChoixGroupe() {
		cbgroupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<? super Number>) (ov, value, new_value) -> {
			try {
				items.removeAll(fs);
				cbgroupe.getSelectionModel().select((int) new_value);
				fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getIdGr());
				items.addAll(fs);
				System.out.println(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	protected void ecouteurDefaultAction() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		button.setOnAction(event -> {
			Fichier f = list.getSelectionModel().getSelectedItem();
			try {
				TransformerFichier.byteToFile(connex.download(f.getidFic()), f.getNom());
			} catch (Exception e) {
				e.printStackTrace();
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
