package interfaceGraph;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tchat.TchatInterface;
import util.LimitedTextField;
import util.Utilisateur;

public class Tchat extends VBox {

	private LimitedTextField ZoneText;
	private Button BouttonEnv;
	HBox hb;
	HBox hboxTextTchat;
	VBox vboxInfo;
	VBox vboxContenu;
	ScrollPane sp;

	private String olds = "";
	private String[] buffer = {};

	//TODO: D�claration automatique de l'utilisateur
	public Utilisateur util = new Utilisateur("guevarat","Guevara","Thomas","Gars");

	private void execute(TchatInterface tchat) throws RemoteException{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		tchat.envoyerMessage(dateFormat.format(date) + "~" + util.getLogin() + "~" + ZoneText.getText() + "�");
		System.out.println(dateFormat.format(date) + "~" + util.getLogin() + "~" + ZoneText.getText() + "�");
		ZoneText.setText("");
	}

	public Tchat() throws AccessException, RemoteException, NotBoundException{
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		TchatInterface connex = (TchatInterface) registry.lookup("Tchat");
		
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction(connex);
		
		majTchat(connex,vboxContenu,vboxInfo,"historique");
				
		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				majTchat(connex,vboxContenu,vboxInfo,"");
			}
		}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
	}
	
	private void genererSousComposant() {
		ZoneText= new LimitedTextField(5);
		BouttonEnv= new Button("Envoyer");
		hb= new HBox();
		hboxTextTchat = new HBox();
		vboxInfo = new VBox();
		vboxContenu = new VBox();
		sp = new ScrollPane();
	}
	
	private void layoutDefaultParametre() {
		ZoneText.setMinSize(550, 10);
		vboxInfo.setStyle("-fx-border-style: solid; -fx-border-width: 0 1 0 0; -fx-border-color: grey; -fx-padding: 0 10 0 0");
		vboxContenu.setStyle("-fx-padding: 0 0 0 5");	
		hboxTextTchat.getChildren().addAll(vboxInfo,vboxContenu);
		sp.setContent(hboxTextTchat);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setMinSize(500, 400);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		hb.getChildren().addAll(ZoneText,BouttonEnv);
		this.getChildren().addAll(sp,hb);
	}
	
	private void ecouteurDefaultAction(TchatInterface connex) {
		ZoneText.setOnAction(event-> {
			try {
				execute(connex);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ZoneText.setText("");
		});

		BouttonEnv.setOnAction(event ->{
			/*Traitement de l'appli*/
			try {
				execute(connex);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			ZoneText.setText("");
		});
	}
	
	private void majTchat(TchatInterface tchat, VBox contenu, VBox info, String type) {
		String[] s;
		String mess;
		try {
			if (type.equals("historique")) mess = tchat.getHistorique();
			else mess = tchat.getMessage();
			if (!mess.equals(olds)) {
				olds=mess;
				String[] str = mess.split("�");					
				for (String u : str) {
					if (!u.equals("")) {
						if (!exist(u,buffer)) {
							s = u.split("~");
							info.getChildren().add(new Label(s[0]+ " " + s[1]));
							contenu.getChildren().add(new Label(s[2]));
						}
					}
				}
				buffer=str;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private boolean exist(String s, String[] str) {
		for (String u : str) {
			if (u.equals(s)) return true;
		}
		return false;
	}
	
	private void addTextLimiter(TextField tf, int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
}
