package interfaceGraph;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fichier.Fichier;
import fichier.FichierListener;
import fichier.GestionFichierInterface;
import groupes.GroupeListener;
import groupes.GroupesInterface;
import util.Connectable;
import util.Fenetre;
import util.Groupe;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
	private GestionFichierInterface connex;
	private Utilisateur u;
	protected ChoiceBox<Groupe> cbgroupe;
	private List<Fichier> fs;
	private ObservableList<Fichier> items;
	protected DirectoryChooser directorychooser;
	private int groupe;

	public TelechargerFichier(Utilisateur utilisateur) throws Exception {
		this.u=utilisateur;
		connex = new Connectable<GestionFichierInterface>().connexion("Fichier");
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurChoixGroupe();
		ecouteurDefaultAction();	
	}

	/**
	 * genere tout les sous Composant
	 */
	protected void genererSousComposant() throws Exception {
		button = new Button("Download");
		label = new Label();
		form = new HBox();
		vb1 = new VBox();
		vb2 = new VBox();
		list = new ListView<Fichier>();
		List<Groupe> groups = u.getGroupe();
		cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(groups));
		
		for (Groupe g : groups) {
			Listener l = new Listener();
			int gr = g.getidGr();
			connex.addGroupListener(l,gr);
		}
		cbgroupe.getSelectionModel().select(0);
		groupe = cbgroupe.getSelectionModel().getSelectedItem().getidGr();
		fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getidGr());
	}

	/**
	 * ecouteur sur le choix du groupe
	 */
	private void ecouteurChoixGroupe() {
		cbgroupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<? super Number>) (ov, value, new_value) -> {
			try {
				items.removeAll(fs);
				cbgroupe.getSelectionModel().select((int) new_value);
				fs = connex.recupererFichierGroupe(cbgroupe.getSelectionModel().getSelectedItem().getidGr());
				items.addAll(fs);
				groupe = cbgroupe.getSelectionModel().getSelectedItem().getidGr();
			} catch (Exception e) {
				Fenetre.creatAlert(AlertType.ERROR, "Information Dialog", "Erreur");
			}
		});
	}
	
	/**
	 * definit tout les Actions-Listeners
	 */
	protected void ecouteurDefaultAction() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		button.setOnAction(event -> {
			Fichier f = list.getSelectionModel().getSelectedItem();
			directorychooser = new DirectoryChooser();
			File selectedDirectory = directorychooser.showDialog(stage);
			if(selectedDirectory == null){
				Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog", "Veuillez choisir un dossier");
			}
			else{
				label.setText(selectedDirectory.getAbsolutePath()+"/"+f.getNom());
				try {
					TransformerFichier.byteToFile(connex.download(f.getidFic()), selectedDirectory.getAbsolutePath()+"/"+f.getNom());
					label.setText("Fichier correctement telecharge");
				} catch (Exception e) {
					e.printStackTrace();
					label.setText("Erreur lors du telechargement");
				}
			}
			
		});
	}

	/**
	 * definit le style par defaut 
	 */
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
	
	/**
	 * Ajoute un fichier a la liste des fichiers
	 * @param f le fichier a ajouter
	 * @param g le groupe ou il faut ajouter le fichier
	 */
	private void ajouterFichier(Fichier f,Integer g) {
		if (groupe == g) {
			fs.add(f);
			items.clear();
			items.addAll(fs);
		}
	}
	
	/**
	 * sous classe implementant un Listener
	 */
	private class Listener extends UnicastRemoteObject implements FichierListener {

		private static final long serialVersionUID = 1L;

		protected Listener() throws RemoteException {
			super();
		}
		
		@Override
		public void nouveauFichier(Fichier f,Integer g) throws RemoteException {
			Platform.runLater(
					() -> {
						ajouterFichier(f,g);
					}
			);
		}
		
	}
}
