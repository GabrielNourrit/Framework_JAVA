package application;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Inscription extends Formulaire {
	private Group form;
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
	
	/**
	 * @description : Cet objet est un formulaire d'inscription par defaut
	 */
	public Inscription() {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
		this.form.getChildren().addAll(l_login, l_titre, t_login, l_mdp, t_mdp, l_nom, t_nom, l_prenom, t_prenom, b_valider);
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
		this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);
		
		/*Attachement de notre form a notre environnement test*/
		pere.getChildren().add(this.getStyleForm());
	}
	
	/**
	 * Genere les sous composant de notre composant
	 */
	@Override
	protected void genererSousComposant() {
		form = new Group();
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
	}

	/**
	 * Definie le comportement par defaut de notre interface
	 */
	@Override
	protected void ecouteurDefaultAction() {
		b_valider.setOnAction(event -> {
			System.out.println("OK");
			
			t_login.setText("");
			t_mdp.setText("");
			t_nom.setText("");
			t_prenom.setText("");
		});
		
		
	}

	/**
	 * Applique les paramètres par default du design du composant
	 */
	@Override
	protected void layoutDefaultParametre() {
		l_titre.setLayoutX(70);
		l_titre.setLayoutY(0);
		
		l_login.setLayoutX(0);
		l_login.setLayoutY(33);
		
		t_login.setLayoutX(100);
		t_login.setLayoutY(30);
		
		l_mdp.setLayoutX(0);
		l_mdp.setLayoutY(63);
		
		t_mdp.setLayoutX(100);
		t_mdp.setLayoutY(60);
		
		l_prenom.setLayoutX(0);
		l_prenom.setLayoutY(93);
		
		t_prenom.setLayoutX(100);
		t_prenom.setLayoutY(90);
		
		l_nom.setLayoutX(0);
		l_nom.setLayoutY(123);
		
		t_nom.setLayoutX(100);
		t_nom.setLayoutY(120);
		
		b_valider.setLayoutX(70);
		b_valider.setLayoutY(150);
	}
	
	/**
	 * @return retourne le formulaire Graphique modifiable a la guise du client.
	 */
	public Group getStyleForm(){
		return this.form;
	}

}
