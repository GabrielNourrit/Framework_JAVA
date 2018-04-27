package application;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnexionStyle extends Formulaire{
	private Group form;
	private Label l_id;
	private Label l_mdp;
	private TextField id;
	private PasswordField mdp;
	
	/**
	 * @description : Cet objet est un formulaire de connexion par defaut
	 */
	public ConnexionStyle(){
		/*generation de notre formulaire*/
		genererSousComposant();
		layoutDefaultParametre();
		ecouteurDefaultAction();
		
		this.form.getChildren().addAll(l_id,id,l_mdp,mdp);
	}
	
	public ConnexionStyle(Group pere, int X, int Y){
		/*generation de notre formulaire*/
		this();		
		/*mise en page form*/
		this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);
		
		/*Attachement de notre form a notre environnement test*/
		pere.getChildren().add(this.getStyleForm());
	
	}
	
	protected void genererSousComposant(){
		this.form = new Group();
		this.l_id = new Label("id");
		this.id = new TextField();
		this.l_mdp = new Label("pass");
		this.mdp = new PasswordField();
	}
	
	/*Definie le comportement par defaut de notre interface*/
	protected void ecouteurDefaultAction(){
		this.mdp.setOnAction(event ->{
			
			/*Traitement de l'appli*/
			System.out.println("id est "+id.getText());
			System.out.print("mdp est "+mdp.getText());
			
			/*On efface les anciennes valeures une fois finie*/
			id.setText("");
			mdp.setText("");
		});
	}
	
	
	/* Permet de parametrer les valeurs par defauts du layout (peut etre a externaliser)*/
	protected void layoutDefaultParametre(){
		/*petite mise en page de notre box*/
		//l_id
		this.l_id.setLayoutX(10);
		this.l_id.setLayoutY(5);
		//id
		this.id.setLayoutX(50);
		//l_mdp
		this.l_mdp.setLayoutX(10);
		this.l_mdp.setLayoutY(43);
		//mdp
		this.mdp.setLayoutY(40);
		this.mdp.setLayoutX(50);
	}
	
	
	/**
	 * @return retourne le formulaire Graphique modifiable a la guise du client.
	 */
	public Group getStyleForm(){
		return this.form;
	}
	
	
}
