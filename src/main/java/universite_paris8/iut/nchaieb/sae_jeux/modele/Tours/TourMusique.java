package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourMusique extends Tour{

    public TourMusique(int x, int y) {
        super(300,3,x,y, 100,10);

    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<SortTour> projectiles) {
        Monstre monstrePlusProche;
        gererCooldown();

        if(this.getCooldown()==this.getCooldownPourAction()){
            if (!listeMonstre.isEmpty() ) {

                monstrePlusProche = this.plusProche(listeMonstre);
                if (monstrePlusProche != null) {
                    this.setActionActuelle("fixe");
                    System.out.println("x="+this.getPosX());
                    System.out.println("y="+this.getPosY());
                    SortTour note = new Projectile(this.getPosX(), this.getPosY(),monstrePlusProche, "tourmusique",this.getAtq(), 5);
                    projectiles.add(note);
                    this.setActionActuelle("attaque");
                    this.setCooldown(0);
                    System.out.println("j'attaque");
                }

            }
        }

    }
}
