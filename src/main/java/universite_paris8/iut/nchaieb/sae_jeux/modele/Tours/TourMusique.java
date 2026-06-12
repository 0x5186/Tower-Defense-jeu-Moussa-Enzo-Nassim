package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourMusique extends Tour{

    public TourMusique(int x, int y) {
        super(300,3,x,y, 150,15);

    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<Projectile> projectiles) {
        Monstre monstrePlusProche;
        gererCooldown();

        if(this.getCooldown()==this.getCooldownPourAction()){
            if (!listeMonstre.isEmpty() ) {

                monstrePlusProche = this.plusProche(listeMonstre);
                if (monstrePlusProche != null) {
                    this.setActionActuelle("fixe");
                    System.out.println("x="+this.getPosX());
                    System.out.println("y="+this.getPosY());
                    Projectile note = new Projectile(this.getPosX(), this.getPosY(),monstrePlusProche, "tourmusique",this.getAtq(), 5);
                    projectiles.add(note);
//                    this.infligerDegat(monstrePlusProche);
                    this.setActionActuelle("attaque");
                    this.setCooldown(0);
                    System.out.println("j'attaque");
                }

            }
        }

    }
}
