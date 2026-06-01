package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

public class Sorcier extends Monstre {

    public  Sorcier(int camp){
        super(1, 1, 0, 0, 1);
        this.nombreDePV=pvMax;
        this.actionActuelle.set("fixe");
        this.portee= 100;

    }


}