package interfaceGraph;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import connexion.ConnexionInterface;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnexionStyle extends Composition{
	private Label l_id;
	private Label l_mdp;
	protected ConnexionInterface connexion;
	protected TextField id;
	protected PasswordField mdp;
	
	/**
	 * Cet objet est un formulaire de connexion par defaut
	 */
	public ConnexionStyle(){
		super();
		try {
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
        connexion = (ConnexionInterface) registry.lookup("Connexion");
		}catch(Exception e) {
			e.printStackTrace();
		}
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();		
	}

	@Override
	protected void genererSousComposant() {
		//this.form = new GridPane();
		this.comp = new VBox();
		this.l_id = new Label("id");
		this.id = new TextField();
		this.l_mdp = new Label("pass");
		this.mdp = new PasswordField();
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		/*Definie le comportement par defaut de notre interface*/
		this.mdp.setOnAction(event ->{
			/*Traitement de l'appli*/
			System.out.println("id est "+this.id.getText());
			System.out.print("mdp est "+this.mdp.getText());
			
				//connex = (ConnexionInterface)Naming.lookup("rmi://localhost/Connexion");
				try {
					if(this.connexion.verifierMdp(id.getText(),mdp.getText())) {
						ScrollPane sp = new ScrollPane();
						Inscription i = new Inscription();
						Stage nouveauStage;
						nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						sp.setContent(i);
						i.setAlignment(Pos.CENTER);
						sp.setFitToWidth(true);
						sp.setFitToHeight(true);
						Scene scene = new Scene(sp, 200, 250);
						nouveauStage.setScene(scene);
					}
					else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText("Login ou mot de passe incorrect");
						alert.showAndWait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
			/*On efface les anciennes valeures une fois finie*/
			this.id.setText("");
			this.mdp.setText("");
		});
		
	}

	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		comp.getChildren().addAll(l_id,id,l_mdp,mdp);
		comp.setMaxSize(120, 100);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(comp);		
	}	
}
