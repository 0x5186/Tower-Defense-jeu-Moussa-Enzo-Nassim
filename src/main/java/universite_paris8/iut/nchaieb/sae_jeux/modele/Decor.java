package universite_paris8.iut.nchaieb.sae_jeux.modele;


import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;

public class Decor {

    private double x;
    private double y;
    private double rayonDetection;
    private String etat;

    public Decor(double x, double y, double rayonDetection, String etatInitial) {
        this.x = x;
        this.y = y;
        this.rayonDetection = rayonDetection;
        this.etat = etatInitial;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRayonDetection() {
        return this.rayonDetection;
    }

    public String getEtat() {
        return this.etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
