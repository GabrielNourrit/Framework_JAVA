package interfaceGraph;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

import interfaceGraph.mail.GestionMail;
import interfaceGraph.sondage.SondaGeneral;
import interfaceGraph.sondage.SondageBouton;
import util.Fenetre;
import util.Groupe;
import util.Type;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parametrage.SettingClientJVM;
import util.Utilisateur;

public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, NotBoundException {
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
		
		Utilisateur user = new Utilisateur("polo", "polo","polo", new Type(1,"Admin"));
		user.addGroupe(new Groupe(1,"Groupe General"));
		//user.addGroupe(new Groupe(2,"Groupe Projet A380"));
		//VBox vb = new ModifierUtilisateur(user);
		VBox vb2 = new VBox();
		vb2.getChildren().addAll(new SondageBouton(user), new SondaGeneral(user));
		VBox vb = new PoseFichier(user);
		vb2.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb2, 600, 600);
		stage.setResizable(false);
		stage.setTitle("Telechargement");
		Fenetre.paramStage(stage,scene);
		stage.show();		
	}

}
