package interfaceGraph;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Alerte extends Composition{

	private Text message;
	private Button continuer;
	private Button changer;
	private HBox hb;
	
	public Alerte(String probleme) {
		genererSousComposant();
		message.setText(probleme);
		this.message.setFont(Font.font("Arial",FontPosture.REGULAR, 20));
		this.message.setWrappingWidth(500);
		this.message.setTextAlignment(TextAlignment.CENTER);;
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	
	
	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		this.message= new Text();
		this.continuer = new Button("Continuer");
		this.changer = new Button("Modifier");
		this.hb = new HBox();
		this.comp = new VBox();
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		this.changer.setOnAction(event ->{
			Stage stage = (Stage) this.changer.getScene().getWindow();
		    stage.close();
			}
		);
	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		hb.getChildren().addAll(this.continuer,this.changer);
		hb.setSpacing(25);
		hb.setAlignment(Pos.CENTER);
		comp.getChildren().addAll(this.message,hb);
		comp.setAlignment(Pos.CENTER);
		comp.setSpacing(20);
		
		this.getChildren().add(comp);
	}

}
