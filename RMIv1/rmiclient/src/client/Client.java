package client;

import java.rmi.*;

import connexion.ConnexionInterface;
import interfaceGraph.ConnexionStyle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

 
public class Client extends Application {
	public static void main (String[] args) {
		//AdditionInterface hello;
		ConnexionInterface c;
		try {
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
	
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			ConnexionStyle c = new ConnexionStyle(root,80,80);
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}