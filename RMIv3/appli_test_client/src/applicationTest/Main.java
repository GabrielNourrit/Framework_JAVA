package applicationTest;

import interfaceGraph.ConnexionStyle;
import interfaceGraph.Droits;
import interfaceGraph.GererGroupe;
import interfaceGraph.ListeUtilisateur;
import interfaceGraph.PoseFichier;
import interfaceGraph.TchatGraphique;
import interfaceGraph.TelechargerFichier;
import interfaceGraph.mail.GestionMail;
import interfaceGraph.sondage.SondaGeneral;
import interfaceGraph.sondage.SondageBouton;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Fenetre;
import util.Utilisateur;

public class Main extends Application{

	private Utilisateur utilisateur;
	
	private VBox vbConnexion;
	
	private TabPane tab;
	private Tab tchatTab;
	private Tab mailTab;
	private Tab ficPosTab;
	private Tab ficDlTab;
	private Tab ficSoTab;
	
	private TchatGraphique tchat;
	private GestionMail gestionMail;
	private PoseFichier poseFichier;
	private TelechargerFichier telechargerFichier;
	private Button btnDroit;
	private Button btnGroupe;
	private Button btnUtilisateur;
	private VBox vbSondage;
	private SondageBouton sondageBouton;
	private SondaGeneral sondageGeneral;
	
	private Scene scene;
	
	private ConnexionStyle connexion; 
	private Label titreConnexion;
	
	private boolean modifierUtil;
	
	private Button deconnexion;
	
	private ScrollPane spConnexion;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ScrollPane sp = new ScrollPane();
			
			titreConnexion = new Label("Connexion");
			titreConnexion.setStyle("-fx-font: 16px \"Serif\"; -fx-padding: 10;");
			titreConnexion.setAlignment(Pos.TOP_CENTER);
			connexion = new ConnexionStyle();
			connexion.setAlignment(Pos.BOTTOM_CENTER);
			spConnexion = new ScrollPane();
			deconnexion =  new Button("se deconnecter");
			deconnexion.setMinWidth(180);
			deconnexion.setOnAction(event -> {
				utilisateur = null;
				vbConnexion = new VBox(titreConnexion, connexion);
				spConnexion.setContent(vbConnexion);
				vbConnexion.setAlignment(Pos.CENTER);
				tab.getTabs().clear();
				//vbConnexion.setStyle("-fx-background-color: #cc1212;");
				titreConnexion.setText("Connexion");
				connexion.setPostConnectEvent(e ->{
					functionBtnCo();
				});
			});
			vbConnexion = new VBox(titreConnexion, connexion);
			spConnexion.setContent(vbConnexion);
			spConnexion.setFitToWidth(true);
			spConnexion.setFitToHeight(true);
			vbConnexion.setAlignment(Pos.CENTER);
			vbConnexion.setMinHeight(0);
			
			VBox vbleft = new VBox(spConnexion);
			
			tab= new TabPane();
			tchatTab = new Tab("Tchat");
			mailTab = new Tab("Mail");
			ficPosTab = new Tab("Pose Fichiers");
			ficDlTab = new Tab("Telecharger Fichiers");
			ficSoTab = new Tab("Sondages");
			
			//tab.getTabs().add(tchatTab);
			
			VBox vbright = new VBox(tab);
			
			HBox hbMain = new HBox(vbleft, vbright);
			hbMain.setMinWidth(1100);
			hbMain.setMinHeight(700);
			vbleft.setMinWidth(200);
			vbright.setMinWidth(900);
			sp.setContent(hbMain);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			
		//	ActionEvent e = new ActionEvent(event -> {
				
		//	});
			
			connexion.setPostConnectEvent(event ->{
				functionBtnCo();
			});
			scene = new Scene(sp, 1100, 700);
			
