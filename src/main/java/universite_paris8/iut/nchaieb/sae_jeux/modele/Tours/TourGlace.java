package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Noeud;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.MurGlace;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

import java.util.ArrayList;

public class TourGlace extends Tour {

    private Terrain terrain;

    private MurGlace mur;

    public TourGlace(int x, int y) {
        super(100, 0, x, y, 300, 0);
        this.mur = null;
    }

    @Override
    public void agir(ObservableList<Monstre> listeMonstre, Base base, ObservableList<SortTour> sortTours) {
        gererCooldown();

        if (this.mur != null && this.mur.isAttaqueFini()) {
            this.mur = null; // Le mur a fondu, on libère la mémoire
        }

        if (this.mur == null && this.getCooldown() >= this.getCooldownPourAction()) {

            int tourX = this.getPosX() / 32;
            int tourY = this.getPosY() / 32;

            int meilleurX = -1, meilleurY = -1, meilleurJumeauX = -1, meilleurJumeauY = -1;
            double minDist = Double.MAX_VALUE;

            for (int dy = -10; dy <= 10; dy++) {
                for (int dx = -10; dx <= 10; dx++) {
                    int caseCibleX = tourX + dx;
                    int caseCibleY = tourY + dy;

                    if (terrain.estCheminNaturel(caseCibleX, caseCibleY)) {

                        double dist = Math.sqrt(dx * dx + (dy + 2) * (dy + 2));

                        if (dist < minDist) {

                            int jumeauX = caseCibleX, jumeauY = caseCibleY;

                            if (!terrain.estCheminNaturel(caseCibleX - 1, caseCibleY) || !terrain.estCheminNaturel(caseCibleX + 1, caseCibleY)) {
                                if (terrain.estCheminNaturel(caseCibleX - 1, caseCibleY)) jumeauX = caseCibleX - 1;
                                else if (terrain.estCheminNaturel(caseCibleX + 1, caseCibleY)) jumeauX = caseCibleX + 1;
                            }
                            else if (!terrain.estCheminNaturel(caseCibleX, caseCibleY - 1) || !terrain.estCheminNaturel(caseCibleX, caseCibleY + 1)) {
                                if (terrain.estCheminNaturel(caseCibleX, caseCibleY - 1)) jumeauY = caseCibleY - 1;
                                else if (terrain.estCheminNaturel(caseCibleX, caseCibleY + 1)) jumeauY = caseCibleY + 1;
                            }

                            terrain.setCaseBloquee(caseCibleX, caseCibleY, true);
                            terrain.setCaseBloquee(jumeauX, jumeauY, true);

                            ArrayList<Noeud> chemin1 = AlgorithmeAEtoile.trouverChemin(terrain, 0, 8, 58, 12);
                            ArrayList<Noeud> chemin2 = AlgorithmeAEtoile.trouverChemin(terrain, 24, 0, 58, 12);
                            ArrayList<Noeud> chemin3 = AlgorithmeAEtoile.trouverChemin(terrain, 0, 22, 58, 12);

                            boolean aStarOk = (chemin1 != null && !chemin1.isEmpty() &&
                                    chemin2 != null && !chemin2.isEmpty() &&
                                    chemin3 != null && !chemin3.isEmpty());

                            terrain.setCaseBloquee(caseCibleX, caseCibleY, false);
                            terrain.setCaseBloquee(jumeauX, jumeauY, false);

                            if (aStarOk) {
                                minDist = dist;
                                meilleurX = caseCibleX;
                                meilleurY = caseCibleY;
                                meilleurJumeauX = jumeauX;
                                meilleurJumeauY = jumeauY;
                            }
                        }
                    }
                }
            }

            if (meilleurX != -1) {
                int dureeMur = 300;

                this.mur = new MurGlace(meilleurX * 32, meilleurY * 32, this, terrain, listeMonstre, base, meilleurX, meilleurY, meilleurJumeauX, meilleurJumeauY, dureeMur);
                sortTours.add(this.mur);
                this.setCooldown(0);
            }
        }
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}