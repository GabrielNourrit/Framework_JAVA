package interfaceGraph.mail;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaceGraph.Composition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mail.MelCell;
import mail.MelInterface;
import util.Utilisateur;

public class GestionMail extends Composition{
	private TableView<MelCell> t;
	private TableColumn<MelCell,String> date;
	private TableColumn<MelCell,String> objet;
	private TableColumn<MelCell,String> mail;
	private Button sup;
	private Button snd;
	private HBox toolb;
	private Utilisateur moi;
	private MelInterface mel;
	private MelCell selected;
	private ObservableList<MelCell> list;
	private ComboBox<String> chooseComboBox;

	public GestionMail(Utilisateur u) throws RemoteException, NotBoundException{
		this.moi = u;
		Registry registry = java.rmi.registry.LocateRegistry.getRegistry("127.0.0.1",1099);
		this.mel = (MelInterface) registry.lookup("Mel");
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}


	@Override
	protected void genererSousComposant() {
		sup = new Button("supprimer");
		snd = new Button("nouveau");
		toolb = new HBox();
		t = new TableView<MelCell>();
		date = new TableColumn<MelCell,String>("Date");
		objet = new TableColumn<MelCell,String>("Objet");
		mail = new TableColumn<MelCell,String>("Expediteur");
		comp = new VBox();
		this.chooseComboBox = new ComboBox<String>();
		/*
		 * Chargement des données en
		 * Table Cell ici
		 * */

		//	mel.add(new TableCell().);
		list = chargement();
		t.setItems(list);

		try{
			initialiserComboBox();
			}catch(Exception e){
				System.out.println("Oups : SOD is in a lonely day");
				e.printStackTrace();
			}
	}

	private void initialiserComboBox() throws Exception{
		this.chooseComboBox.setEditable(false);
		this.chooseComboBox.getItems().add("Reception");
		this.chooseComboBox.getItems().add("Envoyer");
        this.chooseComboBox.setEditable(false);  
		this.chooseComboBox.setValue("Reception");
       // this.chooseComboBox.setText("Objet : ");
	}
	
	private ObservableList<MelCell> chargement(){
		List<MelCell> m = new ArrayList<>();
		try {
			m = mel.chargerMails(moi.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//MelCell mel1 = new MelCell("15-02-18", "[L3-MIAG] Rendu TP2", "ressource/stockage/"+this.moi.getLogin()+"4","thomas guevara #guevarat");
		//MelCell mel2 = new MelCell("16-02-18", "Carglass répare Carglass Remplace", "essource/stockage/"+this.moi.getLogin(),"thomas guevara #guevarat"); 
		ObservableList<MelCell> list = FXCollections.observableArrayList(m);

		date.setCellValueFactory(new PropertyValueFactory<MelCell, String>("date"));
		mail.setCellValueFactory(new PropertyValueFactory<MelCell, String>("expediteur"));
		objet.setCellValueFactory(new PropertyValueFactory<MelCell, String>("objet"));
		return list; 
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.snd.setOnAction(event ->{
			ScrollPane sp = new ScrollPane();
			VBox reponse= new WriteMessage(this.moi);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			sp.setContent(reponse);
			reponse.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 600, 600);
			stage.setScene(scene);
		});

		this.chooseComboBox.setOnAction(ccb->{
			List<MelCell> m = new ArrayList<>();
			try {
				if(this.chooseComboBox.getValue() == "Reception") {
					m = mel.chargerMails(moi.getLogin());
					mail.setText("Expediteur");
				}else {
					//System.out.println(moi.getLogin());
					m = mel.chargerMailsExp(moi.getLogin());
					mail.setText("Receveur");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			ObservableList<MelCell> list = FXCollections.observableArrayList(m);
			t.setItems(list);
		});
			
			
		
		t.setRowFactory( tv -> {
			TableRow<MelCell> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					MelCell rowData = row.getItem();
					VBox a = null;
					try {
						System.out.println("clic clic");
						a = new AffichageMessage(moi,rowData);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ScrollPane sp = new ScrollPane();
					sp.setContent(a);
					Stage nouveauStage = new Stage();
					a.setAlignment(Pos.CENTER);
					sp.setFitToWidth(true);
					sp.setFitToHeight(true);
					Scene scene = new Scene(sp, 550, 550);
					nouveauStage.setScene(scene);
					nouveauStage.show();
					//System.out.println(rowData.getMail());
				}
				MelCell rowData = row.getItem();
				this.selected=rowData;
			});
			return row ;
		});
		
		sup.setOnAction(e->{
			try {
				mel.supprMail(selected.getId(), selected.getExpediteur());
				list = chargement();
				t.setItems(list);
			} catch (Exception e1) {
				System.out.println("erreur");
			}
			//System.out.println(selected.getMail());
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




}
