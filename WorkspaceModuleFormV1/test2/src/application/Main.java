package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	
	//stage = fenetre
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			
			//BorderPane root = new BorderPane();
			Group root = new Group();
			Group g2 = new Group();
			//scene c'est la page dans la fenetre avec les dimensions
			Scene scene = new Scene(root,400,400);
			//lecture du css
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			MonComposant c = new MonComposant();
			
			root.getChildren().addAll(g2,c);//ou remove
			//addAll de gauche a droite Zindex croissant
			
			primaryStage.setTitle("gabriel");
			primaryStage.setResizable(false);
			//on choisis la page de notre fenetre
			primaryStage.setScene(scene);
			//on l'affiche
			primaryStage.show();
			
			/* cmd pratique
			 * Platform.exit();
			 */
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
