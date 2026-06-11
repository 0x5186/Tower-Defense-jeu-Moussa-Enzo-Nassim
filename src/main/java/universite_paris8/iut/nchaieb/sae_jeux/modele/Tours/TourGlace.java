package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class TourGlace extends Tour{

    public TourGlace(int x, int y) {
        super(100, 0 ,x,y,60,0);
    }
    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<Projectile> projectiles){
        //pas d'attaque
        //voir environnement pr gérer le mur
    }
}
