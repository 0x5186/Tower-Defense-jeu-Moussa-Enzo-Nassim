package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;


public class Rayon extends SortTour{

    private int departX;
    private int departY;

    private Monstre cible;
    private int vitesseAttaque;

    private Boolean cibleAtteinte;
    private int temps;
    private DoubleProperty angleRayon;
    private int tempsAttaque;

    public Rayon(int départX, int départY, Monstre cible, int degats, int tempsAttaque) {
        super(départX, départY, degats);
        this.departX=départX;
        this.departY=départY;
        this.cible = cible;
        this.vitesseAttaque = 2;
        this.cibleAtteinte=false;
        this.temps=0;
        this.tempsAttaque=tempsAttaque;
        this.angleRayon= new SimpleDoubleProperty();



    }



    public void sortAJour() {

        System.out.println("rayon");
        if (!verifPosition()) {
            deplacer();


        }
        else {
            this.xProperty().bind(cible.posXProperty());
            this.yProperty().bind(cible.posYProperty());
            infligerDegat();
            this.temps++;
        }
        if(temps>=tempsAttaque || !cible.estVivant()){
            this.setAttaqueFini(true);
        }
        this.angleRayon.set( Math.toDegrees(Math.atan2(this.getY() - this.departY, this.getX() - this.departX)));
        System.out.println("angle"+this.angleRayon);



    }



    public double getAngleRayon() {
        System.out.println(this.angleRayon);
        return angleRayon.get();
    }

    public DoubleProperty angleRayonProperty() {

        return angleRayon;
    }

    public void infligerDegat() {

        this.cible.retirerPV(this.getDegats());
        System.out.println("pv monstre" + this.cible.getPV());
    }

    public boolean verifPosition() {
        return Math.abs(this.getX() - cible.getPosX()) <= vitesseAttaque
                && Math.abs(this.getY() - cible.getPosY()) <= vitesseAttaque;
    }

    public void deplacer() {
        if(cibleAtteinte!= null && !cibleAtteinte){
            if (this.getX() > cible.getPosX()) {
                this.setX(this.getX() - vitesseAttaque);
            } else if (this.getX() < cible.getPosX()) {
                this.setX(this.getX() + vitesseAttaque);
            }

            if (this.getY() > cible.getPosY()) {
                this.setY(this.getY() - vitesseAttaque);
            } else if (this.getY() < cible.getPosY()) {
                this.setY(this.getY() + vitesseAttaque);
            }
        }

    }



    public Monstre getCible() {
        return cible;
    }

    public int getDepartX() {
        return departX;
    }

    public int getDepartY() {
        return departY;
    }



}
