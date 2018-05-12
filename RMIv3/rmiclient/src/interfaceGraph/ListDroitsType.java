package interfaceGraph;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import BaseDeDonnee.gestionUtilisateur.TypesInterface;
import groupes.GroupesInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		TypesInterface connex;
		try {
			Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
            connex = (TypesInterface) registry.lookup("Types");
            List<String> lstStringDroitNotInscrit = connex.getAllLoginNotInType(type.getIdType());
			List<Droit> lstDroitNonInscrit = new ArrayList<>();
			for (String s: lstStringDroitNotInscrit) {
				Droit d = new Droit(s,s);
				lstDroitNonInscrit.add(d);
			}
			olstDroit = FXCollections.observableArrayList(lstDroitNonInscrit);
			droitNonInscrit.setItems((ObservableList<Droit>) olstDroit);
			
			List<String> lstStringUser = connex.getAllLogin(type.getIdType());
			List<Utilisateur> lstUserInscrit = new ArrayList<>();
			for (String s: lstStringUser) {
				Utilisateur u = new Utilisateur(s);
				lstUserInscrit.add(u);
			}
			olstDroitInscrit = FXCollections.observableArrayList(olstDroitInscrit);
			droitInscrit.setItems((ObservableList<Droit>) olstDroitInscrit);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Impossible de ce connecter");
			alert.setHeaderText(null);
			alert.setContentText("Impossible de ce connecter");

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
					Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		            connex = (GroupesInterface) registry.lookup("Groupes");
		            connex.ajouterUtilisateur(type.getIdType(), d.getLibelle());
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
				GroupesInterface connex;
				try {
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
				}
				
			} 
		});
	}
}
