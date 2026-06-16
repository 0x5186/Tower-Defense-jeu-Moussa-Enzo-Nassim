package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;

public class Environnement {
	private IntegerProperty nbTours;




	private Base base;
	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;
	protected ObservableList<SortTour> sortTours;
	private Terrain terrain;
	private IntegerProperty argent;

	private Symboles symboles; //liste des symboles
	private ObservableList<Decor> lesDecors;
	 //pour savoir si on est entrain de placer une tour ou pas
	private final BooleanProperty modePlacementTour;

	// Système de vagues (version 1)
	private IntegerProperty numeroVague;
	private IntegerProperty totalVague;
	private IntegerProperty tempsPauseRestantSec;
	private LecteurVague lecteurVague;
	private ListeApparition vagueActuelle;
	private int compteurSpawn;
	private boolean pauseEntreVagues;
	private int compteurPause;


	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesTours =FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();
		this.sortTours= FXCollections.observableArrayList();

		this.argent = new SimpleIntegerProperty(100);
		this.base = new Base();
		this.modePlacementTour = new SimpleBooleanProperty(false);

		Monstre.compteurID = 0;
		Entite.compteurID = 0;

		this.lecteurVague = new LecteurVague();
		this.numeroVague = new SimpleIntegerProperty(1);
		this.totalVague = new SimpleIntegerProperty(this.lecteurVague.getNbVague());
		this.tempsPauseRestantSec = new SimpleIntegerProperty(10);
		this.pauseEntreVagues = true;
		this.compteurPause = 300;
		this.compteurSpawn = 0;

		//partie decor
		this.lesDecors = FXCollections.observableArrayList();
		Fleur fleur1 = new Fleur(300, 150);
		Fleur fleur2 = new Fleur(600, 370);
		Fleur fleur3 = new Fleur(800, 100);
		Fleur fleur4 = new Fleur(900, 600);
		Fleur fleur5 = new Fleur(1500, 350);
		Decor pillier = new Decor(990, 5, 0.3, "pillier");
		Decor rocher1 = new Decor(1470, 40, 0.15, "rocher");
		Decor rocher2 = new Decor(200, -80, 0.15, "rocher");
		Decor rocher3 = new Decor(-30, 180, 0.15, "rocher");
		Decor arbre1 = new Decor(0, 270, 0.7, "arbre");
		Decor arbre2 = new Decor(1500, -60, 0.6, "arbre");
//		Marre marre = new Marre(800, 250);
		this.lesDecors = FXCollections.observableArrayList();
		this.lesDecors.addAll(fleur1, fleur2, fleur3, fleur4, fleur5, pillier, rocher1, rocher2, arbre1, rocher3, arbre2);
	}

