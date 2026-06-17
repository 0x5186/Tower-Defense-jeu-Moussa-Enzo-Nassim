package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Noeud;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour; // <-- IMPORT AJOUTÉ ICI

import java.util.ArrayList;

public abstract class Monstre extends Entite {

    public static int compteurID = 0;
    private String id;
    protected IntegerProperty nombreDePV;
    protected int pvMax;
    private int atq;
    protected int vitesse;
    protected int portee;
    protected int recompense;

    private ArrayList<Noeud> chemin;
    private boolean cheminCalcule = false;
    private final int TAILLE_TUILE = 32; // FIXÉ À 32 !
    private int targetX;
    private int targetY;

    private IntegerProperty posX;
    private IntegerProperty posY;

    public Monstre(int pvMax, int atq, int recompense, Terrain terrain) {
        this.posX = new SimpleIntegerProperty();
        this.posY = new SimpleIntegerProperty();
        this.atq = atq;
        this.pvMax = pvMax;
        this.nombreDePV= new SimpleIntegerProperty(pvMax);
        this.recompense = recompense;
        this.id = "M" + this.compteurID;
        this.compteurID++;
        this.actionActuelle.set("fixe");
        setSpawnEnnemi(terrain);
    }

    public void setSpawnEnnemi(Terrain terrain) {
        int portailAleatoire = (int) (Math.random() * 3);

        if (portailAleatoire == 0) {
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE); // Spawn 1 (Haut gauche)
            this.targetX = 58;
            this.targetY = 12;
        } else if (portailAleatoire == 1) {
            this.setPosX(24 * TAILLE_TUILE);
            this.setPosY(0);                // Spawn 2 (Haut milieu)
            this.targetX = 26;
            this.targetY = 14;
        } else {
            this.setPosX(0);
            this.setPosY(22 * TAILLE_TUILE);
            this.targetX = 58;
            this.targetY = 12;
        }

        // Sécurité
        int gx = this.getPosX() / TAILLE_TUILE;
        int gy = this.getPosY() / TAILLE_TUILE;
        if (!terrain.estPraticable(gx, gy)) {
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE);
            this.targetX = 58;
            this.targetY = 12;
        }
    }

    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base, ObservableList<Tour> lesTours) {
        if (!estBloqueParAllie(collegues)) {
            this.setActionActuelle("marche");
            this.avancer(terrain);
        }
        if(this.aAtteintSaCible()){
            base.retirerPv(this.atq);
        }
    }

    private boolean estBloqueParAllie(ObservableList<Monstre> collegues) {
        for (Monstre collegue : collegues) {
            if (collegue == this) continue;
            if (!collegue.estVivant()) continue;

            int distanceX = Math.abs(collegue.getPosX() - this.getPosX());
            int distanceY = Math.abs(collegue.getPosY() - this.getPosY());

            if (distanceX + distanceY < TAILLE_TUILE) {
                if (collegue.getId().compareTo(this.getId()) < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void avancer(Terrain terrain) {
        if (terrain == null) return;

        if (!cheminCalcule) {
            int gx = this.getPosX() / TAILLE_TUILE;
            int gy = this.getPosY() / TAILLE_TUILE;

            this.chemin = AlgorithmeAEtoile.trouverChemin(terrain, gx, gy, targetX, targetY);

            if (this.chemin != null && !this.chemin.isEmpty()) {
                this.cheminCalcule = true;
            } else {
                this.chemin = null;
                return;
            }
        }

        if (this.chemin == null || this.chemin.isEmpty()) return;

        Noeud n = this.chemin.get(0);
        int cibleX = n.x * TAILLE_TUILE;
        int cibleY = n.y * TAILLE_TUILE;
        int dx = cibleX - this.getPosX();
        int dy = cibleY - this.getPosY();

        if (dx != 0) {
            this.setPosX(this.getPosX() + (dx > 0 ? 1 : -1));
        } else if (dy != 0) {
            this.setPosY(this.getPosY() + (dy > 0 ? 1 : -1));
        }

        if (this.getPosX() == cibleX && this.getPosY() == cibleY) {
            this.chemin.remove(0);

            if (this.chemin.isEmpty()) {
                if (this.targetX != 58 || this.targetY != 12) {
                    this.targetX = 58;
                    this.targetY = 12;
                    this.cheminCalcule = false;
                }
            }
        }
    }

    public boolean aAtteintSaCible() {
        return this.cheminCalcule && this.chemin != null && this.chemin.isEmpty()
                && this.getPosX() == ((this.targetX * TAILLE_TUILE))
                && this.getPosY() == (this.targetY * TAILLE_TUILE);
    }

    public void recalculerItineraire(Terrain terrain, Base base) {
        int startX;
        int startY;
        if (this.chemin != null && !this.chemin.isEmpty()) {
            startX = this.chemin.get(0).x;
            startY = this.chemin.get(0).y;
        } else {
            startX = this.getPosX() / TAILLE_TUILE;
            startY = this.getPosY() / TAILLE_TUILE;
        }
        ArrayList<Noeud> nouveuChemin = AlgorithmeAEtoile.trouverChemin(terrain, startX, startY, this.targetX, this.targetY);
        if (nouveuChemin != null && !nouveuChemin.isEmpty()) {
            this.chemin = nouveuChemin;
            this.cheminCalcule = true;
        }
    }

    public int getAtq() { return atq; }
    public void infligerDegat(Monstre monstre) { if (monstre.nombreDePV.get() != 0) monstre.retirerPV(this.atq); }

    public void ajouterPV(int soin) { this.nombreDePV.set(Math.min(this.nombreDePV.get() + soin, this.pvMax));}

    public void retirerPV(int degat) { this.nombreDePV.set( Math.max(this.nombreDePV.get() - degat, 0)); }

    public int getPortee() { return portee; }

    public int getRecompense() { return recompense; }

    public boolean estVivant() { return this.nombreDePV.get() > 0; }

    public int getVitesse() { return vitesse; }

    public int getPV() { return this.nombreDePV.get(); }
    public IntegerProperty pvProperty() { return this.nombreDePV; }

    public int getPvMax() {return pvMax;}
    public String getId() { return this.id; }
    @Override public int getPosX() { return posX.get(); }
    @Override public IntegerProperty posXProperty() { return posX; }
    @Override public int getPosY() { return posY.get(); }
    @Override public IntegerProperty posYProperty() { return posY; }
    public void setPosY(int posY) { this.posY.set(posY); }
    public void setPosX(int posX) { this.posX.set(posX); }
}