package application;
	
import application.mail.GestionMail;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;



public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {

			ScrollPane sp = new ScrollPane();
			
			
			VBox vb = new GestionMail();
			
			
			
			sp.setContent(vb);
			vb.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 1000, 1000);
			stage.setTitle("test");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}