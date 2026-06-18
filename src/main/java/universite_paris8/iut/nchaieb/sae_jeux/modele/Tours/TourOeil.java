package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Rayon;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourOeil extends Tour {
    private   SortTour rayon ;
    private int tempsAttaque;
    private int dureeaAttaque;

    public TourOeil(int x, int y) {
        super(150,1,x,y, 300,40);
        this.rayon=null;
        this.dureeaAttaque=100;
        this.tempsAttaque=0;
    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<SortTour> sortsTours) {
        Monstre monstrePlusProche;
        gererCooldown();




        if(this.rayon==null) {
            gererCooldown();
            this.setActionActuelle("charge");
            if ( this.getCooldown() >= this.getCooldownPourAction()) {
                if (!listeMonstre.isEmpty()) {

                    monstrePlusProche = this.plusProche(listeMonstre);
                    if (monstrePlusProche != null) {
                        this.setActionActuelle("fixe");


                        this.setActionActuelle("attaque");

                        System.out.println("zone");
                        this.rayon = new Rayon(this.getPosX(),this.getPosY()-55,monstrePlusProche,this.getAtq(), this.dureeaAttaque);
                        sortsTours.add(rayon);



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
            this.rayon = null;
        }


    }

}
