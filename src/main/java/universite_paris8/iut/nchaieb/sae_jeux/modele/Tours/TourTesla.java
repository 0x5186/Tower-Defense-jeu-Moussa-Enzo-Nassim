package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Zone;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourTesla extends Tour {

    private   SortTour zone ;
    private int tempsAttaque;
    private int dureeaAttaque;


    public TourTesla(int x, int y) {
        super(100, 1, x, y, 200, 15);
        this.zone=null;
        this.dureeaAttaque=120;
        this.tempsAttaque=0;
    }


    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<SortTour> sortsTours) {
        Monstre monstrePlusProche;
        if(this.zone==null) {
            gererCooldown();
            this.setActionActuelle("charge");
            if ( this.getCooldown() >= this.getCooldownPourAction()) {
                if (!listeMonstre.isEmpty()) {

                    monstrePlusProche = this.plusProche(listeMonstre);
                    if (monstrePlusProche != null) {



                        this.setActionActuelle("attaque");

                        this.zone = new Zone(this.getPosX(), this.getPosY(), this.getAtq(), this.portee, listeMonstre, this.dureeaAttaque);
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
            this.setActionActuelle("fixe");

        }



    }

}

