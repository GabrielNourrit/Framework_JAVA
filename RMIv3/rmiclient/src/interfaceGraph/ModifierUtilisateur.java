package interfaceGraph;

import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Connectable;
import util.Fenetre;
import util.LimitedTextField;
import util.Utilisateur;

public class ModifierUtilisateur extends Formulaire {

	private Utilisateur utilisateur;
	private Label l_login;
	private Label l_nom;
	private Label l_prenom;
	private Label l_titre;
	private TextField t_login;
	private TextField t_nom;
	private TextField t_prenom;
	private Button b_mdp;
	private Button b_valider;
	
	/**
	 * Constructeur de la classe ModifierUtilisateur
	 * @param _utilisateur l'utilisateur à modifié
	 */
	public ModifierUtilisateur(Utilisateur _utilisateur) {
		super();
		utilisateur = _utilisateur;
		if (utilisateur != null) {
			genererSousComposant();
			ecouteurDefaultAction();
			layoutDefaultParametre();
			this.setAlignment(Pos.CENTER);
		}
	}


	@Override
	protected void genererSousComposant() {
		form = new VBox();
		try {
			t_login = new TextField(utilisateur.getLogin());
			
			t_nom = new TextField(utilisateur.getNom());
			t_prenom = new TextField(utilisateur.getPrenom());
			LimitedTextField.addTextLimiter(t_nom, 50);
			LimitedTextField.addTextLimiter(t_prenom, 50);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		t_login.setDisable(true);
		b_mdp = new Button("Modifier le mot de passe");
		b_valider= new Button("Modifier");
		l_login = new Label("Login : ");
		l_nom = new Label("Nom : ");
		l_prenom = new Label("Prenom : ");
		l_titre = new Label("Modifier Utilisateur");
	}

	@Override
	protected void ecouteurDefaultAction() {
		b_valider.addEventHandler(ActionEvent.ACTION,event ->{
			Utilisateur uNew = null;
			uNew = new Utilisateur(utilisateur.getLogin(), t_nom.getText(), t_prenom.getText(), utilisateur.getType());
			OperationUtilisateurInterface connex;
			try {
				connex = connectToServeur();
				connex.ModifierUtilisateur(utilisateur, uNew);
			} catch (Exception e) {
				Fenetre.creatAlert(AlertType.ERROR, "Information Dialog", "Erreur :" + e.getMessage());
			}
			
		});
		
		b_mdp.setOnAction(event -> {
			Stage windowModifier = new Stage();
			ModifierMotDePasse vb = new ModifierMotDePasse(utilisateur);
			vb.setPostValidEvent(e -> {
				windowModifier.close();
				
			});
			ScrollPane sp = new ScrollPane();
			sp.setContent(vb);
			vb.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 400, 400);
			windowModifier.setTitle("Modification");
			Fenetre.paramStage(windowModifier,scene);
			windowModifier.show();
		});
	}
	
	public void setPostValidEvent(EventHandler<ActionEvent> value) {
		this.b_valider.addEventHandler(ActionEvent.ACTION, value);
	}

	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		form.getChildren().addAll(l_titre,l_login,t_login,b_mdp,l_nom,t_nom,l_prenom,t_prenom,b_valider);
		form.setMaxSize(200, 200);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);	
	}
	
	private OperationUtilisateurInterface connectToServeur() throws Exception {
		return new Connectable<OperationUtilisateurInterface>().connexion("OperationUtilisateur");
	}
	
}
