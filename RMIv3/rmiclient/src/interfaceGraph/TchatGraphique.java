package interfaceGraph;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fichier.FichierInterface;
import fichier.GestionFichierInterface;
import fichier.Groupe;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tchat.TchatInterface;
import tchat.TchatListener;
import util.LimitedTextField;
import util.Utilisateur;

public class TchatGraphique extends VBox {

	protected LimitedTextField ZoneText;
	protected Button BouttonEnv;
	protected HBox hbTextFButton;
	protected HBox hboxTextTchat;
	protected VBox vboxInfo;
	protected VBox vboxContenu;
	protected ScrollPane sp;
	private TchatInterface connex;
	private GestionFichierInterface connexG;
	private ChoiceBox<Groupe> cbgroupe;
	private Map<String ,Tchat> listener = new HashMap<>();
	private Utilisateur util;// = new Utilisateur("guevarat","Guevara","Thomas","Gars",null);
	

	private void execute(TchatInterface tchat) throws RemoteException{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		System.out.println(cbgroupe.getSelectionModel().getSelectedItem().getLibelle());
		tchat.envoyerMessage(dateFormat.format(date) + "~" + util.getLogin() + "~" + ZoneText.getText() + "§",cbgroupe.getSelectionModel().getSelectedItem().getLibelle());
		System.out.println(dateFormat.format(date) + "~" + util.getLogin() + "~" + ZoneText.getText() + "§");
		ZoneText.setText("");
	}

	public TchatGraphique(Utilisateur util) throws AccessException, RemoteException, NotBoundException, ClassNotFoundException, SQLException{
		super();
		this.util=util;
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		connex = (TchatInterface) registry.lookup("Tchat");	
		connexG = (GestionFichierInterface) registry.lookup("Fichier");
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();
		ecouteurChoixGroupe();
		for (Groupe g : connexG.recupererGroupe(util.getLogin())) {
			Tchat l = new Tchat();
			String gr = g.getLibelle();
			listener.put(gr,l);
			connex.addTchatListener(l,gr);
		}
		//listener = new Tchat();	
		//connex.addTchatListener(listener);
		ajouterMessage(connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getLibelle()));		
	}

	private void genererSousComposant() {
		ZoneText= new LimitedTextField(256);
		BouttonEnv= new Button("Envoyer");
		hbTextFButton= new HBox();
		hboxTextTchat = new HBox();
		vboxInfo = new VBox();
		vboxContenu = new VBox();
		sp = new ScrollPane();
		cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(util.getGroupe()));
		cbgroupe.getSelectionModel().select(0);
		/*cb_groupe = new ChoiceBox<String>(FXCollections.observableArrayList("user","Admin"));
		cb_groupe.getSelectionModel().select(0);*/
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
		hbTextFButton.getChildren().addAll(ZoneText,BouttonEnv);
		this.getChildren().addAll(sp,hbTextFButton,cbgroupe);
	}

	private void ecouteurDefaultAction() {
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
	
	private void ecouteurChoixGroupe() {
		cbgroupe.getSelectionModel().selectedIndexProperty().addListener((ChangeListener<? super Number>) (ov, value, new_value) -> {
			try {
				
				vboxInfo.getChildren().removeAll(vboxInfo.getChildren());
				vboxContenu.getChildren().removeAll(vboxContenu.getChildren());
				cbgroupe.getSelectionModel().select((int) new_value);
				ajouterMessage((connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getLibelle())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void ajouterMessage(String message) {
		String[] str = message.split("§");	
		String[] s;
		for (String u : str) {
			if (!u.equals("")) {
				s = u.split("~");
				vboxInfo.getChildren().addAll(new Label(s[0]+ " " + s[1]));
				vboxContenu.getChildren().add(new Label(s[2]));
			}
		}
	}

	private class Tchat extends UnicastRemoteObject implements TchatListener {

		private static final long serialVersionUID = 1L;

		protected Tchat() throws RemoteException {
			super();
		}

		@Override
		public void nouveauMessage(String message, String groupe) throws RemoteException {
			System.out.println(cbgroupe.getSelectionModel().getSelectedItem().getLibelle());
			if (groupe.equals(cbgroupe.getSelectionModel().getSelectedItem().getLibelle())) {
				Platform.runLater(
						() -> {
							ajouterMessage(message);
						}
				);
			}			
		}
	}
}
