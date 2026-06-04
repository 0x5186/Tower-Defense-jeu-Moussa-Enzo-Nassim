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
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;

public class Environnement {
	private IntegerProperty nbTours;
	private Base base;
	private ObservableList<Tour> lesTours;
	private ObservableList<Monstre> lesMonstres;
	private Terrain terrain;
	private IntegerProperty argent;
	private Symboles symboles; // liste des symboles
	private final BooleanProperty modePlacementTour;

	// --- VARIABLES VAGUES AUTOMATIQUES (PROVENANT DE vagues.txt) ---
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
		this.lesTours = FXCollections.observableArrayList();
		this.lesMonstres = FXCollections.observableArrayList();
		this.symboles = new Symboles();
		this.argent = new SimpleIntegerProperty(100);
		this.base = new Base();
		this.modePlacementTour = new SimpleBooleanProperty(false);

		Monstre.compteurID = 0;
		Entite.compteurID = 0;

		// INITIALISATION DU SYSTÈME DE VAGUES AUTOMATIQUES
		this.lecteurVague = new LecteurVague();
		this.numeroVague = new SimpleIntegerProperty(1);
		this.totalVague = new SimpleIntegerProperty(this.lecteurVague.getNbVague());
		this.tempsPauseRestantSec = new SimpleIntegerProperty(10);
		this.pauseEntreVagues = true;
		this.compteurPause = 600; // 10 secondes de préparation au début (60 ticks * 10)
		this.compteurSpawn = 0;
	}

//  les Get / set:

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

	// GETTERS POUR LES PROPRIÉTÉS DU SYSTÈME DE VAGUES
	public IntegerProperty numeroVagueProperty() { return this.numeroVague; }

	public IntegerProperty totalVagueProperty() { return this.totalVague; }

	public IntegerProperty tempsPauseRestantSecProperty() { return this.tempsPauseRestantSec; }

	public boolean isPauseEntreVagues() { return this.pauseEntreVagues; }

// autres Méthodes:

	public void ajouterTour(Tour tour){
		System.out.println("tour prete");
		this.lesTours.add(tour);
		this.setArgent(this.getArgent() - tour.getCout());
	}

	public void ajouterMonstre(){
		Monstre monstre = new Squelette(this.terrain);
		monstre.setSpawnEnnemi(this.terrain);
		lesMonstres.add(monstre);
	}

	public final IntegerProperty nbToursProperty(){ return this.nbTours; }

	private void preparerVague(int numero) {
		System.out.println("Préparation de la vague : " + numero);
		if (this.lecteurVague.getVagues() != null && numero <= this.lecteurVague.getNbVague()) {
			this.vagueActuelle = this.lecteurVague.getVagues()[numero - 1].getListeApparition();
		} else {
			this.vagueActuelle = null;
		}
	}

	private void faireApparaitreMonstre(int codeMonstre) {
		Monstre monstre = null;
		switch (codeMonstre) {
			case 0:
				monstre = new Squelette(this.terrain);
				break;
			case 1:
				monstre = new Sorcier(this.terrain);
				break;
		}

		if (monstre != null) {
			monstre.setSpawnEnnemi(this.terrain);
			this.lesMonstres.add(monstre);
		}
	}

	public void unTour() {
		// 1. GESTION DU SYSTÈME DE VAGUES AUTOMATIQUES
		if (pauseEntreVagues) {
			compteurPause--;
			int secondesRestantes = (int) Math.ceil(compteurPause / 60.0);
			this.tempsPauseRestantSec.set(secondesRestantes);

			if (compteurPause <= 0) {
				pauseEntreVagues = false;
				this.tempsPauseRestantSec.set(0);
				preparerVague(this.numeroVague.get());
				compteurSpawn = 0;
			}
		} else {
			// Gestion des apparitions cadencées
			if (vagueActuelle != null && vagueActuelle.resteProchain()) {
				compteurSpawn--;
				if (compteurSpawn <= 0) {
					int prochainMonstreCode = vagueActuelle.prochainMonstre();
					int delaiAvantProchain = vagueActuelle.prochainDelai();

					faireApparaitreMonstre(prochainMonstreCode);
					vagueActuelle.avancer();

					compteurSpawn = delaiAvantProchain;
				}
			}
			// Fin de vague / plus de monstres en attente et plus aucun monstre vivant sur le terrain
			else if (this.lesMonstres.isEmpty()) {
				int bonusArgent = 50 + (this.numeroVague.get() * 10);
				System.out.println("Vague " + this.numeroVague.get() + " terminée | Bonus : " + bonusArgent + " joyaux.");

				this.setArgent(this.getArgent() + bonusArgent);
				this.numeroVague.set(this.numeroVague.get() + 1);

				this.pauseEntreVagues = true;
				this.compteurPause = 900;
				this.tempsPauseRestantSec.set(15);
			}
		}

		// 2. ACTIONS DES TOURS
		if (!(this.lesTours == null) && !this.lesTours.isEmpty()) {
			for (int i = 0; i < this.lesTours.size(); i++) {
				this.lesTours.get(i).agir(this.lesMonstres, this.base);
			}
		}

		// 3. ETAT ET ACTIONS DES MONSTRES
		if (!(this.lesMonstres == null) && !this.lesMonstres.isEmpty()) {
			for (int i = this.lesMonstres.size() - 1; i >= 0; i--) {
				Monstre m = this.lesMonstres.get(i);
				if (!m.estVivant()) {
					System.out.println("Monstre tué");
					this.setArgent(this.getArgent() + m.getRecompense());
					this.lesMonstres.remove(i);
				}
				else if (m.getPosX() > this.base.getPosX() + 110) {
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