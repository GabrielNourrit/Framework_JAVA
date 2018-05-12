package interfaceGraph;

import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.UtilisateursInterface;
import groupes.GroupesInterface;
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
import util.Fenetre;
import util.Utilisateur;

public class CreerGroup extends Formulaire{
	protected Button b_valider;
	protected TextField text1;
	protected List<Utilisateur> lstUserNonInscrit;
	protected ObservableList<Utilisateur> olstUser;
	protected ObservableList<Utilisateur> olstUserInscrit;
	protected ListView<Utilisateur> utilisateurNonInscrit;
	protected ListView<Utilisateur> utilisateurInscrit;
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
	
	
	
	public CreerGroup() {
		//GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
		genererSousComposant();
		if (!(this instanceof ListUtilisateursGroupe)) {
			refreshList();
			ecouteurDefaultAction();
		}
		layoutDefaultParametre();
	}

	protected void genererSousComposant() {
		//VBox vb = new VBox();
		
		b_valider = new Button("ajouter");
		text1 = new TextField();
		label1 = new Label("Utilisateurs :");
		label2 = new Label("Nom du Groupe");
		label3 = new Label("Ajouter les groupes");
		label4 = new Label("Utilisateurs total");
		label5 = new Label("Uitlisateurs dans le groupe");
		b_ajouter = new Button("->");
		b_retirer = new Button("<-");
		form = new VBox();
		utilisateurInscrit = new ListView<Utilisateur>();
		utilisateurNonInscrit = new ListView<Utilisateur>();
		lstUserNonInscrit = new ArrayList<>();
		olstUserInscrit = FXCollections.observableArrayList(lstUserNonInscrit);
		vBoxButtonInscription = new VBox(b_ajouter, b_retirer);
		vBoxListNonInscrit = new VBox(label4, utilisateurNonInscrit);
		vBoxListInscrit = new VBox(label5, utilisateurInscrit);
		hBoxInscription = new HBox(vBoxListNonInscrit,vBoxButtonInscription,vBoxListInscrit);
		
	}

	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		
			b_valider.addEventHandler(ActionEvent.ACTION, event -> {
				if (!("".equals(text1.getText()))) {
					List<String> listLogin = new ArrayList<>();
					for (Utilisateur u: olstUserInscrit) {
						listLogin.add(u.getLogin());
					}
					try {
						//GroupesInterface connex = (GroupesInterface) registry.lookup("Groupes");
						GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
						connex.ajouterGroupe(text1.getText(), listLogin);
						text1.setText("");
						for (Utilisateur u: olstUserInscrit) {
							olstUser.add(u);
						}
						lstUserNonInscrit.clear();
						olstUserInscrit = FXCollections.observableArrayList(lstUserNonInscrit);
						utilisateurInscrit.setItems((ObservableList<Utilisateur>) olstUserInscrit);
						utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
					} catch (Exception e) {
						Fenetre.creatAlert(AlertType.ERROR, "Erreur", "Serveur chargement des groupes");
					}
				}
				
			});
		
		
		b_ajouter.setOnAction(event -> {
			Utilisateur u = getUserNonInscritSelected();
			if (u != null) {
				olstUserInscrit.add(u);
				olstUser.remove(u);
				
				utilisateurInscrit.setItems((ObservableList<Utilisateur>) olstUserInscrit);
			} 
		});
		
		b_retirer.setOnAction(event ->{
			Utilisateur u = getUserInscritSelected();
			if (u != null) {
				olstUser.add(u);
				olstUserInscrit.remove(u);
				utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
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
		utilisateurInscrit.setMinWidth(300);
		utilisateurNonInscrit.setMinWidth(300);
		text1.setAlignment(Pos.CENTER);
		form.getChildren().addAll(label2,text1,label1,hBoxInscription,b_valider);
		//_vb.getChildren().add(button);
		//_vb.setAlignment(Pos.CENTER);
		form.setAlignment(Pos.CENTER);
		text1.setMaxWidth(200);
		this.getChildren().add(form);
	}
	
	protected void refreshList() {
		UtilisateursInterface connex;
		try {
            //connex = (UtilisateursInterface) registry.lookup("Utilisateurs");
			connex = new Connectable<UtilisateursInterface>().connexion("Utilisateurs");
			List<Utilisateur> lstUserInscrit = connex.getUsers();
			System.out.println(lstUserInscrit);
			olstUser = FXCollections.observableArrayList(lstUserInscrit);
			
			utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
		} catch (Exception e) {
			Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
		}
	}
	
	public Utilisateur getUserInscritSelected() {
		if (utilisateurInscrit.getSelectionModel() != null) {
			return utilisateurInscrit.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}
	
	public Utilisateur getUserNonInscritSelected() {
		if (utilisateurNonInscrit.getSelectionModel() != null) {
			return utilisateurNonInscrit.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}

	public void setPostAdd(EventHandler<ActionEvent> value) {
		b_valider.addEventHandler(ActionEvent.ACTION, value);	
	}
	
}
