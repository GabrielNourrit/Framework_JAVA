package interfaceGraph.sondage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sondage.SondageInterface;
import sondage.SondageObj;
import util.Utilisateur;

public class AffichageChoixMulti extends Composition{


	private Button bouttonVal;
	private Text titre;
	private VBox vb;
	private Utilisateur user;
	Registry registry=null;	
	SondageObj so;
	private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();

	public AffichageChoixMulti(Utilisateur user, SondageObj so) {
		this.user=user;
		this.so = so;
		genererSousComposant();
		recupere();
		layoutDefaultParametre();
		ecouteurDefaultAction();
	}


	private void recupere() {

		this.titre.setText(so.getQuestion());
		this.titre.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20));
		this.titre.setWrappingWidth(500);

		int max=so.getReponses().size();


		for(int j=0; j<max ; j+=2){ 
			CheckBox rb = new CheckBox(so.getReponses().get(j));
			rb.setUserData(j+1);
			vb.getChildren().add(rb);
			rb.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
				if (isNowSelected) {
					selectedCheckBoxes.add(rb);
				} else {
					selectedCheckBoxes.remove(rb);
				}

			});
		}

	}

	@Override
	protected void genererSousComposant() {
		// TODO Auto-generated method stub
		this.comp= new VBox();
		this.vb= new VBox();
		this.bouttonVal= new Button("Valider");
		this.titre= new Text();
	}

	private String sondageListToString(ArrayList<String> s) {
		String ret ="";
		for(String i : s) {
			ret+=i+";";
		}
		return ret;
	}

	@Override
	protected void ecouteurDefaultAction() {
		this.bouttonVal.setOnAction(e->{

			SondageInterface connect;
			try {
				ArrayList<String> ret = new ArrayList<String>();
				registry = java.rmi.registry.LocateRegistry.getRegistry(1099);
				connect = (SondageInterface) registry.lookup("Sondage");
				System.out.println(this.selectedCheckBoxes);
				for(CheckBox cb : this.selectedCheckBoxes) {
					int index = Integer.parseInt(cb.getUserData().toString());
					ret.add(so.getReponses().get(index-1));
					/*int index = Integer.parseInt(cb.getUserData().toString());
					int value = Integer.parseInt(so.getReponses().get(index));
					ret = so.getReponses();
					ret.set(index, value+1+"");*/
				}
				System.out.println(ret);
				connect.updateSondage(user.getLogin(),so.getId(), ret);//sondageListToString(ret));

			} catch(NullPointerException ex) {
				ex.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez selectionner une proposition ! ");
				alert.showAndWait();
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	@Override
	protected void layoutDefaultParametre() {
		// TODO Auto-generated method stub
		comp.getChildren().addAll(this.titre,vb,this.bouttonVal);
		comp.setMaxSize(120, 100);
		comp.setSpacing(3);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(this.comp);
	}

}