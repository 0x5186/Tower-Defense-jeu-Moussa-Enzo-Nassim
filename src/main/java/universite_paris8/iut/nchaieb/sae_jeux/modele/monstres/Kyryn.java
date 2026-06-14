package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

public class Kyryn extends Monstre {
    private int cooldownSoin = 0;
    private int puissanceSoin = 20;
    private int porteeSoin = 200;

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
                if (allie != this && allie.estVivant() && allie.getPV() < allie.getPvMax() && allie.getSpawnIndex() == this.getSpawnIndex()) {
                    double distance = Math.sqrt(Math.pow(this.getPosX() - allie.getPosX(), 2) + Math.pow(this.getPosY() - allie.getPosY(), 2));

                    if (distance <= porteeSoin) {
                        allie.ajouterPV(puissanceSoin);
                        aSoigne = true;
                    }
                }
            }
            if (aSoigne) {
                this.cooldownSoin = 400;
                this.setActionActuelle("soin");
                System.out.println("✨ Le Kyryn a soigné son groupe de " + puissanceSoin + " PV !");
            }
        }
        if (!aSoigne) {
            if (cooldownSoin < 350) {
                this.setActionActuelle("marche");
                super.agir(collegues, terrain, base);
            }
        }
    }
}