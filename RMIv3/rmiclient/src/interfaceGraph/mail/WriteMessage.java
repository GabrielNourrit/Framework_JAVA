package interfaceGraph.mail;

import interfaceGraph.Composition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mail.MelInterface;
import util.Connectable;
import util.Fenetre;
import util.Utilisateur;

public class WriteMessage extends Composition implements Alertable{

    private Button envoyer;
    private Label notification;
    private TextField objet;
    private TextArea text;
	private ComboBox<String> emailComboBox;
	private HBox hb;
	private Label message;
	private MelInterface mel;
	private Utilisateur moi;
	private String verif;
	//private MelCell mc;
	
    
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
    	VBox alerte = new Alerte(mesg,this);
		Stage s = new Stage();
		Scene scene = new Scene(alerte,800,100);
		s.setScene(scene);
		s.show();
    }
    
    private void chargerMel(){
    	try {
			this.mel = new Connectable<MelInterface>().connexion("Mel");
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
		this.emailComboBox.setEditable(false);
		for (Utilisateur u : this.mel.getAllUsers()){
			this.emailComboBox.getItems().add(u.contact());
		}
        this.emailComboBox.setEditable(true);  
        this.notification.setText("Objet : ");
	}
	
	private void envoyerMessage(){
		String contenu = this.text.getText();
		try{
		this.mel.saveMessage(this.moi.getLogin(),this.emailComboBox.getValue().split("#")[1],contenu,verif);
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
						this.verif = this.objet.getText();
						envoyerMessage();
						Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
						s.close();
						//--
					}
					else {
						methode("Vous avez oublier de mettre un objet");
					}
				}
				else {
					Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog","Votre text est vide, vous ne pouvez pas envoyer un message vide ce n'est pas serieux");
				}
			}
			else {
				Fenetre.creatAlert(AlertType.INFORMATION, "Information Dialog", "Vous n'avez pas choisis de destinataire");
			}
			});
			
	}
		
	@Override
	protected void layoutDefaultParametre() {
		this.emailComboBox.setLayoutX(500);
		text.setWrapText(true);
		objet.setPrefWidth(450);
		hb.getChildren().addAll(this.notification,this.objet);
		comp.getChildren().addAll(this.emailComboBox);
		comp.getChildren().addAll(hb,this.text,this.envoyer,this.message);
		comp.setMaxSize(500,500);
		comp.setSpacing(3);
		this.getChildren().add(this.comp);
	}

	@Override
	public void update() {
		envoyerMessage();
	}

}
