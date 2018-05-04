package interfaceGraph;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;

import fichier.Fichier;
import fichier.GestionFichierInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tchat.TchatInterface;
import util.TransformerFichier;

public class TelechargerFichier extends VBox {
	private HBox form;
	private VBox vb1;
	private VBox vb2;
	private Button button;
	private Label label;
	private ListView<Fichier> list;
	private Registry registry;
	private GestionFichierInterface connex;


	public TelechargerFichier() throws RemoteException, ClassNotFoundException, NotBoundException, SQLException {
		registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (GestionFichierInterface) registry.lookup("Fichier");
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}

	protected void genererSousComposant() {
		button = new Button("Download");
		label = new Label("Filename");
		form = new HBox();
		vb1 = new VBox();
		vb2 = new VBox();
		list = new ListView();
	}

	protected void ecouteurDefaultAction() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		button.setOnAction(event -> {
			//list.getSe
			/*Traitement de l'appli*/
			
			Fichier f = list.getSelectionModel().getSelectedItem();
			try {
				TransformerFichier.byteToFile(connex.download(f.getidFic()), f.getNom());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//System.out.println("id est "+this.id.getText());
			//System.out.print("mdp est "+this.mdp.getText());

			//			TelechargerFichier cherchefichier;
			//					Registry registry;
			//					try {
			//						registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
			//						cherchefichier = (TrouverFichierInterface) registry.lookup("TrouverFichier");
			//						if(cherchefichier.) {
			//							
			//						}
			//					} catch (RemoteException | NotBoundException e) {
			//						// TODO Auto-generated catch block
			//						e.printStackTrace();
			//					}



			//connex = (ConnexionInterface)Naming.lookup("rmi://localhost/Connexion");

			/*On efface les anciennes valeures une fois finie*/


			//s = list.getSelectionModel().getSelectedItem();
			System.out.println("Super!");

		});
	}

	protected void layoutDefaultParametre() throws RemoteException, NotBoundException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int id;
		Fichier s; // item cliquer
		Fichier f = new Fichier(1,"fic1","/");

		
		
		List<Fichier> fs = connex.recupererTousFichiers();
		ObservableList<Fichier> items = FXCollections.observableArrayList (fs);//connex.recupererTousFichiers()); 
		list.setItems(items);
		s = list.getSelectionModel().getSelectedItem();
		vb1.getChildren().add(list);
		vb2.getChildren().addAll(button,label);
		vb1.setAlignment(Pos.CENTER);
		vb2.setAlignment(Pos.CENTER);
		form.getChildren().addAll(vb1,vb2);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);

	}
	public HBox getStyleForm(){
		return this.form;
	}
}
