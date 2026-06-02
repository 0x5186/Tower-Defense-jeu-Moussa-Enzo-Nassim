package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourOeil extends Tour {

    public TourOeil() {
        super(100,6,3000,3000);
    }

//    public void agir(ObservableList<Monstre> listeMonstre) {
//        Monstre monstrePlusProche;
//
//        if (!listeMonstre.isEmpty()) {
//
//            monstrePlusProche = this.plusProche(listeMonstre);
//            if (monstrePlusProche != null) {
//                this.setActionActuelle("fixe");
//                System.out.println("pas nul");
//                this.infligerDegat(monstrePlusProche);
//                this.setActionActuelle("attaque");
//            }
//
//        }
//    }
}
