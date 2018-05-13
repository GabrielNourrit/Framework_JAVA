package interfaceGraph;

import connexion.ConnexionInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import util.Connectable;
import util.Fenetre;
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
	            //connex = (ConnexionInterface) registry.lookup("Connexion");
				connex = new Connectable<ConnexionInterface>().connexion("Connexion");
				if(connex.verifierMdp(id.getText(),mdp.getText())) {
					utilisateur = connex.getUse(id.getText());
					this.id.setText("");
					this.mdp.setText("");
					
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
			} catch(Exception e){
				Fenetre.creatAlert(AlertType.ERROR, "Information Dialog","Erreur");
			}	
			/*On efface les anciennes valeures une fois finie*/
			
		});
	}
	
	/**
	 * set un evenement apres la connection
	 * @param value
	 */
	public void setPostConnectEvent(EventHandler<ActionEvent> value) {
		this.boutonOk.addEventHandler(ActionEvent.ACTION, value);
	}
}

