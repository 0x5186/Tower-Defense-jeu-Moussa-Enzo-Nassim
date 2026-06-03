package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

public class Environnement {
	private IntegerProperty nbTours;




	private Base base;
	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;
	private Terrain terrain;
	private IntegerProperty argent;


	private Symboles symboles; //liste des symboles
	 //pour savoir si on est entrain de placer une tour ou pas
	private final BooleanProperty modePlacementTour;

	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();

		this.argent = new SimpleIntegerProperty(100);


		this.base=new Base();

		Monstre.compteurID = 0;
		Entite.compteurID = 0;


		this.modePlacementTour= new SimpleBooleanProperty(false);
	}

// 	les Get / set:

	public IntegerProperty argentProperty() { return this.argent; }

	public int getArgent() { return this.argent.getValue(); }

	public void setArgent(int montant) { this.argent.set(montant); }

	public ObservableList<Monstre> getLesMonstres() {
		return lesMonstres;
	}

	public ObservableList<Tour> getLesTours() {
		return this.lesTours;
	}

	public Symboles getSymboles() {
		return symboles;
	}
	public ObservableList<String> getSymbolesProperty() {
		return symboles.getCombinaison();
	}

	public Base getBase() {
		return base;
	}

	public boolean isModePlacementTour() {
		return modePlacementTour.get();
	}

	public BooleanProperty modePlacementTourProperty() {
		return modePlacementTour;
	}


	public void setModePlacementTour(boolean modePlacementTour) {
		this.modePlacementTour.set(modePlacementTour);
	}

// autres Méthodes:

	public void ajouterTour(Tour tour){
		System.out.println("tour prete");
		this.lesTours.add(tour);
	}

	public void ajouterMonstre(){

		Monstre monstre=new Squelette(this.terrain);
		lesMonstres.add(monstre);


    }


	public final IntegerProperty nbToursProperty(){ return this.nbTours; }
	public int getNbTours() { return this.nbTours.getValue(); }

	public void unTour() {

		//faut les supp quand ils sont morts / sinon ils continuent d'avancer
		if (!(this.lesTours == null) && !this.lesTours.isEmpty()) {

			for (int i = 0; i < this.lesTours.size(); i++) {
				this.lesTours.get(i).agir(this.lesMonstres, this.base);
			}
		}

		if (!(this.lesMonstres == null) && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					System.out.println("Monstre tué");
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				}
				else if (m.getPosX()>this.base.getPosX()+100) {
					this.lesMonstres.remove(i);

				}
				else {
					m.agir(this.lesMonstres, this.terrain, this.getBase());
				}
			}
		}
	}



	public boolean tourPosable(double xPixel, double yPixel) {
		System.out.println("presque");
		int TAILLE_TUILE = 16;
		int gridX = (int) (xPixel / TAILLE_TUILE);
		int gridY = (int) (yPixel / TAILLE_TUILE);

		if (!this.terrain.estPraticable(gridX, gridY)) {
			return true;
		}
		return false;
	}

	public void validerSymboles() {
		System.out.println(this.getSymboles());
		System.out.println(this.getSymboles().getCombinaison());
		if(this.getSymboles().verifierCombinaison()){

			System.out.println("dans le if");
			this.setModePlacementTour(true);

		}
	}
}