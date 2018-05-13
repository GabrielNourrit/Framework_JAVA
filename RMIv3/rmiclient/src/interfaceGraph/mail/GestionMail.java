package interfaceGraph.mail;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import interfaceGraph.Composition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mail.MelCell;
import mail.MelInterface;
import mail.MelListener;
import util.Connectable;
import util.Fenetre;
import util.Utilisateur;

public class GestionMail extends Composition{
	protected ScrollPane sp;
	protected TableView<MelCell> t;
	protected TableColumn<MelCell,String> date;
	protected TableColumn<MelCell,String> objet;
	protected TableColumn<MelCell,String> mail;
	protected Button sup;
	protected Button snd;
	protected HBox toolb;
	private Utilisateur moi;
	private MelInterface mel;
	private MelCell selected;
	protected ObservableList<MelCell> list;
	protected ComboBox<String> chooseComboBox;
	private List<MelCell> listeMailRecu = new ArrayList<>();
	private List<MelCell> listeMailEnvoye = new ArrayList<>();
	private String action = "recu";

	/**
	 * Constructeur de la gestion des mails que l'on a receptionner ou envoyer
	 * Initialisation de de l'utilisateur, de la liste des mails re�u et celle envoyer
	 * Appelle des diff�rentes methodes
	 * @param u reference l'utilisateur
	 * @throws Exception
	 */
	public GestionMail(Utilisateur u) throws Exception{
		this.moi = u;
		this.mel = new Connectable<MelInterface>().connexion("Mel");
		listeMailRecu = mel.chargerMails(moi.getLogin());
		listeMailEnvoye = mel.chargerMailsExp(moi.getLogin());
		mel.addMailEnvoyeListener(new MailListener(), u.getLogin());
		mel.addMailRecuListener(new MailListener(), u.getLogin());
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}


	@Override
	protected void genererSousComposant() {
		sp = new ScrollPane();
		sup = new Button("supprimer");
		snd = new Button("nouveau");
		toolb = new HBox();
		t = new TableView<MelCell>();
		t.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE
				);
		date = new TableColumn<MelCell,String>("Date");
		objet = new TableColumn<MelCell,String>("Objet");
		mail = new TableColumn<MelCell,String>("Expediteur");
		comp = new VBox();
		this.chooseComboBox = new ComboBox<String>();

		chargement(listeMailRecu);
		t.setItems(list);
		sp.setContent(t);
		
		try{
			initialiserComboBox();
		}catch(Exception e){
			System.out.println("Oups : SOD is in a lonely day");
			e.printStackTrace();
		}
	}

	/**
	 * Remplissage d'un comboBox pour avoir le choix entre voire la liste des message re�u et ceux envoyer
	 * @throws Exception
	 */
	private void initialiserComboBox() throws Exception{
		this.chooseComboBox.setEditable(false);
		this.chooseComboBox.getItems().add("Reception");
		this.chooseComboBox.getItems().add("Envoyer");
		this.chooseComboBox.setEditable(false);  
		this.chooseComboBox.setValue("Reception");
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.snd.setOnAction(event ->{				
			ScrollPane sp = new ScrollPane();
			VBox reponse= new WriteMessage(this.moi);
			Stage stage = new Stage();
			sp.setContent(reponse);
			reponse.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 600, 600);
			Fenetre.paramStage(stage,scene);
			stage.show();
		});

		this.chooseComboBox.setOnAction(ccb->{
			try {
				if(this.chooseComboBox.getValue() == "Reception") {
					list = FXCollections.observableArrayList(listeMailRecu);
					mail.setText("Expediteur");
					action="recu";
				}else {
					list = FXCollections.observableArrayList(listeMailEnvoye);
					mail.setText("Receveur");
					action = "envoye";
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			t.setItems(list);
		});


		t.setRowFactory( tv -> {
			TableRow<MelCell> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					MelCell rowData = row.getItem();
					VBox a = null;
					try {
						if (action.equals("recu")) a = new AffichageMessage(moi,rowData,"recu");
						else a = new AffichageMessage(moi,rowData,"envoye");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					ScrollPane sp = new ScrollPane();
					sp.setContent(a);
					Stage nouveauStage = new Stage();
					a.setAlignment(Pos.CENTER);
					sp.setFitToWidth(true);
					sp.setFitToHeight(true);
					Scene scene = new Scene(sp, 550, 550);
					Fenetre.paramStage(nouveauStage,scene);
					nouveauStage.show();
				}
				MelCell rowData = row.getItem();
				this.selected=rowData;
			});
			return row ;
		});

		sup.setOnAction(e->{
			try {
				for (MelCell m : t.getSelectionModel().getSelectedItems()) {
					if (action.equals("recu")) {
						listeMailRecu.remove(m);
						list = FXCollections.observableArrayList(listeMailRecu); 
						mel.supprMailRec(m.getId());
					}
					else {
						listeMailEnvoye.remove(m);
						list = FXCollections.observableArrayList(listeMailEnvoye);
						mel.supprMailExp(m.getId());
					}
				}		
				t.setItems(list);
			} catch (Exception e1) {
				System.out.println("erreur");
			}
		});
	} 

	@SuppressWarnings("unchecked")
	@Override
	protected void layoutDefaultParametre() {
		this.toolb.getChildren().addAll(this.chooseComboBox,this.sup, this.snd);
		this.toolb.setSpacing(15);
		this.toolb.setAlignment(Pos.CENTER);
		this.t.getColumns().addAll(this.date,this.objet,this.mail);
		this.comp.getChildren().addAll(this.t,this.toolb);
		this.comp.setSpacing(5);
		this.getChildren().add(comp);
	}

	/**
	 * Ajout en temps et en heure des messages que l'on re�ois sans avoir a relancer le serveur
	 * @param message reference les informations d'un mail
	 */
	protected  void ajouterMailRecu(MelCell message) {
		listeMailRecu.add(message);
		chargement(listeMailRecu);
		if (action.contentEquals("recu")) t.setItems(list);
	}

	/**
	 * Ajout en temps et en heure des messages que l'on a envoyer sans avoir a relancer le serveur
	 * @param message reference les informations d'un mail
	 */
	protected  void ajouterMailEnvoye(MelCell message) {
		listeMailEnvoye.add(message);
		chargement(listeMailEnvoye);
		if (action.contentEquals("envoye")) t.setItems(list);
	}
	
	/**
	 * On affiche la liste des messages re�u selon notre identifiant
	 * Les information que l'on affiche sont: La date, l'expediteur et l'objet
	 * @param mc reference une liste de mail
	 */
	private void chargement(List<MelCell> mc){
		list = FXCollections.observableArrayList(mc);
		date.setCellValueFactory(new PropertyValueFactory<MelCell, String>("date"));
		mail.setCellValueFactory(new PropertyValueFactory<MelCell, String>("expediteur"));
		objet.setCellValueFactory(new PropertyValueFactory<MelCell, String>("objet"));
	}

	/**
	 * sous classe implementant un Listener
	 */
	private class MailListener extends UnicastRemoteObject implements MelListener {
		private static final long serialVersionUID = 1L;
		protected MailListener() throws RemoteException {
			super();
		}
		@Override
		public void nouveauMailRecu(MelCell message) throws RemoteException {
			Platform.runLater(() -> {
				ajouterMailRecu(message);
			});			
		}
		@Override
		public void nouveauMailEnvoye(MelCell message) throws RemoteException {
			Platform.runLater(() -> {
				ajouterMailEnvoye(message);
			});	
		}
	}



}
