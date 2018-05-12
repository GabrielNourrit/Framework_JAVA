package interfaceGraph;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.GestionTypeInterface;
import BaseDeDonnee.gestionUtilisateur.TypesInterface;
import fichier.GestionFichierInterface;
import groupes.GroupesInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.Connectable;
import util.Droit;
import util.Type;
import util.Utilisateur;

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
		GestionTypeInterface connex;
		try {
			Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
            connex = (GestionTypeInterface) registry.lookup("Types");
			lstDroitNonInscrit = connex.getAllDroitNotInType(type.getIdType());
			lstDroitInscrit = connex.getAllDroitInType(type.getIdType());
			
			
			olstDroit = FXCollections.observableArrayList(lstDroitNonInscrit);
			droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
			
			//droitNonInscrit.setItems(FXCollections.observableArrayList(lstDroitNonInscrit));
			
			olstDroitInscrit = FXCollections.observableArrayList(lstDroitInscrit);
			droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Impossible de se connecter");
			alert.setHeaderText(null);
			alert.setContentText("Impossible de se connecter");

			alert.showAndWait();
			e.printStackTrace();
		}
		
	}
	
	protected void ecouteurDefaultAction() {
		b_ajouter.setOnAction(event -> {
			Droit d = getDroitNonInscritSelected();
			if (d != null) {
				olstDroitInscrit.add(d);
				olstDroit.remove(d);
				
				droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
				
				GroupesInterface connex;
				try {
					/*Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		            connex = (GroupesInterface) registry.lookup("Groupes");
		            connex.ajouterUtilisateur(type.getIdType(), d.getLibelle());*/
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Impossible de ce connecter");
					alert.setHeaderText(null);
					alert.setContentText("Impossible de ce connecter");

					alert.showAndWait();
					e.printStackTrace();
				}
			} 
		});
		
		b_retirer.setOnAction(event ->{
			Droit d = getDroitInscritSelected();
			if (d != null) {
				olstDroit.add(d);
				olstDroitInscrit.remove(d);
				droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
				
				/*try {
					Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		            connex = (GroupesInterface) registry.lookup("Groupes");
		            connex.supprimerUtilisateur(type.getIdType(), d.getLibelle());
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Impossible de ce connecter");
					alert.setHeaderText(null);
					alert.setContentText("Impossible de ce connecter");

					alert.showAndWait();
					e.printStackTrace();
				}*/
				
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
