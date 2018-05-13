package interfaceGraph;

import connexion.ConnexionInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.Connectable;
import util.Fenetre;
import util.Utilisateur;

public class ConnexionStyle extends Formulaire{
	protected Label l_id;
	protected Label l_mdp;
	protected TextField id;
	protected PasswordField mdp;
	private Utilisateur utilisateur;
	
	/**
	 * Cet objet est un formulaire de connexion par defaut
	 */
	public ConnexionStyle(){
		super();
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();
		utilisateur = null;
	}

	@Override
	protected void genererSousComposant() {
		//this.form = new GridPane();
		this.form = new VBox();
		this.l_id = new Label("id");
		this.id = new TextField();
		this.l_mdp = new Label("pass");
		this.mdp = new PasswordField();
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		/*Definie le comportement par defaut de notre interface*/
		this.mdp.addEventHandler(ActionEvent.ACTION, event ->{
			try {
				ConnexionInterface connex = new Connectable<ConnexionInterface>().connexion("Connexion");
				if(connex.verifierMdp(id.getText(),mdp.getText())) {
					utilisateur = connex.getUse(id.getText());
					
					/*ScrollPane sp = new ScrollPane();
					Inscription i = new Inscription();
					Stage nouveauStage;
					nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					sp.setContent(i);
					i.setAlignment(Pos.CENTER);
					sp.setFitToWidth(true);
					sp.setFitToHeight(true);
					Scene scene = new Scene(sp, 200, 250);
					Fenetre.paramStage(nouveauStage);
					nouveauStage.setScene(scene);*/
				}
				else {
					Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog","Login ou mot de passe incorrect");
				}
			} catch (Exception e) {
				Fenetre.creatAlert(AlertType.ERROR, "Information Dialog","Erreur");
			}
			
			/*On efface les anciennes valeures une fois finie*/
			this.id.setText("");
			this.mdp.setText("");
			//value;
		});		
	}
	
	/**
	 * set un evenement apres la connection
	 * @param value
	 */
	public void setPostConnectEvent(EventHandler<ActionEvent> value) {
		this.mdp.addEventHandler(ActionEvent.ACTION, value);
	}

	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		form.getChildren().addAll(l_id,id,l_mdp,mdp);
		form.setMaxSize(120, 100);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}

	/**
	 * a appeler dans l'evenement de setPostConnectEvent()
	 * @return l'utilisateur connecter
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
}
