package interfaceGraph;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import groupes.GroupesInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.Groupe;
import util.Utilisateur;

public class ListUtilisateursGroupe extends CreerGroup {

	private Groupe groupe;
	
	public ListUtilisateursGroupe(Groupe _groupe) {
		
		super();
		groupe= _groupe;
		text1.setText(groupe.getLibelle());
		text1.setEditable(false);
		refreshList();
		b_valider.setText("Modifier");
		label3.setText("Modification du groupe");
		ecouteurDefaultAction();
	}
	
	@Override
	protected void refreshList() {
		GroupesInterface connex;
		try {
			Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
            connex = (GroupesInterface) registry.lookup("Groupes");
            List<String> lstStringUserNotInscrit = connex.getAllLoginNotInGroupe(groupe.getidGr());
			List<Utilisateur> lstUserNonInscrit = new ArrayList<>();
			for (String s: lstStringUserNotInscrit) {
				Utilisateur u = new Utilisateur(s);
				lstUserNonInscrit.add(u);
			}
			olstUser = FXCollections.observableArrayList(lstUserNonInscrit);
			utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
			
			List<String> lstStringUser = connex.getAllLogin(groupe.getidGr());
			List<Utilisateur> lstUserInscrit = new ArrayList<>();
			for (String s: lstStringUser) {
				Utilisateur u = new Utilisateur(s);
				lstUserInscrit.add(u);
			}
			olstUserInscrit = FXCollections.observableArrayList(lstUserInscrit);
			utilisateurInscrit.setItems((ObservableList<Utilisateur>) olstUserInscrit);
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
			Utilisateur u = getUserNonInscritSelected();
			if (u != null) {
				olstUserInscrit.add(u);
				olstUser.remove(u);
				
				utilisateurInscrit.setItems((ObservableList<Utilisateur>) olstUserInscrit);
				
				GroupesInterface connex;
				try {
					Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		            connex = (GroupesInterface) registry.lookup("Groupes");
		            connex.ajouterUtilisateur(groupe.getidGr(), u.getLogin());
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
			Utilisateur u = getUserInscritSelected();
			if (u != null) {
				olstUser.add(u);
				olstUserInscrit.remove(u);
				utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
				GroupesInterface connex;
				try {
					Registry registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
		            connex = (GroupesInterface) registry.lookup("Groupes");
		            connex.supprimerUtilisateur(groupe.getidGr(), u.getLogin());
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
