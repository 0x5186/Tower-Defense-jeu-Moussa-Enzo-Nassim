package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Projectile {
    private IntegerProperty x;
    private IntegerProperty y;
    private Monstre cible;
    private String lanceur;
    private int degats;

    private  int vitesseAttaque;
    public Projectile(int départX, int départY, Monstre cible, String lanceur, int degats, int vitesseAttaque) {
        this.x = new SimpleIntegerProperty(départX);
        this.y =  new SimpleIntegerProperty(départY);
        this.cible=cible;
        this.lanceur = lanceur;
        this.degats = degats;
        this.vitesseAttaque=2;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void projectilesAJour(){

        if (!verifPosition()){
           deplacer();

//            this.x=this.x-this.cible.getPosX()+vitesseAttaque;
//            this.y=this.y-this.cible.getPosY()+vitesseAttaque;
        }
        if(verifPosition()){
            infligerDegat();
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

        this.cible.retirerPV(degats);
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
        return Math.abs(x.get() - cible.getPosX()) <= vitesseAttaque
                && Math.abs(y.get() - cible.getPosY()) <= vitesseAttaque;
    }

    public void deplacer() {
        if (x.get() > cible.getPosX()) {
            x.set(x.get() - vitesseAttaque);
        } else if (x.get() < cible.getPosX()) {
            x.set(x.get() + vitesseAttaque);
        }

        if (y.get() > cible.getPosY()) {
            y.set(y.get() - vitesseAttaque);
        } else if (y.get() < cible.getPosY()) {
            y.set(y.get() + vitesseAttaque);
        }
    }

    public String getLanceur() {
        return lanceur;
    }

}
