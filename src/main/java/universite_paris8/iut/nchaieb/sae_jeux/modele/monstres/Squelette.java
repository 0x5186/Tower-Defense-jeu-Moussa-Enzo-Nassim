package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Squelette extends Monstre {
    public Squelette(Terrain terrain) {
        super(100, 1,10,terrain);

        this.actionActuelle.set("fixe");
        this.portee= 100;

    }

}
