package test;

import interfaceGraph.CreerGroup;
import interfaceGraph.GererGroupe;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class TestGroupe extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			GererGroupe vb = new GererGroupe();
			/*vb.setPostConnectEvent(event ->{
				if (vb.getUtilisateur()!= null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					
					alert.setContentText(vb.getUtilisateur().getLogin());
	
					alert.showAndWait();
					
				}
			});*/
			//VBox vb = new ConnexionStyle();
			ScrollPane sp = new ScrollPane();
			//VBox vb = new ModifierUtilisateur(new Utilisateur("metzgegu","Guillaume","metzger",null));
			//VBox vb = new Inscription();
			sp.setContent(vb);
			vb.setAlignment(Pos.CENTER);
			/*sp.setContent(p);
			//p.setAlignment(Pos.CENTER);*/
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 670, 400);
			primaryStage.setTitle("test");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
