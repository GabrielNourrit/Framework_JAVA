package interfaceGraph.sondage;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.VBox;
import sondage.SondageInterface;
import sondage.SondageListener;
import sondage.SondageObj;
import tchat.TchatInterface;
import util.Connectable;
import util.Groupe;
import util.Utilisateur;

public class SondaGeneral extends Composition{
	private Utilisateur u;
	private ChoiceBox<String> cb;
	private VBox sondageZone;
	private boolean fait;
	private List<SondageObj> currentSondageFait;
	private List<SondageObj> currentSondageNonFait;
	private Map<Integer ,Listener> listener = new HashMap<>();
	private Listener listenerNewS;
	private SondageInterface connect;
	private Map<Integer ,Integer> indiceSondage = new HashMap<>();

	public SondaGeneral(Utilisateur u) throws Exception{
		this.u=u;
		fait=true;
		genererSousComposant();	
		ecouteurDefaultAction();
		layoutDefaultParametre();
		connect = new Connectable<SondageInterface>().connexion("Sondage");	
		currentSondageFait = connect.getSondageFait(u);
		currentSondageNonFait = connect.getSondageNew(u);
		int i = 0;
		for(SondageObj s : 	currentSondageFait) {
			indiceSondage.put(i,s.getId());
			ResultatSondage r = new ResultatSondage(u, s);
			r.setId("sondage");
			sondageZone.getChildren().add(r);
			Listener l = new Listener();
			listener.put(s.getId(),l);
			connect.addTchatListener(l,s.getId());
			i++;
		}
		listenerNewS = new Listener();
		connect.addTchatListenerNewSondage(listenerNewS);
		cb.getSelectionModel().select(0);
	}

	@Override
	protected void genererSousComposant() {
		currentSondageFait = new ArrayList<SondageObj>();
		comp = new VBox();
		sondageZone = new VBox();
		cb = new ChoiceBox<String>();
		cb.getItems().addAll("fait","non fait");
	}

	@Override
	protected void ecouteurDefaultAction() {
		cb.getSelectionModel().selectedIndexProperty().addListener(i->{

			if(cb.getSelectionModel().getSelectedIndex()==0) {
				this.fait =true;
			}else
				this.fait = false;
			try {
				int i1 = 0;
				if(this.fait) {
					sondageZone.getChildren().clear();
					currentSondageFait = connect.getSondageFait(u);
					for(SondageObj s : 	currentSondageFait) {
						indiceSondage.put(i1,s.getId());
						sondageZone.getChildren().add(new ResultatSondage(u, s));
						i1++;
					}
				}else {
					sondageZone.getChildren().clear();
					currentSondageNonFait = connect.getSondageNew(u);
					for(SondageObj s : 	currentSondageNonFait) {
						indiceSondage.put(i1,s.getId());
						if(!s.isMultiple()) {
							sondageZone.getChildren().add(new AffichagePoint(u, s));
						}else{
							sondageZone.getChildren().add(new AffichageChoixMulti(u, s));
						}
						i1++;
					}
				}

			}catch(Exception e) {
				e.printStackTrace();
			}

		});
	}

	@Override
	protected void layoutDefaultParametre() {
		comp.getChildren().addAll(cb,sondageZone);
		comp.setSpacing(50);
		comp.setAlignment(Pos.CENTER);
		this.getChildren().add(this.comp);
	}


	private void ajouterVote(String resultat, int sondage) {
		int i = 0;
		System.out.println(resultat);
		for (Node s : sondageZone.getChildren()) {
			if (s instanceof ResultatSondage) {
				if (((ResultatSondage) s).idSondage()==sondage) {
					SondageObj so = ((ResultatSondage) s).getSondageObj();
					ResultatSondage r = new ResultatSondage(u, new SondageObj(so.getId(),so.getOwner(),so.getQuestion(),resultat,so.getMultiple(),so.getDate(),so.getTotal()+1));
					sondageZone.getChildren().remove(i);
					sondageZone.getChildren().add(i, r);
					break;
				}
			}
		}
	}

	private void ajouterSondage(SondageObj sondage) throws RemoteException {
		if (!fait) {
			if (sondage.getMultiple()) sondageZone.getChildren().add(new AffichageChoixMulti(u, sondage));
			else sondageZone.getChildren().add(new AffichagePoint(u, sondage));
		}		
		currentSondageNonFait.add(sondage);
		Listener l = new Listener();
		listener.put(sondage.getId(),l);
		connect.addTchatListener(l,sondage.getId());
	}
	
	
	private class Listener extends UnicastRemoteObject implements SondageListener {

		private static final long serialVersionUID = 1L;

		public Listener() throws RemoteException {
			super();
		}

		@Override
		public void nouveauVote(String resultat, Integer sondage) {
			Platform.runLater(
					() -> {
						ajouterVote(resultat,sondage);
					}
					);
		}

		@Override
		public void nouveauSondage(SondageObj sondage) throws RemoteException {
			Platform.runLater(
					() -> {
						try {
							ajouterSondage(sondage);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					);
			
		}

	}

}
