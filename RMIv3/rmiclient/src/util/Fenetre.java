package util;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import parametrage.PropertiesClient;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Fenetre {
	
	private Fenetre(){}
	
	public static void paramStage(Stage stage, Scene scene){
		launchWindow(stage, scene,PropertiesClient.getLocalisationCss() + PropertiesClient.getCssDefaut());
	}
	
	public static void paramStageCssRelatif(Stage stage, Scene scene, String nomFile){
		launchWindow(stage,scene,PropertiesClient.getLocalisationCss() +nomFile);
	}
	
	public static void paramStageCssAbsolut(Stage stage, Scene scene, String url){
		launchWindow(stage , scene,"///" + url);
	}
	
	private static void launchWindow(Stage stage, Scene scene, String url){
		stage.getIcons().add(new Image(PropertiesClient.getLogoImage()));
		scene.getStylesheets().add("file:"+url);
		stage.setScene(scene);
	}
	
	public static Optional<ButtonType> creatAlert(AlertType typeAlert, String titre, String msg){
		Alert alert = new Alert(typeAlert);
		alert.setTitle(titre);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		return alert.showAndWait();
	}
	
}
