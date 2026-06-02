package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Tour extends Entite {
    protected int portee;
    protected double x, y;
    private int atq;
    private final BooleanProperty modePlacementTour;
    public Tour(int portee, int atq, int x, int y) {
        this.atq=atq;
        this.portee = portee;
        this.x = x;
        this.y = y;
        this.modePlacementTour= new SimpleBooleanProperty(false);
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
            System.out.println("pas vide");
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
        System.out.println(monstrePlusProche);
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

