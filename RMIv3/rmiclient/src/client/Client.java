package client;

import interfaceGraph.ConnexionStyle;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parametrage.SettingClientJVM;
import util.Fenetre;
import javafx.application.Application;
import javafx.geometry.Pos;

 
public class Client extends Application {
	
	public static void main (String[] args) {
		try {
			SettingClientJVM.configureProperty();
			SettingClientJVM.useSecurityManager();
			
			launch(args);

			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("HelloClient exception: " + e);
				}
		}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		ScrollPane sp = new ScrollPane();
		VBox vb = new ConnexionStyle();
		//VBox vb = new Inscription();
		sp.setContent(vb);
		vb.setAlignment(Pos.CENTER);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		Scene scene = new Scene(sp, 200, 200);
		stage.setTitle("Connexion");
		Fenetre.paramStage(stage,scene);
		stage.show();
	}
}