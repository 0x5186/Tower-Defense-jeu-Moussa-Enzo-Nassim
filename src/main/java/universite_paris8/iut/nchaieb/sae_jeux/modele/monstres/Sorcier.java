package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

public class Sorcier extends Monstre {

    public  Sorcier(Terrain terrain){
        super(10, 10, 1,terrain);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 100;
    }


}