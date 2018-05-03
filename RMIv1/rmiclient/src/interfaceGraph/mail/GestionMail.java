package interfaceGraph.mail;

import interfaceGraph.Composition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GestionMail extends Composition{
	private TableView<melCell> t;
	private TableColumn<melCell,String> date;
	private TableColumn<melCell,String> objet;
	private TableColumn<melCell,String> mail;
	private Button sup;
	private Button snd;
	private HBox toolb;
	
	public GestionMail(){
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	
	@Override
	protected void genererSousComposant() {
		sup = new Button("supprimer");
		snd = new Button("nouveau");
		toolb = new HBox();
		t = new TableView<melCell>();
		date = new TableColumn<melCell,String>("Date");
		objet = new TableColumn<melCell,String>("Objet");
		mail = new TableColumn<melCell,String>("Mail");
		form = new VBox();
		/*
		 * Chargement des données en
		 * Table Cell ici
		 * */
		
	//	mel.add(new TableCell().);
		ObservableList<melCell> list = chargement();
        t.setItems(list);
        
	}
	
	private ObservableList<melCell> chargement(){
		melCell mel1 = new melCell("15-02-18", "[L3-MIAG] Rendu TP2", "gabriel.nrt@hotmail.fr");
		melCell mel2 = new melCell("16-02-18", "Carglass répare Carglass Remplace", "carglass@arnaqlapost.gouv.fr");
		 
        ObservableList<melCell> list = FXCollections.observableArrayList(mel1, mel2);
        
        date.setCellValueFactory(new PropertyValueFactory<melCell, String>("date"));
        mail.setCellValueFactory(new PropertyValueFactory<melCell, String>("mail"));
        objet.setCellValueFactory(new PropertyValueFactory<melCell, String>("objet"));
        return list; 
	}

	@Override
	protected void ecouteurDefaultAction() {
		
	} 

	@SuppressWarnings("unchecked")
	@Override
	protected void layoutDefaultParametre() {
		this.toolb.getChildren().addAll(this.sup, this.snd);
		this.toolb.setSpacing(15);
		this.toolb.setAlignment(Pos.CENTER);
		this.t.getColumns().addAll(this.date,this.objet,this.mail);
		this.form.getChildren().addAll(this.t,this.toolb);
		this.form.setSpacing(5);
		this.getChildren().add(form);
	}

	
	
	
}
