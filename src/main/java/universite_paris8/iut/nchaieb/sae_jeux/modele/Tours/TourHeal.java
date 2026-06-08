package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourHeal extends Tour {
    private int heal;


    public TourHeal(int x, int y) {
        super(0,0,x,y, 6000, 50);
        this.heal=10;

    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base) {

        gererCooldown();
        System.out.println(this.getCooldown());
        if(this.getCooldown()==this.getCooldownPourAction() && base.getPv()!=base.getPvMax()){
            System.out.println("je heal");
            base.ajouterPv(this.heal);
            this.setCooldown(0);

        }

    }


}
