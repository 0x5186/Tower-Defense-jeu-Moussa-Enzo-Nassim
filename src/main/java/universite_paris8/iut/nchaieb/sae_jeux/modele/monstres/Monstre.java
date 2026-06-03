package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Noeud;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

import java.util.ArrayList;

public abstract class Monstre extends Entite {

    protected int nombreDePV;
    protected int pvMax;
    private int atq;

    protected int vitesse;
    protected int portee;

    protected int recompense;

    private ArrayList<Noeud> chemin;
    private final int TAILLE_TUILE = 16;

    private int targetX;
    private int targetY;

    public Monstre(int pvMax, int atq, int posX, int posY, int vitesse, int recompense) {
        this.atq = atq;
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.vitesse = vitesse;
        this.recompense = recompense;

        this.actionActuelle.set("fixe");
        this.targetX = 119;
        this.targetY = 26;
        this.setPosX(posX);
        this.setPosY(posY);
    }

    public void infligerDegat(Monstre monstre) {
        if (monstre.nombreDePV != 0) {
            monstre.retirerPV(this.atq);
        }
    }

    public void ajouterPV(int soin){
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax){
            this.nombreDePV = this.pvMax;
        }
    }

    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV <= 0) {
            this.nombreDePV = 0;
        }
    }

    public int getPortee() {
        return portee;
    }

    public int getRecompense() {
        return recompense;
    }

    public void setSpawnEnnemi(Terrain terrain) {
        int portailAleatoire = (int) (Math.random() * 3);

        if (portailAleatoire == 0) {
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE);
        } else if (portailAleatoire == 1) {
            this.setPosX(10 * TAILLE_TUILE);
            this.setPosY(51 * TAILLE_TUILE);
        } else {
            this.setPosX(50 * TAILLE_TUILE);
            this.setPosY(0);
        }

        this.targetX = terrain.largeur() - 1;
        this.targetY = 26;
    }

    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base) {
        if (!this.getActionActuelle().get().equals("marche")) {
            this.setActionActuelle("marche");
        }
        this.avancer(terrain, base);
    }

    private void avancer(Terrain terrain, Base base) {
        if (terrain == null) return;

        if (this.chemin == null || this.chemin.isEmpty()) {
            int departGridX = this.getPosX() / TAILLE_TUILE;
            int departGridY = this.getPosY() / TAILLE_TUILE;

            this.chemin = AlgorithmeAEtoile.trouverChemin(terrain, departGridX, departGridY, this.targetX, this.targetY);

            if (this.chemin == null || this.chemin.isEmpty()) return;
        }

        Noeud prochaineEtape = this.chemin.get(0);

        int ciblePixelX = prochaineEtape.x * TAILLE_TUILE;
        int ciblePixelY = prochaineEtape.y * TAILLE_TUILE;

        int dx = ciblePixelX - this.getPosX();
        int dy = ciblePixelY - this.getPosY();

        int deplacementX = 0;
        int deplacementY = 0;

        if (dx > 0) deplacementX = Math.min(this.vitesse, dx);
        else if (dx < 0) deplacementX = Math.max(-this.vitesse, dx);

        if (dy > 0) deplacementY = Math.min(this.vitesse, dy);
        else if (dy < 0) deplacementY = Math.max(-this.vitesse, dy);

        this.setPosX(this.getPosX() + deplacementX);
        this.setPosY(this.getPosY() + deplacementY);

        if (this.getPosX() == ciblePixelX && this.getPosY() == ciblePixelY) {
            this.chemin.remove(0);
            if (this.chemin.isEmpty()) {
                if (base != null) {
                    base.retirerPV(this.atq);
                }
                this.setActionActuelle("atteint objectif");
            }
        }
    }

    public Monstre plusProche(ArrayList<Monstre> listeMonstre) {
        Monstre monstrePlusProche = null;
        for (int i = 0; i < listeMonstre.size(); i++) {
            if (this.estDansLeRayon(listeMonstre.get(i))) {
                if (monstrePlusProche == null || calculDistance(listeMonstre.get(i)) < calculDistance(monstrePlusProche)) {
                    monstrePlusProche = listeMonstre.get(i);
                }
            }
        }
        return monstrePlusProche;
    }

    private int calculDistance(Monstre monstre) {
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());
        return distanceX + distanceY;
    }

    public boolean estDansLeRayon(Monstre monstre) {
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        int distance = distanceX + distanceY;

        if (distance <= this.portee) {
            return true;
        }
        return false;
    }

    public boolean estVivant() {
        return this.nombreDePV > 0;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getPV() {
        return this.nombreDePV;
    }

    public void setSpawnAllie() {
        this.setPosX(700);
        this.setPosY(120);
    }
}