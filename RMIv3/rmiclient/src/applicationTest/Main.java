package applicationTest;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import interfaceGraph.ConnexionStyle2;
import interfaceGraph.TchatGraphique;
import interfaceGraph.mail.GestionMail;
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
import util.Utilisateur;

public class Main extends Application{

	private Utilisateur utilisateur;
	
	private VBox vbConnexion;
	
	private TabPane tab;
	private Tab tchatTab;
	private Tab mailTab;
	
	private TchatGraphique tchat;
	private GestionMail gestionMail;
	
	
	private Button deconnexion;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//VBox vb = new Inscription();
			//VBox vb = new ConnexionStyle();
			ScrollPane sp = new ScrollPane();
			//VBox vb = new ModifierUtilisateur(new Utilisateur("metzgegu","Guillaume","metzger",null));
			//VBox vb = new Inscription();
			//sp.setContent(vb);
			//vb.setAlignment(Pos.CENTER);
			/*sp.setContent(p);
			//p.setAlignment(Pos.CENTER);*/
			
			Label titreConnexion = new Label("Connexion");
			titreConnexion.setStyle("-fx-font: 16px \"Serif\"; -fx-padding: 10;");
			titreConnexion.setAlignment(Pos.TOP_CENTER);
			ConnexionStyle2 connexion = new ConnexionStyle2();
			connexion.setAlignment(Pos.BOTTOM_CENTER);
			ScrollPane spConnexion = new ScrollPane();
			deconnexion =  new Button("se déconnecter");
			deconnexion.setOnAction(event -> {
				utilisateur = null;
				vbConnexion = new VBox(titreConnexion, connexion);
				spConnexion.setContent(vbConnexion);
				vbConnexion.setAlignment(Pos.CENTER);
				tab.getTabs().clear();
				vbConnexion.setStyle("-fx-background-color: #cc1212;");
			});
			vbConnexion = new VBox(titreConnexion, connexion);
			spConnexion.setContent(vbConnexion);
			spConnexion.setFitToWidth(true);
			spConnexion.setFitToHeight(true);
			vbConnexion.setAlignment(Pos.CENTER);
			
			vbConnexion.setStyle("-fx-background-color: #cc1212;");
			VBox vbleft = new VBox(spConnexion);
			
			tab= new TabPane();
			tchatTab = new Tab("Tchat");
			mailTab = new Tab("Mail");
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
			connexion.setPostConnectEvent(event ->{
				vbConnexion = new VBox(titreConnexion, deconnexion);
				vbConnexion.setStyle("-fx-background-color: #00ea36;");
				vbConnexion.setAlignment(Pos.CENTER);
				spConnexion.setContent(vbConnexion);
				
				utilisateur = connexion.getUtilisateur();
				try {
					tchat = new TchatGraphique(utilisateur);
					gestionMail = new GestionMail(utilisateur);
					tchatTab.setContent(tchat);
					mailTab.setContent(gestionMail);
					tab.getTabs().add(tchatTab);
					tab.getTabs().add(mailTab);
				} catch (RemoteException | ClassNotFoundException | NotBoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				titreConnexion.setText("Bonjour "+utilisateur.getLogin());
			});
			Scene scene = new Scene(sp, 1100, 700);
			//scene.getStylesheets().add("ressources/style.css");
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
