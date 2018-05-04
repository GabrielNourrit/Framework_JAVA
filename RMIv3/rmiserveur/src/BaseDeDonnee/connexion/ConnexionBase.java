package BaseDeDonnee.connexion;


public abstract class ConnexionBase {
	protected String link_properties;
	
	protected ConnexionBase(String link_properties){
		this.link_properties = link_properties;
	}
	
}