// 	les Get / set:

	public IntegerProperty argentProperty() { return this.argent; }

	public int getArgent() { return this.argent.getValue(); }

	public void setArgent(int montant) {
		System.out.println("Argent avant = " + this.argent.get());
		System.out.println("Nouvelle valeur = " + montant);

		if(montant > 100)
			this.argent.set(100);
		else
			this.argent.set(montant);

		System.out.println("Argent après = " + this.argent.get());
	}

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

	public void ajouterProjectiles(SortTour sortTour){
		this.sortTours.add(sortTour);
	}


	public ObservableList<SortTour> getLesProjectiles() {
		return sortTours;
	}

	// autres Méthodes:

	public void ajouterTour(Tour tour){
		System.out.println("tour prete");
		this.lesTours.add(tour);
		this.setArgent(this.getArgent()-tour.getCout());

	}

	public void ajouterMonstre() {
//		Monstre monstre = new Nargacuga(this.terrain);
		Monstre monstre = new Sorcier(this.terrain);
		lesMonstres.add(monstre);
	}

	// Version 1 : système de vagues
	private void preparerVague(int numero) {
		if (this.lecteurVague.getVagues() != null && numero > 0 && numero <= this.lecteurVague.getNbVague()) {
			this.vagueActuelle = this.lecteurVague.getVagues()[numero - 1].getListeApparition();
		} else {
			this.vagueActuelle = null;
		}
	}

	private void faireApparaitreMonstre(int codeMonstre) {
		Monstre monstre = null;
		switch (codeMonstre) {
			case 0: monstre = new Squelette(this.terrain); break;
			case 1: monstre = new Sorcier(this.terrain); break;
			case 2: monstre = new Nargacuga(this.terrain); break;
		}
		if (monstre != null) {
			this.lesMonstres.add(monstre);
		}
	}

	public void unTour() {


		if (!this.sortTours.isEmpty()){
			for(int i = 0; i < this.sortTours.size(); i++){
				if(this.sortTours.get(i).isAttaqueFini()){
					this.sortTours.remove(this.sortTours.get(i));
					System.out.println("retirer");
				}
				else{
					this.sortTours.get(i).sortAJour();
				}
			}
		}//

		for(int i = 0; i < this.lesDecors.size(); i++){
			Decor decor = this.lesDecors.get(i);
			if (decor instanceof Fleur){
				((Fleur) decor).mettreAjour(this.lesMonstres);
			}

//			if (decor instanceof  Marre){
//				((Marre) decor).mettreAjour(this.lesMonstres);
//			}
		}

		if (!(this.lesTours == null) && !this.lesTours.isEmpty()) {
			for (int i = 0; i < this.lesTours.size(); i++) {
				this.lesTours.get(i).agir(this.lesMonstres, this.base, this.sortTours);
			}
		}


		if (pauseEntreVagues) {
			compteurPause--;
			if (compteurPause <= 0) {
				pauseEntreVagues = false;
				preparerVague(this.numeroVague.get());
				compteurSpawn = 0;
			}
		} else {
			if (vagueActuelle != null && vagueActuelle.resteProchain()) {
				compteurSpawn--;
				if (compteurSpawn <= 0) {
					faireApparaitreMonstre(vagueActuelle.prochainMonstre());
					compteurSpawn = vagueActuelle.prochainDelai();
					vagueActuelle.avancer();
				}
			} else if (this.lesMonstres.isEmpty()) {
				if (this.numeroVague.get() >= this.lecteurVague.getNbVague()) {
					return;
				}
				int bonusArgent = 50 + (this.numeroVague.get() * 10);
				this.setArgent(this.getArgent() + bonusArgent);
				this.numeroVague.set(this.numeroVague.get() + 1);
				this.pauseEntreVagues = true;
				this.compteurPause = 900;
			}
		}




		if (!(this.lesMonstres == null) && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					System.out.println("Monstre tué");
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				} else if (m.aAtteintSaCible()) {
					System.out.println(this.argent);
					this.lesMonstres.remove(i);
				} else {
					m.agir(this.lesMonstres, this.terrain, this.base);
				}

			}
		}

	}



	public boolean tourPosable(double xPixel, double yPixel) {
		int TAILLE_TUILE = 32;
		int gridX = (int) (xPixel / TAILLE_TUILE);
		int gridY = (int) (yPixel / TAILLE_TUILE);
		if(gridY >= 25) return false;
		if (this.terrain.estPraticable(gridX, gridY))
			return false;

		for (int i = 0; i < this.lesDecors.size(); i++){
			if (this.lesDecors.get(i).estDansLeRayonDecor(gridX, gridY)) {
				return false;
			}
		}

		for(int i=0; i<1;i++){
			if(this.terrain.estPraticable(gridX+i, gridY) || this.terrain.estPraticable(gridX-i, gridY) || this.terrain.estPraticable(gridX, gridY+i) || this.terrain.estPraticable(gridX, gridY-i) || this.terrain.estPraticable(gridX+i, gridY-i) ||this.terrain.estPraticable(gridX-i, gridY+i) || this.terrain.estPraticable(gridX+i, gridY+i)|| this.terrain.estPraticable(gridX-i, gridY-i))
				return false;

		}
		return true ;
	}

	public void validerSymboles() {
		System.out.println(this.getSymboles());
		System.out.println(this.getSymboles().getCombinaison());
		if(this.getSymboles().verifierCombinaison()){

			System.out.println("dans le if");
			this.setModePlacementTour(true);

		}
	}

	public ObservableList<Decor> getLesDecors(){
		return this.lesDecors;
	}

}