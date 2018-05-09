package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Fenetre {
	
	private Fenetre(){}
	
	public static Optional<ButtonType> creatAlert(AlertType typeAlert, String titre, String msg){
		Alert alert = new Alert(typeAlert);
		alert.setTitle(titre);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
		return alert.showAndWait();
	}
	
}
