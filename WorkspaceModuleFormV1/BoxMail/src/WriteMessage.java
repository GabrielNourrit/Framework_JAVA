import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WriteMessage extends Composition{

    private Button envoyer;
    private Label notification;
    private TextField objet;
    private TextArea text;
	private ComboBox emailComboBox;
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
		this.envoyer = new Button ("Send");
		this.notification = new Label();
		this.objet = new TextField("");
		this.text = new TextArea ("");
		this.emailComboBox = new ComboBox();
		this.hb = new HBox();
		this.message = new Label();
		this.alert = new VBox();
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		
		this.envoyer.setOnAction(event ->{
			/*Traitement de l'appli*/
			if (this.emailComboBox.getValue() != null) {
				if (this.text.getLength() != 0) {
					if(this.objet.getLength() != 0) {
						 message.setText("Votre message à bien était envoyé : " + this.emailComboBox.getValue());   
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
					alert.setContentText("Votre text est vide, vous ne pouvez pas envoyer un message vide ce n'est pas sérieux");
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
		hb.getChildren().addAll(this.notification,this.objet);
		comp.getChildren().addAll(this.emailComboBox);
		comp.getChildren().addAll(hb,this.text,this.envoyer,this.message);
		comp.setMaxSize(500,500);
		comp.setSpacing(3);
		//comp.setAlignment(Pos.CENTER);

		this.getChildren().add(this.comp);
	}

}
