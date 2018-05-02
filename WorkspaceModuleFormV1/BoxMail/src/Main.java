import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ScrollPane sp = new ScrollPane();
		
		VBox vb = new AffichageMessage();
		
		
		sp.setContent(vb);
		vb.setAlignment(Pos.CENTER);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		Scene scene = new Scene(sp, 600, 600);
		stage.setResizable(false);
		stage.setTitle("Envoyer un message");
		stage.setScene(scene);
		stage.show();
		
	}

}