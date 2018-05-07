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
	private Map<Integer ,Tchat> listener = new HashMap<>();
	private Utilisateur util;
	

	private void execute(TchatInterface tchat) throws RemoteException{ 
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		tchat.envoyerMessage(dateFormat.format(date) + "~" + util.getPrenom() + " " + util.getNom() + "~" + ZoneText.getText() + "§",cbgroupe.getSelectionModel().getSelectedItem().getIdGr());
		ZoneText.setText("");
	}

	/**
	 * Constructeur du tchat graphique
	 * @param util l l'utilisateur courant qui va utiliser le tchat, grace a cela on connaitra les droits de l'utilisateur et ses groupes
	 * @throws AccessException
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
			int gr = g.getIdGr();
			listener.put(gr,l);
			connex.addTchatListener(l,gr);
		}
		ajouterMessage(connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getIdGr()));		
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
				ajouterMessage((connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getIdGr())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void ajouterMessage(String message) {
		String[] str = message.split("§");	
		String[] s;
		String nom;
		for (String u : str) {
			if (!u.equals("")) {
				s = u.split("~");
				if (s[1].equals(util.getPrenom() + " " + util.getNom())) nom = "Moi";
				else nom = s[1];
				vboxInfo.getChildren().addAll(new Label(s[0]+ " " + nom));
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
		public void nouveauMessage(String message, Integer groupe) throws RemoteException {
			if (groupe==cbgroupe.getSelectionModel().getSelectedItem().getIdGr()) {
				Platform.runLater(
						() -> {
							ajouterMessage(message);
						}
				);
			}			
		}
	}
}
