package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Rayon;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Zone;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourTesla extends Tour {

    private   SortTour zone ;
    private int tempsAttaque;
    private int dureeaAttaque;


    public TourTesla(int x, int y) {
        super(100, 2, x, y, 100, 10);
        this.zone=null;
        this.dureeaAttaque=60;
        this.tempsAttaque=0;
    }


    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<SortTour> sortsTours) {
        Monstre monstrePlusProche;
        if(this.zone==null) {
            gererCooldown();
            if ( this.getCooldown() >= this.getCooldownPourAction()) {
                if (!listeMonstre.isEmpty()) {

                    monstrePlusProche = this.plusProche(listeMonstre);
                    if (monstrePlusProche != null) {
                        this.setActionActuelle("fixe");


                        this.setActionActuelle("attaque");

                        System.out.println("zone");
                        this.zone = new Zone(this.getPosX(), this.getPosY(), this.getAtq(), this.portee, listeMonstre, 60);
                        sortsTours.add(zone);



                    }

                }

            }
        }
        else{
            this.tempsAttaque++;
        }

        if (this.getCooldown() + this.tempsAttaque >= this.getCooldownPourAction() + this.dureeaAttaque) {
            this.setCooldown(0);
            this.tempsAttaque = 0;
            this.zone = null;

        }



    }

}

