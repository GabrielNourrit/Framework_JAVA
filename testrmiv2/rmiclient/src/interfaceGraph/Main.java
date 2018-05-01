package interfaceGraph;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		VBox vb = new Tchat();
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 600, 600);
		stage.setResizable(false);
		stage.setTitle("ChatBox");
		stage.setScene(scene);
		stage.show();
		
	}

}
