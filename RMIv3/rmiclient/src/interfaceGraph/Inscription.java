package interfaceGraph;

import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import BaseDeDonnee.gestionUtilisateur.TypesInterface;
import util.Connectable;
import util.Fenetre;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.LimitedTextField;
import util.Type;
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
	private Label l_groupe;
	private ChoiceBox<Type> cb_type;
	private Utilisateur utilisateur;
	private HBox hb_validerAnnuler;
	
	/**
	 * @description : Cet objet est un formulaire d'inscription par defaut
	 */
	public Inscription() {
		super();
		genererSousComposant();
		ecouteurDefaultAction();
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
		this();
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
		hb_validerAnnuler = new HBox(b_valider);
		hb_validerAnnuler.setAlignment(Pos.CENTER);
		l_groupe = new Label("Groupe : ");
		TypesInterface connex;
		try {
			connex = new Connectable<TypesInterface>().connexion("Types");
			List<Type> lesTypes = connex.getAllTypes();
			cb_type = new ChoiceBox<>(FXCollections.observableArrayList(lesTypes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//cb_type = new ChoiceBox<String>(FXCollections.observableArrayList("user","Admin"));
		cb_type.getSelectionModel().select(0);
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
				utilisateur = new Utilisateur(t_login.getText(), t_nom.getText(), t_prenom.getText(), cb_type.getSelectionModel().getSelectedItem());
				utilisateur.setMdp(mdp);
				try {
					
					OperationUtilisateurInterface connex = new Connectable<OperationUtilisateurInterface>().connexion("OperationUtilisateur");
					if (!(connex.AjouterUtilisateur(utilisateur))) {
						Fenetre.creatAlert(AlertType.WARNING, "Attention !", "Fail");				
					} else {
						t_login.setText("");
						t_mdp.setText("");
						t_nom.setText("");
						t_prenom.setText("");
					}
				} catch (Exception e) {
					Fenetre.creatAlert(AlertType.ERROR, "Information Dialog", "Erreur");
				}
			}
			
		});	
	}
	
	/**
	 * ajoute un evenement apres l'inscription 
	 * @param value
	 */
	public void setPostConnectEvent(EventHandler<ActionEvent> value) {
		this.b_valider.addEventHandler(ActionEvent.ACTION, value);
	}
	

	/**
	 * Applique les parametres par default du design du composant
	 */
	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		form.getChildren().addAll(l_titre,l_login,t_login,l_mdp,t_mdp,l_nom,t_nom,l_prenom,t_prenom,l_groupe,cb_type,hb_validerAnnuler);
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
