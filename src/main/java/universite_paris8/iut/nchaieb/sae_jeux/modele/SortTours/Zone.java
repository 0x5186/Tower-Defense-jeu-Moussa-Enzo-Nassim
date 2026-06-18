package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.Outils;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

import java.util.ArrayList;

public class Zone  extends SortTour {


    private  ObservableList<Monstre> listeMonstre;
    private int portee;

    private int temps;

    private int tempsAttaque;
    public Zone(int x, int y, int degats, int portee  , ObservableList<Monstre> listeMonstre,  int tempsAttaque) {
        super(x, y, degats);

        this.portee=portee;
        this.listeMonstre= listeMonstre;
        this.temps=0;
        this.tempsAttaque=tempsAttaque;


    }


    public void sortAJour() {
        this.temps++;
        System.out.println("a jour");
        for (int i = this.listeMonstre.size() - 1; i >= 0; i--) {
            System.out.println("liste monstre");
            if (this.getOutils().estDansLeRayon(this.getX(), this.getY(), listeMonstre.get(i).getPosX(), listeMonstre.get(i).getPosY(), this.portee)) {
                System.out.println("est dans le rayon");
                infligerDegat( listeMonstre.get(i));
            }
        }
        if(this.temps>=tempsAttaque){
            this.setAttaqueFini(true);
        }

    }
    public void infligerDegat(Monstre monstre) {

        monstre.retirerPV(this.getDegats());
        System.out.println("j'attaque" );

    }


    public int getPortee() {
        return portee;
    }
}