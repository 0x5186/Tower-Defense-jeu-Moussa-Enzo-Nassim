package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;
import java.util.List;

public class CombinaisonValables {
    protected ArrayList<String> tourOeil;
    protected ArrayList<String> tourHeal;


    public CombinaisonValables() {
        this.tourOeil = new ArrayList<String>(List.of("oeil", "croix", "eclipse"));
        this.tourHeal = new ArrayList<String>(List.of("croix","goutte" ,"crystal", "spirale"));
    }


}
