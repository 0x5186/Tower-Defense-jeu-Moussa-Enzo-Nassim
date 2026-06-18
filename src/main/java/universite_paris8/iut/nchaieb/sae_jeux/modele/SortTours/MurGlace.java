package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourGlace;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class MurGlace extends SortTour {

    private TourGlace tourParente;
    private Terrain terrain;
    private ObservableList<Monstre> listeMonstre;
    private Base base;

    private int caseX1, caseY1, caseX2, caseY2;
    private int tempsRestant;

    public MurGlace(int pixelX, int pixelY, TourGlace tourParente, Terrain terrain, ObservableList<Monstre> listeMonstre, Base base, int caseX1, int caseY1, int caseX2, int caseY2, int dureeTicks) {
        super(pixelX, pixelY, 0);
        this.tourParente = tourParente;
        this.terrain = terrain;
        this.listeMonstre = listeMonstre;
        this.base = base;

        this.caseX1 = caseX1;
        this.caseY1 = caseY1;
        this.caseX2 = caseX2;
        this.caseY2 = caseY2;
        this.tempsRestant = dureeTicks;

        this.terrain.setCaseBloquee(caseX1, caseY1, true);
        this.terrain.setCaseBloquee(caseX2, caseY2, true);

        recalculerItineraireMonstres();
    }

    @Override
    public void sortAJour() {
        this.tempsRestant--;

        if (this.tempsRestant <= 0) {
            this.terrain.setCaseBloquee(caseX1, caseY1, false);
            this.terrain.setCaseBloquee(caseX2, caseY2, false);

            recalculerItineraireMonstres();

            this.setAttaqueFini(true);
        }
    }

    private void recalculerItineraireMonstres() {
        for (Monstre m : listeMonstre) {
            m.recalculerItineraire(this.terrain, this.base);
        }
    }

    public TourGlace getTourParente() {
        return tourParente;
    }

    public int getCaseX1() { return caseX1; }
    public int getCaseY1() { return caseY1; }
    public int getCaseX2() { return caseX2; }
    public int getCaseY2() { return caseY2; }
}