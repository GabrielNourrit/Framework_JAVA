package interfaceGraph;

import java.util.List;
import java.util.Optional;

import BaseDeDonnee.gestionUtilisateur.OperationUtilisateurInterface;
import BaseDeDonnee.gestionUtilisateur.UtilisateursInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Fenetre;
import util.Utilisateur;
import util.Connectable;

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
	 * Retourne le user selected
	 * @return
	 */
	public Utilisateur getUserSelected() {
		if (lst_user.getSelectionModel() != null) {
			return lst_user.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}
	
	public ListeUtilisateur(){
		super();
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();	
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
				Fenetre.paramStage(windowModifier,scene);
				windowModifier.show();
			}else {
				Fenetre.creatAlert(AlertType.WARNING,"Attention !","Veuillez selectionnez un utilisateur");
			}
		});
		
		btn_supprimer.setOnAction(event ->{
			Utilisateur user = getUserSelected();
			if (user!= null) {			
				Optional<ButtonType> result = Fenetre.creatAlert(AlertType.CONFIRMATION, "Confirmation", "Voulez vous vraiment supprimer cet utilisateur ?");		
				if (result.get() == ButtonType.OK){
				    //Suprimer cet utilisateur
					OperationUtilisateurInterface connex;
					
					try {
						connex = new Connectable<OperationUtilisateurInterface>().connexion("OperationUtilisateur");
						if (connex.SupprimerUtilisateur(user.getLogin())) {
							refreshList();
						} else {
							Fenetre.creatAlert(AlertType.WARNING,"Attention !","Fail");
						}
					} catch (Exception e) {
						Fenetre.creatAlert(AlertType.ERROR,"Information Dialog","Erreur");
					}
				}				
			} 
			else {
				Fenetre.creatAlert(AlertType.WARNING,"Attention !","Veuillez selectionnez un utilisateur");
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
			Fenetre.paramStage(windowModifier,scene);
			windowModifier.show();
		});
	}

	/**
	 * Rafraichi la liste en envoyant une requête au serveur
	 */
	private void refreshList() {
		try {
            UtilisateursInterface connex = new Connectable<UtilisateursInterface>().connexion("Utilisateurs");
			List<Utilisateur> lesUser = connex.getUsers();
			olstUser = FXCollections.observableArrayList(lesUser);
			
			//ObservableList<String> l = lesUser;
			lst_user.setItems((ObservableList<Utilisateur>) olstUser);
		} catch (Exception e) {
			Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
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
	
	
	/**
	 *  @return retourne le formulaire Graphique modifiable a la guise du client.
	 */
	public VBox getStyleForm(){
		return this.form;
	}

}
