import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AffichagePoint extends Composition{

	
	private Button BouttonVal;
	private Text titre;
	private VBox vb;
	private ToggleGroup group;
	
	public AffichagePoint() {
		genererSousComposant();
		recupere();
		layoutDefaultParametre();
	}
	
	
	private void recupere() {
		String titre = new String("Comment vivre sans toi, sans toit, sans sang et sans sens tel est la question? \n");
		
		ArrayList<String> list= new ArrayList<String>();
		list.add(new String("Pokemon"));
		list.add(new String("yugiho"));
		
		this.titre.setText(titre);
		this.titre.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20));
		this.titre.setWrappingWidth(500);

		for(String e: list) {
			RadioButton rb = new RadioButton(e);
			rb.setToggleGroup(this.group);
			vb.getChildren().add(rb);
		}
		
		 

	}

	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		this.comp= new VBox();
		this.vb= new VBox();
		this.BouttonVal= new Button("Valider");
		this.titre= new Text();
		this.group = new ToggleGroup();
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		comp.getChildren().addAll(this.titre,vb,this.BouttonVal);
		comp.setMaxSize(120, 100);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(this.comp);
	}
	
}
