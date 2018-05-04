package client;

import java.rmi.*;

import interfaceGraph.ConnexionStyle;
import interfaceGraph.ConnexionStyle2;
import interfaceGraph.Inscription;
import interfaceGraph.mail.WriteMessage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mail.Utilisateur;
import parametrage.SettingClientJVM;
import javafx.application.Application;
import javafx.geometry.Pos;

 
public class Client extends Application {
	/*protected Pane p;
	
	public Client(Pane pane) {
		p = pane;
	}*/
	
	public static void main (String[] args) {
		//AdditionInterface hello;
		//ConnexionInterface c;
		try {
			SettingClientJVM.configureProperty();
			SettingClientJVM.useSecurityManager();
			
			/*hello = (AdditionInterface)Naming.lookup("rmi://localhost/ABC");
			int result=hello.add(9,10);
			System.out.println("Result is :"+result);*/
			
			/*c = (ConnexionInterface)Naming.lookup("rmi://localhost/c");
			System.out.println(c.verifierMdp("metzgegu","01234"));*/
			
			launch(args);

			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("HelloClient exception: " + e);
				}
		}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		//BorderPane sp = new BorderPane();
		ScrollPane sp = new ScrollPane();
		//VBox vb = new ConnexionStyle();
		VBox vb = new WriteMessage(new Utilisateur("guevarat","guevara","thomas","ennerver"));
		//VBox vb = new Inscription();
		sp.setContent(vb);
		vb.setAlignment(Pos.CENTER);
		/*sp.setContent(p);
		//p.setAlignment(Pos.CENTER);*/
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		Scene scene = new Scene(sp, 200, 200);
		stage.setTitle("test");
		stage.setScene(scene);
		stage.show();
		
	}
}