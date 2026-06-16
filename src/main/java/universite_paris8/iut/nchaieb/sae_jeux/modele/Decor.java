package universite_paris8.iut.nchaieb.sae_jeux.modele;


import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;

public class Decor {

    private double x;
    private double y;
    private double rayonDetection;
    private String etat;
    private double taille;
    private double rayonPlacable;

    public Decor(double x, double y, double rayonDetection, String etatInitial, double taille, double rayonPlacable) {
        this.x = x;
        this.y = y;
        this.rayonDetection = rayonDetection;
        this.etat = etatInitial;
        this.taille = taille;
        this.rayonPlacable = rayonPlacable;
    }

    public Decor(double x, double y, double rayonDetection, double taille){
        this.x = x;
        this.y = y;
        this.rayonDetection = rayonDetection;
        this.taille = taille;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRayonDetection() {
        return this.rayonDetection;
    }

    public String getEtat() {
        return this.etat;
    }

    public double getTaille(){
        return this.taille;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public boolean estDansLeRayonDecor(int gridX, int gridY){
        //juste repris méthode tourPosable
        int TAILLE_TUILE = 32;
        int decorGridX = (int) (this.getX() / TAILLE_TUILE);
        int decorGridY = (int) (this.getY() / TAILLE_TUILE);
        int distance = Math.abs(gridX - decorGridX) + Math.abs(gridY - decorGridY);
        return distance <= this.rayonPlacable;
    }

}
