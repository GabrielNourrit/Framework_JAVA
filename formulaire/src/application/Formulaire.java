package application;

import javafx.scene.Parent;

public abstract class Formulaire extends Parent{
	
	/**
	 * @description : genere tout les sous Composant du formulaire
	 */
	protected abstract void genererSousComposant();
	
	/**
	 * @description : definit tout les Actions-Listeners du formulaire
	 */
	protected abstract void ecouteurDefaultAction();

	/**
	 * @description : definit le style par defaut du formulaire
	 */
	protected abstract void layoutDefaultParametre();
	
	
}
