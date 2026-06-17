package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Nargacuga extends Monstre {
    public Nargacuga (Terrain terrain){
        super(75, 10, 10,terrain);
        this.nombreDePV.set(pvMax);
        this.actionActuelle.set("fixe");
        this.portee= 2;
    }
}
