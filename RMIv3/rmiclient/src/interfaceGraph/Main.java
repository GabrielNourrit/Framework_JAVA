package interfaceGraph;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

import util.Groupe;
import util.Type;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Utilisateur;

public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, NotBoundException {
		launch(args);
		/*Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		GestionFichierInterface connex = (GestionFichierInterface) registry.lookup("Fichier");
		/*byte[] bytes = Files.readAllBytes(Paths.get("test.txt"));
		System.out.println(connex.enregisterFichier("test.txt",bytes));
		byte[] bytes=connex.sauvegarderFichier("ressources/ganttCahierDesCharges.pdf");
		try{
            Files.write(Paths.get("ganttCahierDesCharges.pdf"),bytes);
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
	}

	@Override
	public void start(Stage stage) throws Exception {
		Utilisateur user = new Utilisateur("metzgegu", "Guillaume","metzger", new Type(1,"Admin"));
		user.addGroupe(new Groupe(1,"Groupe Projet MORAT"));
		user.addGroupe(new Groupe(2,"Groupe Projet A380"));
		VBox vb = new TchatGraphique(user);
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 600, 600);
		stage.setResizable(false);
		stage.setTitle("ChatBox");
		stage.setScene(scene);
		stage.show();		
	}

}
