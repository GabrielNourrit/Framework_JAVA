package interfaceGraph;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox vb = new ListeUtilisateur();
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
			Scene scene = new Scene(sp, 400, 400);
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
