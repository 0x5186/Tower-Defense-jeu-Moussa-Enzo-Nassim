package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Squelette extends Monstre {
    public Squelette(Terrain terrain) {
        super(13, 1,10,terrain);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 100;

    }

}
