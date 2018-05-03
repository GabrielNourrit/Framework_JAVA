package interfaceGraph;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import connexion.OperationUtilisateurInterface;
import connexion.Utilisateur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ModifierUtilisateur extends Formulaire {

	private Utilisateur utilisateur;
	private Label l_login;
	private Label l_nom;
	private Label l_prenom;
	private Label l_titre;
	private TextField t_login;
	private TextField t_nom;
	private TextField t_prenom;
	private Button b_valider;
	private int choix;
	
	/**
	 * Constructeur de la classe ModifierUtilisateur
	 * @param _utilisateur l'utilisateur à modifié
	 */
	public ModifierUtilisateur(Utilisateur _utilisateur) {
		super();
		utilisateur = _utilisateur;
		if (utilisateur != null) {
			genererSousComposant();
			ecouteurDefaultAction();
			layoutDefaultParametre();
			this.setAlignment(Pos.CENTER);
		}
	}


	@Override
	protected void genererSousComposant() {
		form = new VBox();
		try {
			t_login = new TextField(utilisateur.getLogin());
			
			t_nom = new TextField(utilisateur.getNom());
			t_prenom = new TextField(utilisateur.getPrenom());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		t_login.setDisable(true);
		
		b_valider= new Button("Modifier");
		l_login = new Label("Login : ");
		l_nom = new Label("Nom : ");
		l_prenom = new Label("Prenom : ");
		l_titre = new Label("Modifier Utilisateur");
	}

	@Override
	protected void ecouteurDefaultAction() {
		b_valider.setOnAction(event ->{
			if (!(t_nom.getText().equals(utilisateur.getNom()))) {
				OperationUtilisateurInterface connex = connectToServeur();
				if (connex != null) {
					
				}
			}
			if (!(t_prenom.getText().equals(utilisateur.getPrenom()))) {
				OperationUtilisateurInterface connex = connectToServeur();
				if (connex != null) {
					
				}
			}
		});
	}

	@Override
	protected void layoutDefaultParametre() {
		/*petite mise en page de notre box*/
		form.getChildren().addAll(l_titre,l_login,t_login,l_nom,t_nom,l_prenom,t_prenom,b_valider);
		form.setMaxSize(200, 200);
		form.setSpacing(3);
		form.setAlignment(Pos.CENTER);
		this.getChildren().add(form);	
	}
	
	private OperationUtilisateurInterface connectToServeur() {
		Registry registry;
		try {
			registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
			OperationUtilisateurInterface connex = (OperationUtilisateurInterface) registry.lookup("OperationUtilisateur");
			return connex;
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
