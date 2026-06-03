package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Tour extends Entite {
    protected int portee;

    private int atq;

    private int cooldown;
    private int cooldownPourAction; //temps de chargement d'une attaque
    public Tour(int portee, int atq, int x, int y, int cooldownPourAction) {
        super(x,y);
        this.atq=atq;
        this.portee = portee;


        this.cooldown=0;
        this.cooldownPourAction=cooldownPourAction;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCooldownPourAction() {
        return cooldownPourAction;
    }


    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }




//
//    public void agir(ObservableList<Monstre> listeMonstre) {
//        Monstre monstrePlusProche;
//        gererCooldown();
//        System.out.println(cooldown);
//        if(this.cooldown==cooldownPourAttaque){
//            if (!listeMonstre.isEmpty() ) {
//
//                monstrePlusProche = this.plusProche(listeMonstre);
//                if (monstrePlusProche != null) {
//                    this.setActionActuelle("fixe");
//
//                    this.infligerDegat(monstrePlusProche);
//                    this.setActionActuelle("attaque");
//                    this.cooldown=0;
//                    System.out.println("j'attaque");
//                }
//
//            }
//        }
//
//    }

    public void gererCooldown() {
        if(this.cooldown<this.cooldownPourAction){
            System.out.println("+1");
            System.out.println("cooldown action: "+ this.cooldownPourAction);
            this.cooldown++;
        }
        else {
            System.out.println("=");
            this.cooldown=this.cooldownPourAction;
        }
    }



    public void agir(ObservableList<Monstre> listeMonstre, Base base){}

    public boolean estDansLeRayon (Monstre monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = distanceX+distanceY;

        //on compare la distance a la porte mais on doit les mettre à unité égale
        if (distance <= this.portee) {
            return true;
        }

        return false;
    }

    public int getPortee() {
        return portee;
    }



    public Monstre plusProche (ObservableList<Monstre> listeMonstre){

        Monstre monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(estDansLeRayon(listeMonstre.get(i))){
                if( monstrePlusProche==null || calculDistance(listeMonstre.get(i))<calculDistance(monstrePlusProche)){
                    monstrePlusProche=listeMonstre.get(i);

                }
            }
        }

        return monstrePlusProche;

    }

    private int calculDistance(Monstre monstre) {
        int distance = (monstre.getPosX()+ monstre.getPosY())-(getPosY()+getPosX());
        if (distance<0)
            distance=distance*-1;
        return distance;
    }



    public  void infligerDegat(Monstre monstre){
        System.out.println("tour attaque");
        if (monstre.getPV() != 0){
            monstre.retirerPV(this.atq);

        }
    }
}

