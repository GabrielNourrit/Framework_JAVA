package interfaceGraph;

import java.util.List;
import java.util.Optional;

import groupes.GroupesInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Connectable;
import util.Fenetre;
import util.Groupe;

public class GererGroupe extends Formulaire {
	
	private Button btn_ajouter;
	private Button btn_modifier;
	private Button btn_supprimer;
	private HBox hb_btn;
	private ListView<Groupe> lesGroupes;
	private ObservableList<Groupe> obsltGroupes;

	public GererGroupe() {
		super();
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
		
	}
	
	@Override
	protected void genererSousComposant() {
		form = new VBox();
		btn_ajouter = new Button("Ajouter un Groupe");
		btn_modifier = new Button("Ajouter / Supprimer des utilisateurs");
		btn_supprimer = new Button("Supprimer");
		hb_btn = new HBox( btn_modifier, btn_supprimer);
		lesGroupes = new ListView<Groupe>();
		refreshList();
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		btn_supprimer.setOnAction(event ->{
			Groupe groupe = getGroupeSelected();
			if (groupe != null) {
				Optional<ButtonType> result = Fenetre.creatAlert(AlertType.CONFIRMATION, "Confirmation", "Voulez vous vraiment supprimer ce groupe ?");
				if (result.get() == ButtonType.OK){
					try {
						GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
						connex.suprimerGroupe(groupe.getidGr());
						refreshList();
					} catch (Exception e) {
						Fenetre.creatAlert(AlertType.ERROR, "Erreur", "Impossible de supprimer le groupe");
					}
				}
			} else {
				Fenetre.creatAlert(AlertType.WARNING, "Attention !", "Veuillez selectionnez un groupe");
			}
		});
		btn_ajouter.setOnAction(event ->{
			Stage windowAjouter = new Stage();
			CreerGroup vb = new CreerGroup();
			vb.setPostAdd(e -> {
				refreshList();
				windowAjouter.close();
				
			});
			ScrollPane sp = new ScrollPane();
			sp.setContent(vb);
			vb.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 700, 400);
			windowAjouter.setTitle("Ajouter groupe");
			Fenetre.paramStage(windowAjouter,scene);
			windowAjouter.show();
		});
		btn_modifier.setOnAction(event ->{
			Groupe groupe = getGroupeSelected();
			if (groupe != null) {
				Stage windowAjouter = new Stage();
				ListUtilisateursGroupe vb = new ListUtilisateursGroupe(groupe);
				vb.setPostAdd(e -> {
					refreshList();
					windowAjouter.close();
					
				});
				ScrollPane sp = new ScrollPane();
				sp.setContent(vb);
				vb.setAlignment(Pos.CENTER);
				sp.setFitToWidth(true);
				sp.setFitToHeight(true);
				Scene scene = new Scene(sp, 700, 400);
				windowAjouter.setTitle("Modifier un groupe");
				Fenetre.paramStage(windowAjouter,scene);
				windowAjouter.show();
			}
			
		});
	}

	/**
	 * refresh la liste des groupes
	 */
	public void refreshList() {
		try {

			GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
			List<Groupe> groupes = connex.getAllGroupes();
			obsltGroupes = FXCollections.observableArrayList(groupes);
			lesGroupes.setItems(obsltGroupes);
		} catch (Exception e) {
			Fenetre.creatAlert(AlertType.ERROR, "Erreur", "Impossible de rafrechir les groupes");
		}
		
	}

	@Override
	protected void layoutDefaultParametre() {
		form.getChildren().addAll(btn_ajouter,hb_btn,lesGroupes);
		//lst_user.setMaxWidth(400);
		//form.setMaxSize(400, 700);
		btn_ajouter.setAlignment(Pos.CENTER);
		form.setMinWidth(400);
		form.setMaxWidth(400);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);
	}
	
	public Groupe getGroupeSelected() {
		if (lesGroupes.getSelectionModel() != null) {
			return lesGroupes.getSelectionModel().getSelectedItem();
		} else {
			return null;
		}
	}

}
