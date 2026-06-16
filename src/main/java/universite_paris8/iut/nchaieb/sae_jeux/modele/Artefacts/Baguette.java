package universite_paris8.iut.nchaieb.sae_jeux.modele.Artefacts;

public class Baguette extends Artefact{

    private int reductionCooldown;

    public Baguette(){
        super("baguette", "images/baguette.png");
        this.reductionCooldown = 30;
    }

    public int getReductionCooldown(){
        return this.reductionCooldown;
    }
}
