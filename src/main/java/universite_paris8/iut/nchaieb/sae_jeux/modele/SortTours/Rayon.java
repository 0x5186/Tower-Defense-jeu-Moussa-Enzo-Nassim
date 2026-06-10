package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;


public class Rayon extends SortTour{

    private Monstre cible;
    private int vitesseAttaque;
    private DoubleProperty angle;
    private Boolean cibleAtteinte;
    private int temps;

    private int tempsAttaque;

    public Rayon(int départX, int départY, Monstre cible, int degats, int tempsAttaque) {
        super(départX, départY, degats);
        this.cible = cible;
        this.vitesseAttaque = 2;

        this.temps=0;
        this.tempsAttaque=tempsAttaque;
        this.angle= new SimpleDoubleProperty(0);

    }

    public DoubleProperty getAngle() {
        return angle;
    }


    public void sortAJour() {
        this.angle.set( Math.toDegrees(Math.atan2(this.getX()-this.cible.getPosX(),this.getY()-this.cible.getPosY()))) ;
        if (!verifPosition()) {
            deplacer();

//            this.x=this.x-this.cible.getPosX()+vitesseAttaque;
//            this.y=this.y-this.cible.getPosY()+vitesseAttaque;
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




    }

    //    public boolean verifPosition(){
//        if(this.x.equals(this.cible.getPosX())&&this.y.equals(this.cible.getPosY()) ){
//            System.out.println("true");
//            return true;
//        }
//        return false;
//    }
//
    public void infligerDegat() {

        this.cible.retirerPV(this.getDegats());
        System.out.println("pv monstre" + this.cible.getPV());
    }
//
//    public void deplacer(){
//        if(this.x.getValue()>this.cible.getPosX()){
//            this.x.set(this.x.getValue()-vitesseAttaque);
//        }
//        else if(this.x.getValue()<this.cible.getPosX()){
//            this.x.set(this.x.getValue()+vitesseAttaque);
//        }
//        if(this.y.getValue()>this.cible.getPosY()){
//            this.y.set(this.y.getValue()-vitesseAttaque);
//        }
//        else if(this.y.getValue()<this.cible.getPosY()){
//            this.x.set(this.y.getValue()+vitesseAttaque);
//        }
//
//

    /// /        this.x.setValue(this.x.getValue()+((this.cible.getPosX()-this.x.getValue())/vitesseAttaque));
    /// /        this.y.setValue(this.y.getValue()+((this.cible.getPosY()-this.y.getValue())/vitesseAttaque));
//    }
    public boolean verifPosition() {
        return Math.abs(this.getX() - cible.getPosX()) <= vitesseAttaque
                && Math.abs(this.getY() - cible.getPosY()) <= vitesseAttaque;
    }

    public void deplacer() {
        if(cibleAtteinte!= null && !cibleAtteinte){
            if (this.getX() > cible.getPosY()) {
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




}
