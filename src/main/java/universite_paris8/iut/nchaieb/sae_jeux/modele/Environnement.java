package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

public class Environnement {
	private IntegerProperty nbTours;




	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;
	private Terrain terrain;

	public Tour tourAPlacer;
	private Symboles symboles; //liste des symboles
	 //pour savoir si on est entrain de placer une tour ou pas


	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();
		tourAPlacer=new Tour(1,1,1,1);


		Monstre.compteurID = 0;
		Entite.compteurID = 0;
	}

// 	les Get :


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




// autres Méthodes:

	public void ajouterTour(Tour tour){
		System.out.println("tour prete");
		this.tourAPlacer=tour;
		this.tourAPlacer.setModePlacementTour(true);
//		this.lesTours.add(tour);
	}

	public void ajouterMonstre(){

		Monstre monstre=new Squelette();
		monstre.setSpawnEnnemi(terrain);
		lesMonstres.add(monstre);


    }


	public final IntegerProperty nbToursProperty(){ return this.nbTours; }
	public int getNbTours() { return this.nbTours.getValue(); }

	public void unTour() {

		//faut les supp quand ils sont morts / sinon ils continuent d'avancer
		if (!(this.lesMonstres == null) && !this.lesMonstres.isEmpty()) {
			for (int i =0; i< this.lesTours.size() ; i++) {
				this.lesTours.get(i).agir(this.lesMonstres);

			}


			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				if (!this.lesMonstres.get(i).estVivant()) {
					System.out.println("je suis remove");
					this.lesMonstres.remove(i);
				} else {
					this.lesMonstres.get(i).agir(this.lesMonstres, this.terrain);

				}
			}

		}
	}



	public void placerTour(double xPixel, double yPixel) {
		System.out.println("presque");
		int TAILLE_TUILE = 16;
		int gridX = (int) (xPixel / TAILLE_TUILE);
		int gridY = (int) (yPixel / TAILLE_TUILE);

		if (!this.terrain.estPraticable(gridX, gridY)) {

			int posXGridPixel = gridX * TAILLE_TUILE;
			int posYGridPixel = gridY * TAILLE_TUILE;
			this.tourAPlacer.setPosX(posXGridPixel);
			this.tourAPlacer.setPosY(posYGridPixel);
			this.lesTours.add(this.tourAPlacer);


			this.tourAPlacer.setModePlacementTour(false);
			System.out.println("Tour placée avec succès en X:" + gridX + " Y:" + gridY);
		} else {
			System.out.println("Impossible de placer une tour sur le chemin des monstres !");
		}
	}
}