			primaryStage.setTitle("test");
			Fenetre.paramStage(primaryStage, scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	private void functionBtnCo() {
		utilisateur = connexion.getUtilisateur();
		if (utilisateur != null) {
			
			try {
				
				if (utilisateur.hasRight("DVC")) {
					tchat = new TchatGraphique(utilisateur);
					tchatTab.setContent(tchat);
					tab.getTabs().add(tchatTab);
				}
				if (utilisateur.hasRight("DM")) {
					gestionMail = new GestionMail(utilisateur);
					mailTab.setContent(gestionMail);
					tab.getTabs().add(mailTab);
				}
				if (utilisateur.hasRight("DTF")) {
					telechargerFichier = new TelechargerFichier(utilisateur);
					ficDlTab.setContent(telechargerFichier);
					tab.getTabs().add(ficDlTab);
				}
				if (utilisateur.hasRight("DPF")) {
					poseFichier = new PoseFichier(utilisateur);
					ficPosTab.setContent(poseFichier);
					tab.getTabs().add(ficPosTab);
				}
				
				if (utilisateur.hasRight("DVS")) {
					vbSondage = new VBox();
					sondageBouton = new SondageBouton(utilisateur);
					sondageGeneral = new SondaGeneral(utilisateur);
					vbSondage.getChildren().addAll(sondageBouton,sondageGeneral);
					ficSoTab.setContent(vbSondage);
					tab.getTabs().add(ficSoTab);
				}
				vbConnexion = new VBox();
				vbConnexion.getChildren().addAll(titreConnexion, deconnexion);
				
				modifierUtil = false;
				if (utilisateur.hasRight("DMT")) {
					btnDroit = new Button("Modifier Types");
					btnDroit.setStyle("-fx-padding: 10; -fx-margin: 10;");
					btnDroit.setMinWidth(180);
					btnDroit.setOnAction(e -> {
						Stage windowModifier = new Stage();
						Droits gererDroits = new Droits(utilisateur);
						ScrollPane spListUtilisateur = new ScrollPane();
						spListUtilisateur.setContent(gererDroits);
						gererDroits.setAlignment(Pos.CENTER);
						spListUtilisateur.setFitToWidth(true);
						spListUtilisateur.setFitToHeight(true);
						Scene scene = new Scene(spListUtilisateur, 400, 400);
						windowModifier.setTitle("Modification");
						Fenetre.paramStage(windowModifier,scene);
						windowModifier.show();
						
					});
					vbConnexion.getChildren().add(btnDroit);
				}
				if (utilisateur.hasRight("DMG")) {
					btnGroupe = new Button("Modifier Groupes");
					btnGroupe.setStyle("-fx-padding: 10; -fx-margin: 10;");
					btnGroupe.setMinWidth(180);
					btnGroupe.setOnAction(e -> {
						Stage windowModifier = new Stage();
						GererGroupe gererGroupe = new GererGroupe();
						ScrollPane spListUtilisateur = new ScrollPane();
						spListUtilisateur.setContent(gererGroupe);
						gererGroupe.setAlignment(Pos.CENTER);
						spListUtilisateur.setFitToWidth(true);
						spListUtilisateur.setFitToHeight(true);
						Scene scene = new Scene(spListUtilisateur, 400, 400);
						windowModifier.setTitle("Modification");
						Fenetre.paramStage(windowModifier,scene);
						windowModifier.show();
					});
					vbConnexion.getChildren().add(btnGroupe);
				}
				if (utilisateur.hasRight("DMU")) {
					btnUtilisateur = new Button("Modifier utilisateur");
					btnUtilisateur.setStyle("-fx-padding: 10; -fx-margin: 10;");
					btnUtilisateur.setMinWidth(180);
					btnUtilisateur.setOnAction(e -> {
						Stage windowModifier = new Stage();
						ListeUtilisateur listeUtilisateur = new ListeUtilisateur();
						ScrollPane spListUtilisateur = new ScrollPane();
						spListUtilisateur.setContent(listeUtilisateur);
						listeUtilisateur.setAlignment(Pos.CENTER);
						spListUtilisateur.setFitToWidth(true);
						spListUtilisateur.setFitToHeight(true);
						Scene scene = new Scene(spListUtilisateur, 400, 400);
						windowModifier.setTitle("Modification");
						Fenetre.paramStage(windowModifier,scene);
						windowModifier.show();
						
					});
					vbConnexion.getChildren().add(btnUtilisateur);
					
				} 
				vbConnexion.setAlignment(Pos.CENTER);
				spConnexion.setContent(vbConnexion);
				
				
				
				titreConnexion.setText("Bonjour "+utilisateur.getLogin());
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
