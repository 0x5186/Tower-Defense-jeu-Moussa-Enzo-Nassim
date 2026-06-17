package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;

public class Kyryn extends Monstre {

    private int compteurTicks;

    public Kyryn(Terrain terrain) {
        super(150, 10, 40, terrain);
        this.nombreDePV.set(pvMax);
        this.actionActuelle.set("fixe");
        this.portee = 50;
        this.compteurTicks = 0;
    }

    @Override
    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base, ObservableList<Tour> lesTours) {
        super.agir(collegues, terrain, base, lesTours);
        this.compteurTicks++;

        if (this.compteurTicks >= 300) {
            this.soignerAllies(collegues);
            this.compteurTicks = 0;
        }
    }

    private void soignerAllies(ObservableList<Monstre> collegues) {
        Monstre monstreLeplusBlesse = null;
        double ratioPvMin = 1.0;
        for (Monstre autre : collegues) {
            if (autre.estVivant() && autre != this) {
                double ratioPvActuelle = (double) autre.getPV() / autre.getPvMax();

                if( ratioPvActuelle < ratioPvMin) {
                    ratioPvMin = ratioPvActuelle;
                    monstreLeplusBlesse = autre;
                }
            }
        }
        if (monstreLeplusBlesse != null && ratioPvMin < 1.0){
            monstreLeplusBlesse.ajouterPV(40);
        }
    }
}