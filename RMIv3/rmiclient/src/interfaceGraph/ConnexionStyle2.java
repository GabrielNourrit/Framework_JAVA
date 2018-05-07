package interfaceGraph;

import java.rmi.registry.Registry;

import connexion.ConnexionInterface;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import util.Utilisateur;

public class ConnexionStyle2 extends ConnexionStyle{
	protected Button boutonOk;	
	private ConnexionInterface connex;
	private Utilisateur utilisateur;

	/**
	 * Cet objet est un formulaire de connexion par defaut + bouton valider
	 */
	public ConnexionStyle2(){
		super();
		genererBouton();
		listenerButtonValider();
		utilisateur = null;
		
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
		this.boutonOk.addEventHandler(ActionEvent.ACTION, event ->{
			/*Traitement de l'appli*/
			System.out.println("id est "+this.id.getText());
			System.out.print("mdp est "+this.mdp.getText());
			try {
				Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
	            connex = (ConnexionInterface) registry.lookup("Connexion");
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
		});
	}
}

