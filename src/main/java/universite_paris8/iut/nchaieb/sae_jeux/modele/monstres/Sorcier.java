package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Sorcier extends Monstre {

    public  Sorcier(Terrain terrain){
        super(50, 5, 5,terrain);

        this.actionActuelle.set("fixe");
        this.portee= 100;
    }


}