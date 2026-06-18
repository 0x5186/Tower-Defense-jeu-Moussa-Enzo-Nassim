package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;

public class Boss extends Monstre {

    private static final int TAILLE_TUILE_BOSS = 32;
    private static final int PORTEE_EN_CASES = 2;

    private Tour tourCible;
    private int cooldownAttaque;

    public Boss(Terrain terrain) {
        super(750, 34, 150, terrain);
        this.nombreDePV.set(pvMax);
        this.portee = PORTEE_EN_CASES * TAILLE_TUILE_BOSS;
        this.actionActuelle.set("marche");
        this.tourCible = null;
        this.cooldownAttaque = 0;
    }

    @Override
    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base, ObservableList<Tour> lesTours) {

        if (tourCible != null && !tourCible.estVivant()) {
            tourCible = null;
            this.setActionActuelle("marche");
        }

        if (tourCible == null) {
            double minDist = this.portee;
            for (Tour t : lesTours) {
                double dist = Math.abs(t.getPosX() - this.getPosX()) + Math.abs(t.getPosY() - this.getPosY());
                if (dist <= minDist && t.estVivant()) {
                    minDist = dist;
                    tourCible = t;
                }
            }
        }

        if (tourCible != null) {
            this.setActionActuelle("attaque");
            cooldownAttaque++;

            if (cooldownAttaque >= 40) {
                tourCible.retirerPv(this.getAtq());
                cooldownAttaque = 0;
            }
        }
        else {
            super.agir(collegues, terrain, base, lesTours);
        }
    }
}