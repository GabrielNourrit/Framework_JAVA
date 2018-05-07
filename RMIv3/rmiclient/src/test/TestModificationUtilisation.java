package test;

import java.util.ArrayList;
import java.util.List;

import fichier.Groupe;
import interfaceGraph.ModifierMotDePasse;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Utilisateur;

public class TestModificationUtilisation extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			List<Groupe> s = new ArrayList<>();
			s.add(new Groupe(1,"Groupe Projet MORAT"));
			s.add(new Groupe(2,"Groupe Projet A380"));
			VBox vb = new ModifierMotDePasse(new Utilisateur("metzgegu","Guillaume","metzger",null,s));
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