package application;

import javafx.scene.Group;
import javafx.scene.control.Button;

public class ConnexionStyle2 extends ConnexionStyle{
	private Button boutonOk;	
	
	public ConnexionStyle2(){
		super();
		this.boutonOk= new Button("se connecter");
		//place
		this.boutonOk.setLayoutX(120);
		this.boutonOk.setLayoutY(75);
		//ecouteur
		this.boutonOk.setOnAction(event ->{
			
			/*Traitement de l'appli*/
			System.out.println("id est "+this.id.getText());
			System.out.print("mdp est "+this.mdp.getText());
			
			/*On efface les anciennes valeures une fois finie*/
			this.id.setText("");
			this.mdp.setText("");
		});
		
		
		this.form.getChildren().add(boutonOk);
	}
	
	
	public ConnexionStyle2(Group pere, int X, int Y){
		//generation de notre formulaire
		this();
		//mise en page form
		this.getStyleForm().setLayoutX(X);
		this.getStyleForm().setLayoutY(Y);
		
		//Attachement de notre form a notre environnement test
		pere.getChildren().add(this.getStyleForm());
	}
		
}

