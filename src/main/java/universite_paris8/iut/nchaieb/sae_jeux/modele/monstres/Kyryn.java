package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

public class Kyryn extends Monstre {
    private int cooldownSoin = 0;
    private int puissanceSoin = 30;
    private int porteeSoin = 150;

    public Kyryn (Terrain terrain) {
        super(80, 5, 35, terrain);
    }

    @Override
    public void agir(ObservableList<Monstre> collegues, Terrain terrain, Base base) {
        if (cooldownSoin > 0) {
            cooldownSoin--;
        }

        boolean aSoigne = false;

        if (cooldownSoin <= 0) {
            for (Monstre allie : collegues) {
                if (allie != this && allie.estVivant() && allie.getPV() < allie.getPvMax()) {

                    double distance = Math.sqrt(Math.pow(this.getPosX() - allie.getPosX(), 2) + Math.pow(this.getPosY() - allie.getPosY(), 2));

                    if (distance <= porteeSoin) {
                        allie.ajouterPV(puissanceSoin);
                        this.cooldownSoin = 150;
                        this.setActionActuelle("soin");
                        aSoigne = true;
                        break;
                    }
                }
            }
        }
        if (!aSoigne) {
            if (cooldownSoin < 100) {
                this.setActionActuelle("marche");
                super.agir(collegues, terrain, base);
            }
        }
    }
}