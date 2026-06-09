package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.*;

public class Environnement {
	private IntegerProperty nbTours;
	private Base base;
	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;
	protected ObservableList<Projectile> lesProjectiles;
	private Terrain terrain;
	private IntegerProperty argent;
	private Symboles symboles;
	private final BooleanProperty modePlacementTour;

	// Système de vagues
	private IntegerProperty numeroVague;
	private IntegerProperty totalVague;
	private IntegerProperty tempsPauseRestantSec;
	private LecteurVague lecteurVague;
	private ListeApparition vagueActuelle;
	private int compteurSpawn;
	private boolean pauseEntreVagues;
	private int compteurPause;
	private Tour tourAPlacer;

	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesTours = FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();
		this.lesProjectiles = FXCollections.observableArrayList();

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
	}

	// --- Getters / Setters ---

	public IntegerProperty argentProperty() { return this.argent; }
	public int getArgent() { return this.argent.getValue(); }

	public void setArgent(int montant) {
		if (montant > 100) this.argent.set(100);
		else this.argent.set(Math.max(montant, 0)); // Sécurité pour ne pas tomber en dessous de 0
	}

	public ObservableList<Monstre> getLesMonstres() { return lesMonstres; }
	public ObservableList<Tour> getLesTours() { return this.lesTours; }
	public Symboles getSymboles() { return symboles; }
	public ObservableList<String> getSymbolesProperty() { return symboles.getCombinaison(); }
	public Base getBase() { return base; }

	public boolean isModePlacementTour() { return modePlacementTour.get(); }
	public BooleanProperty modePlacementTourProperty() { return modePlacementTour; }
	public void setModePlacementTour(boolean modePlacementTour) { this.modePlacementTour.set(modePlacementTour); }

	public final IntegerProperty nbToursProperty() { return this.nbTours; }
	public void setTourAPlacer(Tour tour) { this.tourAPlacer = tour; }
	public Tour getTourAPlacer() { return this.tourAPlacer; }

	public void ajouterProjectiles(Projectile projectile) { this.lesProjectiles.add(projectile); }
	public void setLesProjectiles(ObservableList<Projectile> lesProjectiles) { this.lesProjectiles = lesProjectiles; }
	public ObservableList<Projectile> getLesProjectiles() { return lesProjectiles; }


	// --- Méthodes du jeu ---

	public void ajouterTour(Tour tour) {
		if (tour != null) {
			System.out.println("tour prete");
			this.lesTours.add(tour);
			this.setArgent(this.getArgent() - tour.getCout());
		}
	}

	public void placerLaTourAttente(double xPixel, double yPixel) {
		int TAILLE_TUILE = 32;
		int gridX = (int) (xPixel / TAILLE_TUILE);
		int gridY = (int) (yPixel / TAILLE_TUILE);

		if (this.tourAPlacer != null) {
			this.tourAPlacer.setPosX(gridX * TAILLE_TUILE);
			this.tourAPlacer.setPosY(gridY * TAILLE_TUILE);
			this.lesTours.add(this.tourAPlacer);
			this.setArgent(this.getArgent() - this.tourAPlacer.getCout());
			this.tourAPlacer = null;
		}
	}

	public void ajouterMonstre() {
		Monstre monstre = new Nargacuga(this.terrain);
		lesMonstres.add(monstre);
	}

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

		// 1. Gestion des projectiles
		if (this.lesProjectiles != null && !this.lesProjectiles.isEmpty()) {
			// Parcours à l'envers pour pouvoir supprimer sans problème d'index
			for (int i = this.lesProjectiles.size() - 1; i >= 0; i--) {
				if (this.lesProjectiles.get(i).verifPosition()) {
					this.lesProjectiles.remove(i);
					System.out.println("Projectile retiré");
				} else {
					this.lesProjectiles.get(i).projectilesAJour();
				}
			}
		}

		// 2. Gestion des vagues
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

		// 3. Action des tours
		if (this.lesTours != null && !this.lesTours.isEmpty()) {
			for (Tour t : this.lesTours) {
				// Si votre méthode agir() prend 3 arguments, commentez la ligne du dessous et décommentez l'autre.
				// t.agir(this.lesMonstres, this.base, this.lesProjectiles);
				t.agir(this.lesMonstres, this.base, this.lesProjectiles);
			}
		}

		// 4. Action des monstres
		if (this.lesMonstres != null && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				} else if (m.aAtteintSaCible()) {
					this.base.retirerPv(m.getAtq());
					this.lesMonstres.remove(i);
				} else {
					m.agir(this.lesMonstres, this.terrain, this.getBase());
				}
			}
		}
	}

	public boolean tourPosable(double xPixel, double yPixel) {
		int TAILLE_TUILE = 32;
		int gridX = (int) (xPixel / TAILLE_TUILE);
		int gridY = (int) (yPixel / TAILLE_TUILE);
		if(gridY >= 25) return false;
		return !this.terrain.estPraticable(gridX, gridY);
	}

	public void validerSymboles() {
		if (this.getSymboles().verifierCombinaison()) {
			this.setModePlacementTour(true);
		}
	}
}