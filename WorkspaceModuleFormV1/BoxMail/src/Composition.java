
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public abstract class Composition extends VBox{	
	/**
	 * Le formulaire
	 */
	protected VBox comp;
	
	/**
	 * genere tout les sous Composant du formulaire
	 */
	protected abstract void genererSousComposant();
	
	/**
	 * definit tout les Actions-Listeners du formulaire
	 */
	protected abstract void ecouteurDefaultAction();

	/**
	 * definit le style par defaut du formulaire
	 */
	protected abstract void layoutDefaultParametre();
	
	/**
	 * Ajoute le noeud n (Button,Label,etc..) au formulaire
	 * @param n le noeud (Button,Label,etc..) � ajouter
	 */
	public void addElementForm(Node n) {
		comp.getChildren().add(n);
	}
	
	/**
	 * Ajoute le noeud n (Button,Label,etc..) � l'index du formulaire
	 * @param index l'index o� l'on ajoute
	 * @param n le noeud (Button,Label,etc..) � ajouter
	 */
	public void addElementForm(int index, Node n) {
		comp.getChildren().add(index, n);
	}
	
	/**
	 * Supprime le noeud � l'indice index du formulaire
	 * @param index indice du noeud � supprimer
	 */
	public void removeElement(int index) {
		comp.getChildren().remove(index);
	}
	
	/**
	 * Supprime le noeud n du formulaire
	 * @param n le noeud � supprimer
	 */
	public void removeElement(Node n) {
		comp.getChildren().remove(n);
	}
	
}
