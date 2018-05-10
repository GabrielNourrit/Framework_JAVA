package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import fichier.GestionFichierInterface;
import util.Connectable;
import util.Groupe;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
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
	protected VBox vboxTextTchat;
	protected VBox vboxInfo;
	protected VBox vboxContenu;
	protected ScrollPane sp;
	private TchatInterface connex;
	private GestionFichierInterface connexG;
	private ChoiceBox<Groupe> cbgroupe;
	private Map<Integer ,Tchat> listener = new HashMap<>();
	private Utilisateur util;
	private boolean droit;
	

	private void execute(TchatInterface tchat) throws RemoteException{ 
		if (!ZoneText.getText().trim().isEmpty()) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			tchat.envoyerMessage(dateFormat.format(date) + "~" + util.getPrenom() + " " + util.getNom() + "~" + ZoneText.getText() + "|",cbgroupe.getSelectionModel().getSelectedItem().getidGr());
			ZoneText.setText("");
		}
	}

	/**
	 * Constructeur du tchat graphique
	 * @param util l l'utilisateur courant qui va utiliser le tchat, grace a cela on connaitra les droits de l'utilisateur et ses groupes
	 * @throws Exception 
	 */
	public TchatGraphique(Utilisateur util) throws Exception{
		super();
		this.util=util;
		if (util.hasRight("DEC")) droit = true;
		else droit=false;
		connex = new Connectable<TchatInterface>().connexion("Tchat");	
		connexG = new Connectable<GestionFichierInterface>().connexion("Fichier");
		
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();
		ecouteurChoixGroupe();
		for (Groupe g : connexG.recupererGroupe(util.getLogin())) {
			Tchat l = new Tchat();
			int gr = g.getidGr();
			listener.put(gr,l);
			connex.addTchatListener(l,gr);
		}
		ajouterMessage(connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getidGr()));		
	}

	private void genererSousComposant() throws Exception {
		ZoneText= new LimitedTextField(256);
		BouttonEnv= new Button("Envoyer");
		hbTextFButton= new HBox();
		vboxTextTchat = new VBox();
		vboxInfo = new VBox();
		vboxContenu = new VBox();
		sp = new ScrollPane();	
		try {
			cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(util.getGroupe()));
		} catch (RemoteException | ClassNotFoundException | NotBoundException | SQLException e) {
			cbgroupe = new ChoiceBox<Groupe>(FXCollections.observableArrayList(new ArrayList<Groupe>()));
		}
		cbgroupe.getSelectionModel().select(0);
	}

	private void layoutDefaultParametre() {
		ZoneText.setMinSize(550, 10);
		vboxInfo.setStyle("-fx-border-style: solid; -fx-border-width: 0 1 0 0; -fx-border-color: grey; -fx-padding: 0 10 0 0");
		vboxContenu.setStyle("-fx-padding: 0 0 0 5");
		vboxContenu.setMaxWidth(200);
		//vboxTextTchat.getChildren().addAll(vboxInfo,vboxContenu);
		sp.setContent(vboxTextTchat);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setMinSize(500, 400);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		if (droit) hbTextFButton.getChildren().addAll(ZoneText,BouttonEnv);
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
				ajouterMessage((connex.getHistorique(cbgroupe.getSelectionModel().getSelectedItem().getidGr())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void ajouterMessage(String message) {
		String[] str = message.split(Pattern.quote("|"));	
		String[] s;
		String nom;
		TextArea t;
		for (String u : str) {
			if (!u.equals("")) {
				s = u.split("~");
				if (s[1].equals(util.getPrenom() + " " + util.getNom())) nom = "Moi";
				else nom = s[1];
				t = new TextArea();
				t.setText(s[0]+ " " + nom + " : " + s[2]);
				vboxTextTchat.getChildren().add(t);
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
			if (groupe==cbgroupe.getSelectionModel().getSelectedItem().getidGr()) {
				Platform.runLater(
						() -> {
							ajouterMessage(message);
						}
				);
			}			
		}
	}
}
