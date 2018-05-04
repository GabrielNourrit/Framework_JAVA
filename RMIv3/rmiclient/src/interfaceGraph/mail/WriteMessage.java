package interfaceGraph.mail;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import connexion.ConnexionInterface;
import interfaceGraph.Composition;
import interfaceGraph.Inscription;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mail.MelInterface;

public class WriteMessage extends Composition{

    private Button envoyer;
    private Label notification;
    private TextField objet;
    private TextArea text;
	private ComboBox<String> emailComboBox;
	private VBox alert;
	private HBox hb;
	private Label message;
	private String sauvegarde;
    
    public WriteMessage() {
		genererSousComposant();
		affichage();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
    
    public WriteMessage(String adr, String obj) {
		this();
		this.emailComboBox.setValue(adr);
		this.objet.setText("[RE]: " + obj);
	}
    
    
    public void affichage() {
    	this.emailComboBox.getItems().addAll("guevarat@example.com","nourritG@example.com","lyuya@example.com","metzgegu@example.com","dervieuxc@example.com", "");
        this.emailComboBox.setPromptText("Contact");
        this.emailComboBox.setEditable(true);  
        
        this.notification.setText("Objet : ");	
    }

    public void methode(String mesg) {
    	VBox alerte = new Alerte(mesg);
		Stage s = new Stage();
		Scene scene = new Scene(alerte,800,400);
		s.setScene(scene);
		s.show();
    }
    
	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		
		this.comp= new VBox();
		this.envoyer = new Button ("Envoyer");
		this.notification = new Label();
		this.objet = new TextField("");
		this.text = new TextArea ("");
		this.emailComboBox = new ComboBox<String>();
		this.hb = new HBox();
		this.message = new Label();
		this.alert = new VBox();
	}

	@Override
	protected void ecouteurDefaultAction() {
		
		this.envoyer.setOnAction(event ->{
			if (this.emailComboBox.getValue() != null) {
				if (this.text.getLength() != 0) {
					if(this.objet.getLength() != 0) {
						//---
						
						
						try {
							Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
							MelInterface mel = (MelInterface) registry.lookup("Mel");
							mel.test();
						}catch (Exception e) {
							e.printStackTrace();
						}
						
						
						
						
						
						
						//---
						 message.setText("Votre message � bien �tait envoy� � : " + this.emailComboBox.getValue());   
		                        emailComboBox.setValue(null);
		                        this.text.clear();
		                        this.objet.clear();
					}
					else {
						methode("Vous avez oublier de mettre un objet");
					}
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Votre text est vide, vous ne pouvez pas envoyer un message vide ce n'est pas s�rieux");
					alert.showAndWait();
				}
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Vous n'avez pas choisis de destinataire");
				alert.showAndWait();
			}
			});
	}
		
	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		this.emailComboBox.setLayoutX(500);
		text.setWrapText(true);
		objet.setPrefWidth(450);
		hb.getChildren().addAll(this.notification,this.objet);
		comp.getChildren().addAll(this.emailComboBox);
		comp.getChildren().addAll(hb,this.text,this.envoyer,this.message);
		comp.setMaxSize(500,500);
		comp.setSpacing(3);
		//comp.setAlignment(Pos.CENTER);

		this.getChildren().add(this.comp);
	}

}