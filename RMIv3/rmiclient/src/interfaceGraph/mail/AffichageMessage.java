package interfaceGraph.mail;
import interfaceGraph.Composition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mail.Utilisateur;

public class AffichageMessage extends Composition{

	private Label expediteur;
	private Label e;
	private Label date;
	private Label d;
	private Label objet;
	private Label o;
	private Label msg;
	private VBox vb;
	private HBox hbe;
	private HBox hbd;
	private HBox hbo;
	private HBox hbbutton;
	private Utilisateur u;

	private ScrollPane sp;
	private Button retour;
	private Button repondre;
	
	public AffichageMessage(Utilisateur u) {
		this.u=u;
		genererSousComposant();
		lecture();
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}
	
	public void lecture() {
		this.e.setText("thomas.guevara@exemple.fr");
		this.d.setText("23/06/1995");
		this.o.setText("As tu vue les quenouille?");
		this.msg.setText("Salut! \nJe ne sais pas si tu as d�j� vue des quenouilles mais la mienne est magnifique! \nCordialement, \nGuillaume Metzger");
		this.vb.getChildren().add(msg);
	}
	
	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		this.expediteur = new Label("Exp�diteur : ");
		this.e = new Label();
		this.date = new Label("Date de reception : ");
		this.d = new Label();
		this.objet = new Label("Objet : ");
		this.o = new Label();
		this.msg = new Label();
		this.vb = new VBox();
		this.hbe = new HBox();
		this.hbd = new HBox();
		this.hbo = new HBox();
		this.hbbutton = new HBox();
		this.sp = new ScrollPane();
		this.repondre = new Button("R�pondre");
		this.retour = new Button("Retour");
		this.comp = new VBox();
		
		
	}

	@Override
	protected void ecouteurDefaultAction() {
		// TODO Auto-generated method stub
		this.repondre.setOnAction(event ->{
			ScrollPane sp = new ScrollPane();
			VBox reponse= new WriteMessage(this.u,this.e.getText(), this.o.getText());
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			sp.setContent(reponse);
			reponse.setAlignment(Pos.CENTER);
			sp.setFitToWidth(true);
			sp.setFitToHeight(true);
			Scene scene = new Scene(sp, 600, 600);
			stage.setScene(scene);
			}
		);
	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		sp.setMinSize(700, 700);
		sp.setContent(vb);
		sp.setMinSize(500, 400);
		sp.setFitToWidth(true);
		sp.setFitToHeight(true);
		this.msg.setWrapText(true);
		hbe.getChildren().addAll(this.expediteur,this.e);
		hbd.getChildren().addAll(this.date,this.d);
		hbo.getChildren().addAll(this.objet,this.o);
		hbbutton.getChildren().addAll(this.retour,this.repondre);
		hbbutton.setSpacing(350);
		comp.getChildren().addAll(hbe,hbd,hbo,this.sp,hbbutton);
		comp.setMaxSize(500,500);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(this.comp);
	}

}
