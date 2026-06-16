package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourGlace;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.MurGlace;
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

	// 🟢 Système de mur de glace importé du CRASH
	private boolean murActif = false;
	private int dureeRestanteMur = 0;
	private int cooldownMur = 0;
	private MurGlace murG1 = null;
	private MurGlace murG2 = null;

	public Environnement(Terrain terrain) {
		this.terrain = terrain;
		this.nbTours = new SimpleIntegerProperty();
		this.lesTours = FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();
		this.sortTours = FXCollections.observableArrayList();

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
		else this.argent.set(Math.max(montant, 0)); // Eviter l'argent négatif
	}

	public ObservableList<Monstre> getLesMonstres() { return lesMonstres; }
	public ObservableList<Tour> getLesTours() { return this.lesTours; }
	public Symboles getSymboles() { return symboles; }
	public ObservableList<String> getSymbolesProperty() { return symboles.getCombinaison(); }
	public Base getBase() { return base; }
	public boolean isModePlacementTour() { return modePlacementTour.get(); }
	public BooleanProperty modePlacementTourProperty() { return modePlacementTour; }
	public void setModePlacementTour(boolean modePlacementTour) { this.modePlacementTour.set(modePlacementTour); }
	public void ajouterProjectiles(SortTour sortTour) { this.sortTours.add(sortTour); }
	public ObservableList<SortTour> getLesProjectiles() { return sortTours; }

	// --- Méthodes de jeu ---
	public void ajouterTour(Tour tour) {
		System.out.println("tour prete");
		this.lesTours.add(tour);
		this.setArgent(this.getArgent() - tour.getCout());
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

	// 🟢 La méthode pour calculer et placer le Mur de Glace dynamiquement
	private void tenterInvoquerMur(TourGlace tourGlace) {
		int tourX = tourGlace.getPosX() / 32;
		int tourY = tourGlace.getPosY() / 32;
		int meilleurX = -1, meilleurY = -1, meilleurJumeauX = -1, meilleurJumeauY = -1;
		double minDist = Double.MAX_VALUE;

		for (int dy = -10; dy <= 10; dy++) {
			for (int dx = -10; dx <= 10; dx++) {
				int caseCibleX = tourX + dx;
				int caseCibleY = tourY + dy;
				if (terrain.estCheminNaturel(caseCibleX, caseCibleY)) {
					double dist = Math.sqrt(dx * dx + (dy + 2) * (dy + 2));
					if (dist < minDist) {
						int jumeauX = caseCibleX, jumeauY = caseCibleY;
						if (!terrain.estCheminNaturel(caseCibleX - 1, caseCibleY) || !terrain.estCheminNaturel(caseCibleX + 1, caseCibleY)) {
							if (terrain.estCheminNaturel(caseCibleX - 1, caseCibleY)) jumeauX = caseCibleX - 1;
							else if (terrain.estCheminNaturel(caseCibleX + 1, caseCibleY)) jumeauX = caseCibleX + 1;
						} else if (!terrain.estCheminNaturel(caseCibleX, caseCibleY - 1) || !terrain.estCheminNaturel(caseCibleX, caseCibleY + 1)) {
							if (terrain.estCheminNaturel(caseCibleX, caseCibleY - 1)) jumeauY = caseCibleY - 1;
							else if (terrain.estCheminNaturel(caseCibleX, caseCibleY + 1)) jumeauY = caseCibleY + 1;
						}

						terrain.setCaseBloquee(caseCibleX, caseCibleY, true);
						terrain.setCaseBloquee(jumeauX, jumeauY, true);

						var chemin1 = AlgorithmeAEtoile.trouverChemin(terrain, 0, 8, 58, 12);
						var chemin2 = AlgorithmeAEtoile.trouverChemin(terrain, 24, 0, 58, 12);
						var chemin3 = AlgorithmeAEtoile.trouverChemin(terrain, 0, 22, 58, 12);
						boolean aStarOk = (chemin1 != null && !chemin1.isEmpty() && chemin2 != null && !chemin2.isEmpty() && chemin3 != null && !chemin3.isEmpty());

						terrain.setCaseBloquee(caseCibleX, caseCibleY, false);
						terrain.setCaseBloquee(jumeauX, jumeauY, false);

						if (aStarOk) {
							minDist = dist; meilleurX = caseCibleX; meilleurY = caseCibleY; meilleurJumeauX = jumeauX; meilleurJumeauY = jumeauY;
						}
					}
				}
			}
		}
		if (meilleurX != -1) {
			terrain.setCaseBloquee(meilleurX, meilleurY, true);
			terrain.setCaseBloquee(meilleurJumeauX, meilleurJumeauY, true);
			this.murG1 = new MurGlace(meilleurX * 32, meilleurY * 32);
			this.murG2 = new MurGlace(meilleurJumeauX * 32, meilleurJumeauY * 32);
			this.lesTours.addAll(this.murG1, this.murG2);
			this.murActif = true; this.dureeRestanteMur = 2; this.cooldownMur = 6;

			// 🟢 On dit aux monstres de recalculer le chemin car un mur est apparu !
			for (Monstre m : lesMonstres) m.recalculerItineraire(this.terrain, this.base);
		}
	}

	public void unTour() {

		// 1. Mise à jour des sorts (sécurisé en bouclant à l'envers)
		if (!this.sortTours.isEmpty()) {
			for (int i = this.sortTours.size() - 1; i >= 0; i--) {
				if (this.sortTours.get(i).isAttaqueFini()) {
					this.sortTours.remove(i);
				} else {
					this.sortTours.get(i).sortAJour();
				}
			}
		}

		// 2. Gestion des Tours et Mur de Glace
		if (this.lesTours != null && !this.lesTours.isEmpty()) {
			// Tentative d'apparition d'un mur s'il n'y en a pas et que le CD est à 0
			if (!murActif && cooldownMur == 0) {
				for (Tour t : this.lesTours) {
					if (t instanceof TourGlace) {
						tenterInvoquerMur((TourGlace) t);
						if (murActif) break;
					}
				}
			}

			for (int i = 0; i < this.lesTours.size(); i++) {
				this.lesTours.get(i).agir(this.lesMonstres, this.base, this.sortTours);
			}
		}

		// 3. Gestion des Vagues et fonte du Mur
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

				if (murActif) {
					dureeRestanteMur--;
					if (dureeRestanteMur <= 0) {
						murActif = false;
						if (murG1 != null && murG2 != null) {
							terrain.setCaseBloquee(murG1.getPosX() / 32, murG1.getPosY() / 32, false);
							terrain.setCaseBloquee(murG2.getPosX() / 32, murG2.getPosY() / 32, false);
							lesTours.removeAll(murG1, murG2);
							murG1 = null; murG2 = null;
						}
						// Le mur a fondu, on redit aux monstres de recalculer le chemin direct
						for (Monstre m : lesMonstres) m.recalculerItineraire(this.terrain, this.base);
					}
				} else if (cooldownMur > 0) {
					cooldownMur--;
				}

				int bonusArgent = 50 + (this.numeroVague.get() * 10);
				this.setArgent(this.getArgent() + bonusArgent);
				this.numeroVague.set(this.numeroVague.get() + 1);
				this.pauseEntreVagues = true;
				this.compteurPause = 900;
			}
		}

		// Mouvement des Monstres
		if (this.lesMonstres != null && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				} else if (m.aAtteintSaCible()) {
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
		if (gridY >= 25) return false;
		if (this.terrain.estPraticable(gridX, gridY)) return false;

		for (int i = 0; i < 1; i++) {
			if (this.terrain.estPraticable(gridX + i, gridY) || this.terrain.estPraticable(gridX - i, gridY) || this.terrain.estPraticable(gridX, gridY + i) || this.terrain.estPraticable(gridX, gridY - i) || this.terrain.estPraticable(gridX + i, gridY - i) || this.terrain.estPraticable(gridX - i, gridY + i) || this.terrain.estPraticable(gridX + i, gridY + i) || this.terrain.estPraticable(gridX - i, gridY - i))
				return false;
		}
		return true;
	}

	public void validerSymboles() {
		if (this.getSymboles().verifierCombinaison()) {
			this.setModePlacementTour(true);
		}
	}
}