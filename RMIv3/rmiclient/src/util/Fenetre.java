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
	
	/**
	 * Premet de construir une fenetre avec les parametre par defaut
	 * @param stage
	 * @param scene
	 */
	public static void paramStage(Stage stage, Scene scene){
		launchWindow(stage, scene,PropertiesClient.getLocalisationCss() + PropertiesClient.getCssDefaut());
	}
	
	/**
	 * Premet de construir une fenetre en donnant le nom d'un fichier css pour donner un style particulie
	 * @param stage
	 * @param scene
	 * @param nomFile : qui doit ce trouvé dans le dossier definit pour les styles
	 */
	public static void paramStageCssRelatif(Stage stage, Scene scene, String nomFile){
		launchWindow(stage,scene,PropertiesClient.getLocalisationCss() +nomFile);
	}
	
	/**
	 * Premet de construir une fenetre en donnant l'URI + nom d'un fichier css pour donner un style particulie
	 * @param stage
	 * @param scene
	 * @param uri : c'est une URL absolut qui comment à la racine de l'ordinateur
	 */
	public static void paramStageCssAbsolut(Stage stage, Scene scene, String url){
		launchWindow(stage , scene,"///" + url);
	}
	
	/**
	 * Constructeur de fenetre avec un logo et un fichier css
	 * @param stage
	 * @param scene
	 * @param uri
	 */
	private static void launchWindow(Stage stage, Scene scene, String url){
		stage.getIcons().add(new Image(PropertiesClient.getLogoImage()));
		scene.getStylesheets().add("file:"+url);
		stage.setScene(scene);
	}
	
	/**
	 * Constructeur d'alert pour permetre une utilisation plus simple et maintenace plus facile
	 * @param typeAlert
	 * @param titre
	 * @param msg
	 * @return Optional<ButtonType>
	 */
	public static Optional<ButtonType> creatAlert(AlertType typeAlert, String titre, String msg){
		Alert alert = new Alert(typeAlert);
		alert.setTitle(titre);
		alert.setHeaderText(null);
		alert.setContentText(msg);		
		alert.getDialogPane().getStyleClass().add("alertFenetre");
		
		Scene scene = alert.getDialogPane().getScene();
		Stage stage = (Stage) scene.getWindow();
		Fenetre.paramStage(stage, scene);
		
		return alert.showAndWait();
	}
	
}
