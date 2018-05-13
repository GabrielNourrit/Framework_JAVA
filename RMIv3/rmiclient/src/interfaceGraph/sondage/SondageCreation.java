package interfaceGraph.sondage;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import connexion.ConnexionInterface;
import interfaceGraph.Composition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sondage.SondageInterface;
import util.Connectable;
import util.Fenetre;
import util.Utilisateur;

public class SondageCreation extends Composition{
	private Label titre1;
	private Label titre2;
	private Label titre3;
	private TextField question;
	private VBox choix;
	private Button nouveau;
	private BoutonChoix multiple;
	private DatePicker date;
	private Group questionC;
	private Button ok;
	private Utilisateur u;

	public SondageCreation(Utilisateur u) {
		this.u= u;
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}

	@Override
	protected void genererSousComposant() {
		this.comp= new VBox();
		this.titre1 = new Label("Question :");
		this.question = new TextField();
		this.titre2 = new Label("Reponses :");
		this.nouveau = new Button("ajouter choix");
		this.choix = new VBox();
		this.multiple = new BoutonChoix("multiple ");
		this.titre3 = new Label("Date de cloture :");
		this.date = new DatePicker();
		this.ok = new Button("ok");
		this.questionC = new Group();
	}

	private void prblm(String arg) {
		Fenetre.creatAlert(AlertType.ERROR,"Error Dialog" ,arg );
	}

	private boolean tailleAcceptable(String t) {
		return (t.length()>0);
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.nouveau.setOnAction(event ->{
			this.choix.getChildren().add(new TextField());
		});
		this.ok.setOnAction(ev->{
			if(!tailleAcceptable(((TextField) question).getText())) { 
				prblm("Veuillez ecrire une question ! ");
			}else {
				//--TRAITEMENT 1

				String reponses ="";

				if(choix.getChildren().isEmpty()) {
					prblm("Veuillez ajouter au moins 1 reponse");
				}else {

					for(Node reponse : choix.getChildren()) {
						if(!tailleAcceptable(((TextField) reponse).getText())) {
							prblm("Veuillez remplir les barres de reponses generees ! ");
						}else {
							//--TRAITEMENT 2
							reponses+=((TextField) reponse).getText()+";0;";
						}
					}

					try {
						if(!tailleAcceptable(this.date.getValue().toString())) { 
							prblm("Veuillez ecrire une question ! ");
						}else {
							//--TRAITEMENT 3

							SondageInterface s = new Connectable<SondageInterface>().connexion("Sondage");

							s.creerSondage(u.getLogin(),this.question.getText(), reponses, this.multiple.isChecked(), this.date.getValue().toString());

							Stage st = (Stage) getScene().getWindow();
							st.close();
						}
					}catch(NullPointerException e) {
						prblm("Veuillez selectionner une date avec le calendrier.");
					} catch (Exception e) {
						prblm("Erreur connexion serveur");
					}
				}

			}
		});
	}

	@Override
	protected void layoutDefaultParametre() {
		this.question.setLayoutX(70);
		this.titre1.setLayoutY(4);
		questionC.getChildren().addAll(titre1,question);

		comp.getChildren().addAll(questionC,titre2,choix,nouveau,multiple,titre3,date,ok);
		comp.setMaxSize(120, 100);
		comp.setSpacing(5);
		this.getChildren().add(comp);	
	}

}
