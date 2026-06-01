package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

public class Squelette extends Monstre {
    public Squelette() {
        super(1, 1,0,0, 2);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 100;

    }

}
