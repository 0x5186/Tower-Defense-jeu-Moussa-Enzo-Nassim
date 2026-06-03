package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Tour extends Entite {
    protected int portee;
    protected double x, y;
    protected int cout;
    private final BooleanProperty modePlacementTour;
    public Tour(int portee, int atq, int x, int y, int cout) {

        this.portee = portee;
        this.cout = cout;
        this.x = x;
        this.y = y;
        this.modePlacementTour= new SimpleBooleanProperty(false);
    }

     public int getCout() {
        return this.cout;
    }
    public boolean isModePlacementTour() {
        return modePlacementTour.get();
    }

    public BooleanProperty modePlacementTourProperty() {
        return modePlacementTour;
    }


    public void setModePlacementTour(boolean modePlacementTour) {
        this.modePlacementTour.set(modePlacementTour);
    }










    public void agir(ObservableList<Monstre> listeMonstre) {
        Monstre monstrePlusProche;

        if (!listeMonstre.isEmpty()) {

            monstrePlusProche = this.plusProche(listeMonstre);
            if (monstrePlusProche != null) {
                this.setActionActuelle("fixe");
                System.out.println("pas nul");
                this.infligerDegat(monstrePlusProche);
                this.setActionActuelle("attaque");
            }

        }
    }





    public boolean estDansLeRayon (Monstre monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = distanceX + distanceY;


        //on compare la distance a la porte mais on doit les mettre à unité égale
        return distance <= this.portee;
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

//        if (monstre.nombreDePV != 0){
////            monstre.retirerPV(this.atq);
//
//        }
    }
}

