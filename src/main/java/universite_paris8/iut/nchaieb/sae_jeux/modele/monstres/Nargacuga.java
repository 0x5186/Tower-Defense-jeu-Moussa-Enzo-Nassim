package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Nargacuga extends Monstre {
    public Nargacuga (Terrain terrain){
        super(1, 10, 4,terrain);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 2;
    }
}
