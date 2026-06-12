package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AEtoile.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole.Symboles;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Vague.LecteurVague;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Vague.ListeApparition;
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

	private IntegerProperty numeroVague;
	private IntegerProperty totalVague;
	private IntegerProperty tempsPauseRestantSec;
	private LecteurVague lecteurVague;
	private ListeApparition vagueActuelle;
	private int compteurSpawn;
	private boolean pauseEntreVagues;
	private int compteurPause;
	private Tour tourAPlacer;

	private boolean murActif = false;
	private int dureeRestanteMur = 0;
	private int cooldownMur = 0;
	private MurGlace murG1  = null;
	private MurGlace murG2  = null;

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

	public IntegerProperty argentProperty() { return this.argent; }
	public int getArgent() { return this.argent.getValue(); }
	public void setArgent(int montant) {
		if (montant > 100) this.argent.set(100);
		else this.argent.set(Math.max(montant, 0));
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

	public void ajouterTour(Tour tour) {
		if (tour != null) {
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

	private void preparerVague(int numero) {
		if (this.lecteurVague.getVagues() != null && numero > 0 && numero <= this.lecteurVague.getNbVague()) {
			this.vagueActuelle = this.lecteurVague.getVagues()[numero - 1].getListeApparition();
		} else {
			this.vagueActuelle = null;
		}
	}

	private void tenterInvoquerMur(TourGlace tourGlace) {
		int tourX = tourGlace.getPosX() / 32;
		int tourY = tourGlace.getPosY() / 32;
		int meilleurX = -1;
		int meilleurY = -1;
		int meilleurJumeauX = -1;
		int meilleurJumeauY = -1;
		double minDist = Double.MAX_VALUE;

		for (int dy = -10; dy <= 10; dy++) {
			for (int dx = -10; dx <= 10; dx++) {
				int caseCibleX = tourX + dx;
				int caseCibleY = tourY + dy;

				if (terrain.estCheminNaturel(caseCibleX, caseCibleY)) {

					double dist = Math.sqrt(dx * dx + (dy + 2) * (dy + 2));

					if (dist < minDist) {
						int jumeauX = caseCibleX;
						int jumeauY = caseCibleY;

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

						boolean aStarOk = (chemin1 != null && !chemin1.isEmpty() &&
								chemin2 != null && !chemin2.isEmpty() &&
								chemin3 != null && !chemin3.isEmpty());

						terrain.setCaseBloquee(caseCibleX, caseCibleY, false);
						terrain.setCaseBloquee(jumeauX, jumeauY, false);

						if (aStarOk) {
							minDist = dist;
							meilleurX = caseCibleX;
							meilleurY = caseCibleY;
							meilleurJumeauX = jumeauX;
							meilleurJumeauY = jumeauY;
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
			this.murActif = true;
			this.dureeRestanteMur = 2;
			this.cooldownMur = 6;

			for (Monstre m : lesMonstres) {
				m.recalculerItineraire(this.terrain, this.base);
			}
			System.out.println("DEUX murs posés côte à côte ");
		} else {
			System.out.println("Mur annulé : Risque de blocage total ");
		}
	}

	public void unTour() {
		if (this.lesProjectiles != null && !this.lesProjectiles.isEmpty()) {
			for (int i = this.lesProjectiles.size() - 1; i >= 0; i--) {
				if (this.lesProjectiles.get(i).verifPosition()) this.lesProjectiles.remove(i);
				else this.lesProjectiles.get(i).projectilesAJour();
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
					Monstre monstre = null;
					switch (vagueActuelle.prochainMonstre()) {
						case 0: monstre = new Squelette(this.terrain); break;
						case 1: monstre = new Sorcier(this.terrain); break;
						case 2: monstre = new Nargacuga(this.terrain); break;
						case 3: monstre = new Dino(this.terrain); break;
						case 4: monstre = new Armure(this.terrain); break;
					}
					if (monstre != null) this.lesMonstres.add(monstre);
					compteurSpawn = vagueActuelle.prochainDelai();
					vagueActuelle.avancer();
				}
			} else if (this.lesMonstres.isEmpty()) {
				if (this.numeroVague.get() >= this.lecteurVague.getNbVague()) return;

				if (murActif) {
					dureeRestanteMur--;
					if (dureeRestanteMur <= 0) {
						murActif = false;

						if (murG1 != null && murG2 != null) {
							terrain.setCaseBloquee(murG1.getPosX() / 32, murG1.getPosY() / 32, false);
							terrain.setCaseBloquee(murG2.getPosX() / 32, murG2.getPosY() / 32, false);

							lesTours.removeAll(murG1, murG2);
							murG1 = null;
							murG2 = null;
						}

						System.out.println("La barricade de Glace a fondu ");
						for (Monstre m : lesMonstres) m.recalculerItineraire(this.terrain, this.base);
					}
				} else if (cooldownMur > 0) {
					cooldownMur--;
					if (cooldownMur == 0) System.out.println("Glace rechargée ");
				}

				int bonusArgent = 50 + (this.numeroVague.get() * 10);
				this.setArgent(this.getArgent() + bonusArgent);
				this.numeroVague.set(this.numeroVague.get() + 1);
				this.pauseEntreVagues = true;
				this.compteurPause = 900;
			}
		}

		if (this.lesTours != null && !this.lesTours.isEmpty()) {
			if (!murActif && cooldownMur == 0) {
				for (Tour t : this.lesTours) {
					if (t instanceof TourGlace) {
						tenterInvoquerMur((TourGlace) t);
						if (murActif) break;
					}
				}
			}
			for (Tour t : this.lesTours) t.agir(this.lesMonstres, this.base, this.lesProjectiles);
		}

		if (this.lesMonstres != null && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				} else if (m.aAtteintSaCible()) {
					this.base.retirerPv(m.getAtq());
					this.lesMonstres.remove(i);
				} else m.agir(this.lesMonstres, this.terrain, this.getBase());
			}
		}
	}

	public boolean tourPosable(double xPixel, double yPixel) {
		int gridX = (int) (xPixel / 32);
		int gridY = (int) (yPixel / 32);
		if(gridY >= 25 || gridY < 0 || gridX >= 60 || gridX < 0) return false;
		return !this.terrain.estCheminNaturel(gridX, gridY);
	}

	public void validerSymboles() {
		if (this.getSymboles().verifierCombinaison()) this.setModePlacementTour(true);
	}
}