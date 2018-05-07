package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import BaseDeDonnee.gestionUtilisateur.UtilisateursInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Utilisateur;

public class ListeUtilisateur extends Formulaire {
	private ListView<Utilisateur> lst_user;
	private ObservableList<Utilisateur> olstUser;
	private Button btn_modifier;
	private Button btn_supprimer;
	private Button btn_ajouter;
	
	private HBox hBox;
	
	
	/**
	 * Permet in fine de modifier redefinir la méthode action du button
	 * @return le Button modifier
	 */
	public Button getButtonModifier() {
		return btn_modifier;
	}
	
	/**
	 * Permet in fine de modifier redefinir la méthode action du button
	 * @return le Button Supprimer
	 */
	public Button getButtonSupprimer() {
		return btn_supprimer;
	}
	
	/**
	 * Retourne le
	 * @return
	 */
	public Utilisateur getUserSelected() {
		if (lst_user.getSelectionModel() != null) {
			return lst_user.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}
	
	public ListeUtilisateur() {
		super();
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();
		//this.setAlignment(Pos.CENTER);
		
	}
	
	public ListeUtilisateur(Group pere, int X, int Y) {
		this();
		/*mise en page form*/
		//getStyleForm().setLayoutX(X);
		//getStyleForm().setLayoutY(Y);
		
		
		/*Attachement de notre form a notre environnement test*/
		//pere.getChildren().add(this.getStyleForm());
		
	}
	
	
	@Override
	protected void genererSousComposant() {
		form = new VBox();
		
		btn_modifier = new Button("Modifier");
		btn_supprimer = new Button("Supprimer"); 
		btn_ajouter = new Button("Ajouter");
		
		hBox = new HBox();
		hBox.getChildren().addAll(btn_ajouter,btn_modifier,btn_supprimer);
		
		lst_user = new ListView<>();
		
		refreshList();
		/*lst_user.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				return new UtilisateurRow();
			}
		});*/
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		btn_modifier.setOnAction(event ->{
			Utilisateur user = getUserSelected();
			if (user!= null) {
				Stage windowModifier = new Stage();
				ModifierUtilisateur vb = new ModifierUtilisateur(getUserSelected());
				vb.setPostValidEvent(e -> {
					refreshList();
					windowModifier.close();
					
				});
				ScrollPane sp = new ScrollPane();
				sp.setContent(vb);
				vb.setAlignment(Pos.CENTER);
				sp.setFitToWidth(true);
				sp.setFitToHeight(true);
				Scene scene = new Scene(sp, 400, 400);
				windowModifier.setTitle("Modification");
				windowModifier.setScene(scene);
				windowModifier.show();
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attention !");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez selectionnez un utilisateur");
				alert.showAndWait();
			}
		});
		
		btn_supprimer.setOnAction(event ->{
			Utilisateur user = getUserSelected();
			if (user!= null) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText(null);
				alert.setContentText("Voulez vous vraiment supprimer cet utilisateur ?");
				
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
				    //Suprimer cet utilisateur
					OperationUtilisateurInterface connex;
					Registry registry;
					try {
						registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
						connex = (OperationUtilisateurInterface) registry.lookup("OperationUtilisateur");
						if (connex.SupprimerUtilisateur(user.getLogin())) {
							refreshList();
						} else {
							alert = new Alert(AlertType.WARNING);
							alert.setTitle("Attention !");
							alert.setHeaderText(null);
							alert.setContentText("Fail");
							alert.showAndWait();
						}
					} catch (RemoteException | NotBoundException | ClassNotFoundException | SQLException e) {
						Alert alert1 = new Alert(AlertType.ERROR);
						alert1.setTitle("Information Dialog");
						alert1.setHeaderText(null);
						alert1.setContentText("Erreur");
						alert1.showAndWait();
					}
		            
					
				}
				
			} 
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Attention !");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez selectionnez un utilisateur");

				alert.showAndWait();
			}
			
		});
		btn_ajouter.setOnAction(event -> {
			Stage windowModifier = new Stage();
			Inscription vb = new Inscription();
			vb.setPostConnectEvent(e -> {
				refreshList();
				windowModifier.close();
				
			});
			ScrollPane sp = new ScrollPane();
			sp.setContent(vb);
			vb.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 400, 400);
			windowModifier.setTitle("Modification");
			windowModifier.setScene(scene);
			windowModifier.show();
		});
	}

	/**
	 * Rafraichi la liste en envoyant une requête au serveur
	 */
	private void refreshList() {
		UtilisateursInterface connex;
		try {
			Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
            connex = (UtilisateursInterface) registry.lookup("Utilisateurs");
			List<Utilisateur> lesUser = connex.getUsers();
			olstUser = FXCollections.observableArrayList(lesUser);
			
			//ObservableList<String> l = lesUser;
			lst_user.setItems((ObservableList<Utilisateur>) olstUser);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Impossible de ce connecter");
			alert.setHeaderText(null);
			alert.setContentText("Impossible de ce connecter");

			alert.showAndWait();
			e.printStackTrace();
		}
		
	}

	@Override
	protected void layoutDefaultParametre() {
		form.getChildren().addAll(hBox,lst_user);
		//lst_user.setMaxWidth(400);
		//form.setMaxSize(400, 700);
		lst_user.setMinWidth(400);	
		lst_user.setMaxWidth(400);
		form.setMinWidth(400);
		form.setMaxWidth(400);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}
	
	
	
	/* @return retourne le formulaire Graphique modifiable a la guise du client.*/
	public VBox getStyleForm(){
		return this.form;
	}

}
