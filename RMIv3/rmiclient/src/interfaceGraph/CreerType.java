package interfaceGraph;

import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.GestionTypeInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.Connectable;
import util.Droit;
import util.Fenetre;

public class CreerType extends Formulaire{

	protected Button b_valider;
	protected TextField text1;
	protected List<Droit> lstDroitNonInscrit;
	protected List<Droit> lstDroitInscrit;
	protected ObservableList<Droit> olstDroit;
	protected ObservableList<Droit> olstDroitInscrit;
	protected ListView<Droit> droitNonInscrit;
	protected ListView<Droit> droitInscrit;
	protected Button b_ajouter;
	protected Button b_retirer;
	private Label label1;
	private Label label2;
	protected Label label3;
	private Label label4;
	private Label label5;
	private HBox hBoxInscription;
	private VBox vBoxButtonInscription;
	private VBox vBoxListInscrit;
	private VBox vBoxListNonInscrit;
	
	
	
	public CreerType() {
		genererSousComposant();
		if (!(this instanceof ListDroitsType)) {
			refreshList();
			ecouteurDefaultAction();
		}
		layoutDefaultParametre();
	}

	protected void genererSousComposant() {	
		b_valider = new Button("ajouter");
		text1 = new TextField();
		label1 = new Label("Droit :");
		label2 = new Label("Nom du Type");
		label3 = new Label("Ajouter les types");
		label4 = new Label("Droit total");
		label5 = new Label("Droits dans le groupe");
		b_ajouter = new Button("->");
		b_retirer = new Button("<-");
		form = new VBox();
		droitInscrit = new ListView<Droit>();
		droitNonInscrit = new ListView<Droit>();
		lstDroitNonInscrit = new ArrayList<>();
		lstDroitInscrit = new ArrayList<>();
		olstDroitInscrit = FXCollections.observableArrayList(lstDroitInscrit);
		vBoxButtonInscription = new VBox(b_ajouter, b_retirer);
		vBoxListNonInscrit = new VBox(label4, droitNonInscrit);
		vBoxListInscrit = new VBox(label5, droitInscrit);
		hBoxInscription = new HBox(vBoxListNonInscrit,vBoxButtonInscription,vBoxListInscrit);
		
	}

	protected void ecouteurDefaultAction() {
		
			b_valider.addEventHandler(ActionEvent.ACTION, event -> {
				if (!("".equals(text1.getText()))) {
					List<String> listLogin = new ArrayList<>();
					for (Droit t: olstDroitInscrit) {
						listLogin.add(t.getId());
					}
					try {
						GestionTypeInterface connex = new Connectable<GestionTypeInterface>().connexion("Types");
						connex.addType(text1.getText(), listLogin);
						text1.setText("");
						for (Droit t: olstDroitInscrit) {
							olstDroit.add(t);
						}
						lstDroitNonInscrit.clear();
						olstDroitInscrit = FXCollections.observableArrayList(lstDroitInscrit);
						droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
						droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
					} catch (Exception e) {
						e.printStackTrace();
						Fenetre.creatAlert(AlertType.ERROR, "Erreur", "Serveur chargement des types");
					}
				}
				
			});
		
		
		b_ajouter.setOnAction(event -> {
			Droit t = getDroitNonInscritSelected();
			if (t != null) {
				olstDroitInscrit.add(t);
				olstDroit.remove(t);
				
				droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
			} 
		});
		
		b_retirer.setOnAction(event ->{
			Droit t = getDroitInscritSelected();
			if (t != null) {
				olstDroit.add(t);
				olstDroitInscrit.remove(t);
				droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
			} 
		});
	}

	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		label1.setAlignment(Pos.CENTER);
		label2.setAlignment(Pos.CENTER);
		label3.setAlignment(Pos.CENTER);
		b_valider.setAlignment(Pos.BOTTOM_CENTER);
		b_ajouter.setAlignment(Pos.CENTER);
		b_retirer.setAlignment(Pos.CENTER);
		vBoxButtonInscription.setAlignment(Pos.CENTER);
		vBoxButtonInscription.setMinWidth(50);
		droitInscrit.setMinWidth(300);
		droitNonInscrit.setMinWidth(300);
		text1.setAlignment(Pos.CENTER);
		form.getChildren().addAll(label2,text1,label1,hBoxInscription,b_valider);
		//_vb.getChildren().add(button);
		//_vb.setAlignment(Pos.CENTER);
		form.setAlignment(Pos.CENTER);
		text1.setMaxWidth(200);
		this.getChildren().add(form);
	}
	
	protected void refreshList() {
		GestionTypeInterface connex;
		try {
            //connex = (UtilisateursInterface) registry.lookup("Utilisateurs");
			connex = new Connectable<GestionTypeInterface>().connexion("Types");
			lstDroitNonInscrit = connex.getAllDroit();
			olstDroit = FXCollections.observableArrayList(lstDroitNonInscrit);
			
			droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
		} catch (Exception e) {
			Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
		}
	}
	
	public Droit getDroitInscritSelected() {
		if (droitInscrit.getSelectionModel() != null) {
			return droitInscrit.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}
	
	public Droit getDroitNonInscritSelected() {
		if (droitNonInscrit.getSelectionModel() != null) {
			return droitNonInscrit.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}

	public void setPostAdd(EventHandler<ActionEvent> value) {
		b_valider.addEventHandler(ActionEvent.ACTION, value);
		
	}
}