package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourHeal extends Tour {
    private int heal;
    public TourHeal() {
        super(0,0,3000,3000);
        this.heal=10;
    }


    public void competence(Base base) {
       base.ajouterPv(this.heal);
    }
}
