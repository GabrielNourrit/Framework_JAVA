package interfaceGraph;

import fichier.Fichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TelechargerFichier extends Formulaire{
	private Group form;
	private Button button;
	private Label label;
	private ListView<Fichier> list; 


	public TelechargerFichier(Group pere, int X, int Y) {
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
		this.form.getChildren().addAll(button, label,list);
		this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);
		pere.getChildren().add(this.getStyleForm());
	}

	@Override
	protected void genererSousComposant() {
		button = new Button("Download");
		label = new Label("Filename");
		form = new Group();
		list = new ListView();
	}

	@Override
	protected void ecouteurDefaultAction() {
		int id;
		Fichier s; // item cliquer
		Fichier f = new Fichier(1,"/fic1");



		ObservableList<Fichier> items = FXCollections.observableArrayList (f); 
		list.setItems(items);
		s = list.getSelectionModel().getSelectedItem();
		button.setOnAction(event -> {
			/*Traitement de l'appli*/
			


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

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		list.setPrefWidth(200);  
		list.setPrefHeight(300); 
		this.button.setLayoutX(200);
		this.button.setLayoutY(50);
		this.label.setLayoutX(30);
		this.label.setLayoutY(-120);
		this.list.setLayoutX(-50);
		this.list.setLayoutY(-100);

	}
	public Group getStyleForm(){
		return this.form;
	}
}
