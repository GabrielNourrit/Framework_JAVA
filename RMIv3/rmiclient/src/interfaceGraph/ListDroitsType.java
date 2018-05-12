package interfaceGraph;

import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.GestionTypeInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import util.Connectable;
import util.Droit;
import util.Fenetre;
import util.Type;

public class ListDroitsType extends CreerType{
private Type type;
	
	public ListDroitsType(Type _type) {
		
		super();
		type= _type;
		text1.setText(type.getLibelle());
		text1.setEditable(false);
		refreshList();
		b_valider.setText("Modifier");
		label3.setText("Modification du groupe");
		ecouteurDefaultAction();
	}
	
	@Override
	protected void refreshList() {
		try {
			GestionTypeInterface connex = new Connectable<GestionTypeInterface>().connexion("Types");
			lstDroitNonInscrit = connex.getAllDroitNotInType(type.getIdType());
			lstDroitInscrit = connex.getAllDroitInType(type.getIdType());
			
			
			olstDroit = FXCollections.observableArrayList(lstDroitNonInscrit);
			droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
			
			//droitNonInscrit.setItems(FXCollections.observableArrayList(lstDroitNonInscrit));
			
			olstDroitInscrit = FXCollections.observableArrayList(lstDroitInscrit);
			droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
		} catch (Exception e) {
			Fenetre.creatAlert(AlertType.WARNING, "Impossible de se connecter", "Impossible de se connecter");
		}
		
	}
	
	protected void ecouteurDefaultAction() {
		b_ajouter.setOnAction(event -> {
			Droit d = getDroitNonInscritSelected();
			if (d != null) {
				olstDroitInscrit.add(d);
				olstDroit.remove(d);				
				droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
			} 
		});
		
		b_retirer.setOnAction(event ->{
			Droit d = getDroitInscritSelected();
			if (d != null) {
				olstDroit.add(d);
				olstDroitInscrit.remove(d);
				droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
			} 
		});
		
		b_valider.setOnAction(event->{
			GestionTypeInterface connex;
			List<String> l = new ArrayList<>();
			for (Droit s : olstDroitInscrit) {
				l.add(s.getId());
			}
			try {
				connex = new Connectable<GestionTypeInterface>().connexion("Types");
				connex.modifierType(type, l);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
}
