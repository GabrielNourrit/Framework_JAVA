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
import mail.Utilisateur;

public class WriteMessage extends Composition implements Alertable{

    private Button envoyer;
    private Label notification;
    private TextField objet;
    private TextArea text;
	private ComboBox<String> emailComboBox;
	private VBox alert;
	private HBox hb;
	private Label message;
	private MelInterface mel;
	private Utilisateur moi;
	private String verif;
	
	
    
    public WriteMessage(Utilisateur u) {
    	this.moi = u;
		chargerMel();
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
    
    public WriteMessage(Utilisateur u, String adr, String obj) {
		this(u);
		this.emailComboBox.setValue(adr);
		this.objet.setText("[RE]: " + obj);
	}
    

    public void methode(String mesg) {
    	String contenu = verif + ";" + this.text.getText();
    	VBox alerte = new Alerte(mesg,this);
		Stage s = new Stage();
		Scene scene = new Scene(alerte,800,100);
		s.setScene(scene);
		s.show();
    }
    
    private void chargerMel(){
    	try {
			Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
			this.mel = (MelInterface) registry.lookup("Mel");
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	@Override
	protected void genererSousComposant() {
		
		this.comp= new VBox();
		this.envoyer = new Button ("Envoyer");
		this.notification = new Label();
		this.objet = new TextField("");
		this.text = new TextArea ("");
		this.emailComboBox = new ComboBox<String>();
		this.hb = new HBox();
		this.message = new Label();
		this.alert = new VBox();
    	this.verif = "[Sans objet]";
    	
		try{
		initialiserComboBox();
		}catch(Exception e){
			System.out.println("Oups : SOD is in a lonely day");
			e.printStackTrace();
		}
		
	}

	private void initialiserComboBox() throws Exception{
		this.emailComboBox.setPromptText("Contact");
		for (Utilisateur u : this.mel.getAllUsers()){
			
			this.emailComboBox.getItems().add(u.contact());
		}
        this.emailComboBox.setEditable(true);  
        this.notification.setText("Objet : ");
	}
	
	private void envoyerMessage(){
		String contenu = verif + ";" + this.text.getText();
		try{
		this.mel.saveMessage(this.moi,contenu);
		 message.setText("Votre message a bien etait envoye a : " + this.emailComboBox.getValue());   
         emailComboBox.setValue(null);
         this.text.clear();
         this.objet.clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void ecouteurDefaultAction() {
		
		this.envoyer.setOnAction(event ->{
			if (this.emailComboBox.getValue() != null) {
				if (this.text.getLength() != 0) {
					if(this.objet.getLength() != 0) {
						//---
						// On remplace les ";" car c'est notre délimiteur Objet;Message
						// et si l'utilisateur entre des ; dans son objet, on va le remplacer par
						// des blancs pour eviter de deplacer une partie de l'objet dans le message
						//---
						this.verif = this.objet.getText().replace(";", " ");
						envoyerMessage();

						//--
					}
					else {
						methode("Vous avez oublier de mettre un objet");
					}
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Votre text est vide, vous ne pouvez pas envoyer un message vide ce n'est pas serieux");
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

	@Override
	public void update() {
		envoyerMessage();
	}

}
