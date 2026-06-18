package universite_paris8.iut.nchaieb.sae_jeux.modele.Artefacts;

public class Epee extends Artefact{

    private int bonusAtq;

    public Epee(){
        super("épée", "images/épée.png");
        this.bonusAtq = 10;
    }

    public int getBonusAtq(){
        return bonusAtq;
    }
}
