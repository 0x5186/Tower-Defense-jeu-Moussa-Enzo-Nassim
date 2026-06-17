package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Squelette extends Monstre {
    public Squelette(Terrain terrain) {
        super(30, 1,2,terrain);
        this.actionActuelle.set("fixe");
        this.portee= 100;
    }

}
