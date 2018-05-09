package interfaceGraph.mail;

import java.rmi.RemoteException;
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
import mail.MelCell;
import mail.MelInterface;
import util.Connectable;
import util.Fenetre;
import util.Utilisateur;


public class AffichageMessage extends Composition{

	protected Label expediteur;
	protected Label e;
	protected Label date;
	protected Label d;
	protected Label objet;
	protected Label o;
	protected Label msg;
	protected VBox vb;
	protected HBox hbe;
	protected HBox hbd;
	protected HBox hbo;
	protected HBox hbbutton;
	private Utilisateur u;

	private ScrollPane sp;
	private Button repondre;
	private MelInterface mailInterface;
	private String type;

	public AffichageMessage(Utilisateur u, MelCell mc, String t) throws Exception {
		this.u=u;
		type=t;
		//mailInterface = (MelInterface) registry.lookup("Mel");
		mailInterface = new Connectable<MelInterface>().connexion("Mel");
		genererSousComposant();
		lecture(mc);
		ecouteurDefaultAction();
		layoutDefaultParametre();
	}

	public void lecture (MelCell mc) throws RemoteException {
		this.e.setText(mc.getExpediteur());
		this.d.setText(mc.getDate());
		this.o.setText(mc.getObjet());
		this.msg.setText(mailInterface.chargerMessage(mc.getId()+""));
		this.vb.getChildren().add(msg);
	}

	@Override
	protected void genererSousComposant() {
		if (type.equals("recu")) this.expediteur = new Label("Expediteur : ");
		else this.expediteur = new Label("Destinataire : ");
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
		this.repondre = new Button("Repondre");
		this.comp = new VBox();


	}

	@Override
	protected void ecouteurDefaultAction() {
		this.repondre.setOnAction(event ->{
			ScrollPane sp = new ScrollPane();
			VBox reponse= new WriteMessage(this.u,this.e.getText(), this.o.getText());
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			sp.setContent(reponse);
			reponse.setAlignment(Pos.CENTER);
			Fenetre.paramStage(stage);
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
		hbbutton.getChildren().add(this.repondre);
		comp.getChildren().addAll(hbe,hbd,hbo,this.sp,hbbutton);
		comp.setMaxSize(500,500);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(this.comp);
	}

}