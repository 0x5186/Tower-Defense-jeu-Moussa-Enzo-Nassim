package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Noeud;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

import java.util.ArrayList;

public abstract class Monstre extends Entite {

    public static int compteurID = 0;
    private String id;
    protected int nombreDePV;
    protected int pvMax;
    private int atq;
    protected int vitesse;
    protected int portee;
    protected int recompense;

    private ArrayList<Noeud> chemin;
    private boolean cheminCalcule = false;
    private final int TAILLE_TUILE = 16;
    private int targetX;
    private int targetY;

    private IntegerProperty posX;
    private IntegerProperty posY;

    public Monstre(int pvMax, int atq, int recompense, Terrain terrain) {
        this.posX = new SimpleIntegerProperty();
        this.posY = new SimpleIntegerProperty();
        this.atq = atq;
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
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
            this.setPosY(8 * TAILLE_TUILE);
        } else if (portailAleatoire == 1) {
            this.setPosX(50 * TAILLE_TUILE);
            this.setPosY(0);
        } else {
            this.setPosX(10 * TAILLE_TUILE);
            this.setPosY(43 * TAILLE_TUILE);
        }

        // Fallback si la case n'est pas praticable
        int gx = this.getPosX() / TAILLE_TUILE;
        int gy = this.getPosY() / TAILLE_TUILE;
        if (!terrain.estPraticable(gx, gy)) {
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE);
        }

        this.targetX = 119;
        this.targetY = 26;
    }

    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base) {
        if (!estBloqueParAllie(collegues)) {
            this.setActionActuelle("marche");
            this.avancer(terrain);
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
        }
    }

    public boolean aAtteintSaCible() {
        return this.cheminCalcule && this.chemin != null && this.chemin.isEmpty()
                && this.getPosX() == (this.targetX * TAILLE_TUILE)
                && this.getPosY() == (this.targetY * TAILLE_TUILE);
    }

    public int getAtq() { return atq; }
    public void infligerDegat(Monstre monstre) { if (monstre.nombreDePV != 0) monstre.retirerPV(this.atq); }
    public void ajouterPV(int soin) { this.nombreDePV = Math.min(this.nombreDePV + soin, this.pvMax); }
    public void retirerPV(int degat) { this.nombreDePV = Math.max(this.nombreDePV - degat, 0); }
    public int getPortee() { return portee; }
    public int getRecompense() { return recompense; }
    public boolean estDansLeRayon(Monstre monstre) { return (Math.abs(monstre.getPosX() - this.getPosX()) + Math.abs(monstre.getPosY() - this.getPosY())) <= this.portee; }
    public boolean estVivant() { return this.nombreDePV > 0; }
    public int getVitesse() { return vitesse; }
    public int getPV() { return this.nombreDePV; }
    public String getId() { return this.id; }
    @Override public int getPosX() { return posX.get(); }
    @Override public IntegerProperty posXProperty() { return posX; }
    @Override public int getPosY() { return posY.get(); }
    @Override public IntegerProperty posYProperty() { return posY; }
    public void setPosY(int posY) { this.posY.set(posY); }
    public void setPosX(int posX) { this.posX.set(posX); }
}