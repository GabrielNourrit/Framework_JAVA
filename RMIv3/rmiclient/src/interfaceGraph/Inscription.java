package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import fichier.Groupe;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.LimitedTextField;
import util.Utilisateur;

public class Inscription extends Formulaire {
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
	private Button b_annuler;
	private Label l_groupe;
	private ChoiceBox<String> cb_groupe;
	private Utilisateur utilisateur;
	private HBox hb_validerAnnuler;
	//private int choix;
	
	/**
	 * @description : Cet objet est un formulaire d'inscription par defaut
	 */
	public Inscription() {
		super();
		//choix=-1;
		genererSousComposant();
		ecouteurDefaultAction();
		//ecouteurChoixGroupe();
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
		form = new VBox();
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
		b_annuler = new Button("Annuler");
		hb_validerAnnuler = new HBox(b_valider,b_annuler);
		hb_validerAnnuler.setAlignment(Pos.CENTER);
		l_groupe = new Label("Groupe : ");
		cb_groupe = new ChoiceBox<String>(FXCollections.observableArrayList("user","Admin"));
		cb_groupe.getSelectionModel().select(0);
		LimitedTextField.addTextLimiter(t_login, 50);
		LimitedTextField.addTextLimiter(t_nom, 50);
		LimitedTextField.addTextLimiter(t_prenom, 50);
		LimitedTextField.addTextLimiter(t_mdp, 100);
	}

	/**
	 * Definie le comportement par defaut de notre interface
	 */
	@Override
	protected void ecouteurDefaultAction() {
		b_valider.addEventHandler(ActionEvent.ACTION,event -> {		
			
			if (t_login.getText()!="" && t_mdp.getText()!="") {
				String mdp = BCrypt.hashpw(t_mdp.getText(), BCrypt.gensalt());
				List<Groupe> l=null;
				utilisateur = new Utilisateur(t_login.getText(), t_nom.getText(), t_prenom.getText(), cb_groupe.getSelectionModel().getSelectedItem(),l);
				utilisateur.setMdp(mdp);
				OperationUtilisateurInterface connex;
				Registry registry;
				try {
					registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
					connex = (OperationUtilisateurInterface) registry.lookup("OperationUtilisateur");
					if (!(connex.AjouterUtilisateur(utilisateur))) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Attention !");
						alert.setHeaderText(null);
						alert.setContentText("Fail");
						alert.showAndWait();
						
					} else {
						t_login.setText("");
						t_mdp.setText("");
						t_nom.setText("");
						t_prenom.setText("");
					}
				} catch (RemoteException | NotBoundException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			
		});	
	}
	
	public void setPostConnectEvent(EventHandler<ActionEvent> value) {
		this.b_valider.addEventHandler(ActionEvent.ACTION, value);
	}
	
	public void setAnnulerEvent(EventHandler<ActionEvent> value) {
		b_annuler.addEventHandler(ActionEvent.ACTION, value);
	}
	
	/**
	 * Ecouteur d'evenement sur le choix du groupe
	 */
	/*private void ecouteurChoixGroupe() {
		cb_groupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<Number>) (ov, value, new_value) -> {
			System.out.println(value);	
			System.out.println(new_value);
			choix = (int) new_value;
			System.out.println(ov.getClass());
		});
	}*/

	/**
	 * Applique les parametres par default du design du composant
	 */
	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		form.getChildren().addAll(l_titre,l_login,t_login,l_mdp,t_mdp,l_nom,t_nom,l_prenom,t_prenom,l_groupe,cb_groupe,hb_validerAnnuler);
		//form.setMinSize(minWidth, minHeight);
		form.setMaxSize(160, 100);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);	
	}
	
	
	/**
	 * @return retourne le formulaire Graphique modifiable a la guise du client.
	 */
	public VBox getStyleForm(){
		return this.form;
	}
	
	/**
	 * Retourne l'utilisateur créer, devrai etre appellé dans l'evenement après l'inscription d'un utilisateur
	 * @return Utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
