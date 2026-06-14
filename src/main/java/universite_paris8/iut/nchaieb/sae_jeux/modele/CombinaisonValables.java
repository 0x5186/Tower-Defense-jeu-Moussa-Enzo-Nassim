package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonValables {
    protected ArrayList<String> tourOeil;
    protected ArrayList<String> tourHeal;
    protected ArrayList<String> tourMusic;
    protected ArrayList<String> tourTesla;
    protected ArrayList<String> tourGlace;


    public CombinaisonValables() {
        this.tourOeil = new ArrayList<String>(List.of("oeil", "croix", "eclipse"));
        this.tourHeal = new ArrayList<String>(List.of("croix","goutte" ,"crystal", "spirale"));
        this.tourMusic = new ArrayList<String>(List.of("note","corne","triangle","crystal"));
        this.tourTesla = new ArrayList<String>(List.of("tomoe"));
        this.tourGlace = new ArrayList<String>(List.of("spirale","crystal"));
    }


}
