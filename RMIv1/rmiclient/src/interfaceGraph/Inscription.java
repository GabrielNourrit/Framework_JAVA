package interfaceGraph;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Inscription extends Composition {
	private Label l_login;
	private Label l_mdp;
	private Label l_nom;
	private Label l_prenom;
	private Label l_titre;
	private TextField t_login;
	private PasswordField t_mdp;
	private TextField t_nom;
	private TextField t_prenom;
	private Button b_valider;
	private Label l_groupe;
	private ChoiceBox<String> cb_groupe;
	private int choix;
	
	/**
	 * @description : Cet objet est un formulaire d'inscription par defaut
	 */
	public Inscription() {
		super();
		choix=-1;
		genererSousComposant();
		ecouteurDefaultAction();
		ecouteurChoixGroupe();
		layoutDefaultParametre();
		this.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Inscription() avec liaison au pere et parametrage du placement
	 * @param pere le groupe pere de notre formulaire 
	 * @param X l'axe en X du formulaire dans la fenetre
	 * @param Y l'axe en Y du formulaire dans la fenetre
	 */
	public Inscription(Group pere, int X, int Y) {
		/*generation de notre formulaire*/
		this();
		/*mise en page form*/
		/*this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);*/
		
		/*Attachement de notre form a notre environnement test*/
		//pere.getChildren().add(this.getStyleForm());
	}
	
	/**
	 * Genere les sous composant de notre composant
	 */
	@Override
	protected void genererSousComposant() {
		//form = new GridPane();
		comp = new VBox();
		l_login = new Label("Login : ");
		l_mdp = new Label("Mot de passe : ");
		l_nom = new Label("Nom : ");
		l_prenom = new Label("Prenom : ");
		l_titre = new Label("Inscription");
		t_login = new TextField();
		t_mdp = new PasswordField();
		t_nom = new TextField();
		t_prenom = new TextField();
		b_valider = new Button("Valider");
		l_groupe = new Label("Groupe : ");
		cb_groupe = new ChoiceBox<String>(FXCollections.observableArrayList("Groupe 1","Groupe 2"));
	}

	/**
	 * Definie le comportement par defaut de notre interface
	 */
	@Override
	protected void ecouteurDefaultAction() {
		b_valider.setOnAction(event -> {		
			t_login.setText("");
			t_mdp.setText("");
			t_nom.setText("");
			t_prenom.setText("");
		});	
		if (choix == 1);
	}
	
	/**
	 * Ecouteur d'evenement sur le choix du groupe
	 */
	private void ecouteurChoixGroupe() {
		cb_groupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Number>) (ov, value, new_value) -> {
			System.out.println(value);	
			System.out.println(new_value);
			choix = (int) new_value;
			System.out.println(ov.getClass());
		});
	}

	/**
	 * Applique les parametres par default du design du composant
	 */
	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		comp.getChildren().addAll(l_titre,l_login,t_login,l_mdp,t_mdp,l_nom,t_nom,l_prenom,t_prenom,l_groupe,cb_groupe,b_valider);
		comp.setMaxSize(120, 100);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(comp);	
	}
	
	
	/**
	 * @return retourne le formulaire Graphique modifiable a la guise du client.
	 */
	public VBox getStyleForm(){
		return this.comp;
	}

}
