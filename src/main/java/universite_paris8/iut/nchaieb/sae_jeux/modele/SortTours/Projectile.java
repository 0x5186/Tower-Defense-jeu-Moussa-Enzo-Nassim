package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Projectile  extends SortTour{
    private Monstre cible;
    private String lanceur;


    private  int vitesseAttaque;
    public Projectile(int départX, int départY, Monstre cible, String lanceur, int degats, int vitesseAttaque) {
        super(départX, départY, degats);

        this.cible=cible;
        this.lanceur = lanceur;

        this.vitesseAttaque=2;

    }


    public void sortAJour(){

        if (!verifPosition()){
           deplacer();
        }
        if(verifPosition()){
            infligerDegat();
            this.setAttaqueFini(true);
        }


    }
//
//    public boolean verifPosition(){
//        if(this.x.equals(this.cible.getPosX())&&this.y.equals(this.cible.getPosY()) ){
//            System.out.println("true");
//            return true;
//        }
//        return false;
//    }
//
    public void infligerDegat(){

        this.cible.retirerPV(this.getDegats());
        System.out.println("pv monstre" +this.cible.getPV());
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
////        this.x.setValue(this.x.getValue()+((this.cible.getPosX()-this.x.getValue())/vitesseAttaque));
////        this.y.setValue(this.y.getValue()+((this.cible.getPosY()-this.y.getValue())/vitesseAttaque));
//    }
    public boolean verifPosition() {
        return Math.abs(this.getX() - cible.getPosX()) <= vitesseAttaque
            && Math.abs(this.getY() - cible.getPosY()) <= vitesseAttaque;
    }

    public void deplacer() {
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

    public String getLanceur() {
        return lanceur;
    }

}
