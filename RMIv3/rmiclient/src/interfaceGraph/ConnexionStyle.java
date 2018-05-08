package interfaceGraph;

import java.rmi.registry.Registry;

import connexion.ConnexionInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import parametrage.PropertiesClient;
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
			/*Traitement de l'appli*/
			System.out.println("id est "+this.id.getText());
			System.out.print("mdp est "+this.mdp.getText());
			
			ConnexionInterface connex;
			try {
				Registry registry = java.rmi.registry.LocateRegistry.getRegistry(PropertiesClient.getAdresseServeur(),1099);
	            connex = (ConnexionInterface) registry.lookup("Connexion");
				//connex = (ConnexionInterface)Naming.lookup("rmi://localhost/Connexion");
				if(connex.verifierMdp(id.getText(),mdp.getText())) {
					utilisateur = connex.getUse(id.getText());
					System.out.println(utilisateur);
					/*ScrollPane sp = new ScrollPane();
					Inscription i = new Inscription();
					Stage nouveauStage;
					nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					sp.setContent(i);
					i.setAlignment(Pos.CENTER);
					sp.setFitToWidth(true);
					sp.setFitToHeight(true);
					Scene scene = new Scene(sp, 200, 250);
					nouveauStage.setScene(scene);*/
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Login ou mot de passe incorrect");

					alert.showAndWait();
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Erreur");
				alert.showAndWait();
			}
			
			/*On efface les anciennes valeures une fois finie*/
			this.id.setText("");
			this.mdp.setText("");
			//value;
		});
		
	}
	
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
}
