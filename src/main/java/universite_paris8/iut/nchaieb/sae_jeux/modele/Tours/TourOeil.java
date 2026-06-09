package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourOeil extends Tour {

    public TourOeil(int x, int y) {
        super(150,5,x,y, 50,25);

    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base) {
        Monstre monstrePlusProche;
        gererCooldown();

        if(this.getCooldown()==this.getCooldownPourAction()){
            if (!listeMonstre.isEmpty() ) {

                monstrePlusProche = this.plusProche(listeMonstre);
                if (monstrePlusProche != null) {
                    this.setActionActuelle("fixe");

                    this.infligerDegat(monstrePlusProche);
                    this.setActionActuelle("attaque");
                    this.setCooldown(0);
                    System.out.println("j'attaque");
                }

            }
        }

    }

}
