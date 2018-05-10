package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import parametrage.PropertiesClient;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Fenetre {
	
	private Fenetre(){}
	
	public static Stage newStage(){
		Stage stage = new Stage();
		//Fenetre.paramStage(stage);
		return stage;
	}
	
	public static void paramStage(Stage stage){
		if(PropertiesClient.getLogoImage().trim().isEmpty())
			stage.getIcons().add(new Image(PropertiesClient.getLogoImage()));
	}
	
	public static Optional<ButtonType> creatAlert(AlertType typeAlert, String titre, String msg){
		Alert alert = new Alert(typeAlert);
		alert.setTitle(titre);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		return alert.showAndWait();
	}
	
}
