package interfaceGraph;

import java.util.ArrayList;
import java.util.List;

import groupes.GroupesInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import util.Connectable;
import util.Fenetre;
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
		try {
			GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
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
			Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
		}
		
	}
	
	protected void ecouteurDefaultAction() {
		b_ajouter.setOnAction(event -> {
			Utilisateur u = getUserNonInscritSelected();
			if (u != null) {
				olstUserInscrit.add(u);
				olstUser.remove(u);			
				utilisateurInscrit.setItems((ObservableList<Utilisateur>) olstUserInscrit);
				try {
		            GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
		            connex.ajouterUtilisateur(groupe.getidGr(), u.getLogin());
				} catch (Exception e) {
					Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
				}
			} 
		});
		
		b_retirer.setOnAction(event ->{
			Utilisateur u = getUserInscritSelected();
			if (u != null) {
				olstUser.add(u);
				olstUserInscrit.remove(u);
				utilisateurNonInscrit.setItems((ObservableList<Utilisateur>) olstUser);
				try {
					GroupesInterface connex = new Connectable<GroupesInterface>().connexion("Groupes");
		            connex.supprimerUtilisateur(groupe.getidGr(), u.getLogin());
				} catch (Exception e) {
					Fenetre.creatAlert(AlertType.WARNING, "Impossible de ce connecter", "Impossible de ce connecter");
				}			
			} 
		});
	}
	

}
