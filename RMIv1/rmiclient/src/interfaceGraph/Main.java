package interfaceGraph;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.remote.rmi.RMIServer;

import fichier.FichierInterface;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, NotBoundException {
		//launch(args);
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		FichierInterface connex = (FichierInterface) registry.lookup("Fichier");
		/*byte[] bytes = Files.readAllBytes(Paths.get("test.txt"));
		System.out.println(connex.enregisterFichier("test.txt",bytes));*/
		byte[] bytes=connex.sauvegarderFichier("ressources/ganttCahierDesCharges.pdf");
		try{
            Files.write(Paths.get("ganttCahierDesCharges.pdf"),bytes);
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
