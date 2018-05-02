package interfaceGraph;

import java.rmi.Naming;

import connexion.ConnexionInterface;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ConnexionStyle2 extends ConnexionStyle{
	private Button boutonOk;	

	/**
	 * Cet objet est un formulaire de connexion par defaut + bouton valider
	 */
	public ConnexionStyle2(){
		super();
		genererBouton();
		listenerButtonValider();
		
	}
	
	/**
	 * genere tout les sous Composant du formulaire
	 */
	private void genererBouton() {
		this.boutonOk= new Button("se connecter");
		boutonOk.setAlignment(Pos.CENTER);
		this.addElementForm(boutonOk);
		this.setSpacing(5);
	}
	
	/**
	 * definit Actions-Listeners du bouton valider
	 */
	private void listenerButtonValider() {
		this.boutonOk.setOnAction(event ->{
			/*Traitement de l'appli*/
			System.out.println("id est "+this.id.getText());
			System.out.print("mdp est "+this.mdp.getText());
			ConnexionInterface connex;
			try {
				connex = (ConnexionInterface)Naming.lookup("rmi://localhost/Connexion");
				if(connex.verifierMdp(id.getText(),mdp.getText())) {
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
}

