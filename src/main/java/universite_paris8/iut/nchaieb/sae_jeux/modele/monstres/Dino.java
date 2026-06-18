package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
public class Dino extends Monstre{
    public Dino(Terrain terrain) {
        super( 150,5,2, terrain);
        this.nombreDePV.set(pvMax);
        this.actionActuelle.set("fixe");
        this.portee= 100;
    }
}
