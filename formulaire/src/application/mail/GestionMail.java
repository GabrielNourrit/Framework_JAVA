package application.mail;

import application.Composition;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class GestionMail extends Composition{
	private TableView t;
	private TableColumn date;
	private TableColumn objet;
	private TableColumn mail;
	
	public GestionMail(){
		genererSousComposant();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	
	@Override
	protected void genererSousComposant() {
		t = new TableView();
		date = new TableColumn("Date");
		objet = new TableColumn("Objet");
		mail = new TableColumn("Mail");
		form = new VBox();
	}

	@Override
	protected void ecouteurDefaultAction() {
		
	} 

	@SuppressWarnings("unchecked")
	@Override
	protected void layoutDefaultParametre() {
		this.t.getColumns().addAll(this.date,this.objet,this.mail);
		this.form.getChildren().add(this.t);
		this.form.setMaxSize(500, 200);
		this.form.setSpacing(5);
		this.getChildren().add(form);
	}

	
	
	
}