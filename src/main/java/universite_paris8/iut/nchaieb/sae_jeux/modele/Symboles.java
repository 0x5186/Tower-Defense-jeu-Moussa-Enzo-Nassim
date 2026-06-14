package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;

public class Symboles {
    private ObservableList<String> combinaison;
    private CombinaisonValables combinaisonValables;

    public Symboles() {
        this.combinaison = FXCollections.observableArrayList();
        this.combinaisonValables = new CombinaisonValables();
    }

    public ObservableList<String> getCombinaison() {
        return combinaison;
    }

    public void ajouterSymbole(String symbole){
        boolean afficherAvertissement = false;

        if (this.combinaison.size() < 6){
            this.combinaison.add(symbole);
        } else {
            System.out.println("Ce symbole ne sera pas comptabilisé");
            afficherAvertissement = true;
        }
    }

    public void retirerSymbole() {
        // Sécurité : on vérifie que la liste n'est pas vide avant de retirer !
        if (!this.combinaison.isEmpty()) {
            this.combinaison.remove(combinaison.size() - 1);
        }
    }

    public void reset() {
        this.combinaison.clear();
    }

    public boolean verifierCombinaison() {
        // CORRECTION : && (ET) au lieu de || (OU)
        if (this.combinaison != null && !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)
                    || combinaison.equals(combinaisonValables.tourHeal)
                    || combinaison.equals(combinaisonValables.tourMusic)
                    || combinaison.equals(combinaisonValables.tourTesla)){
                return true;
            }
            else if (combinaison.equals(combinaisonValables.tourGlace)){
                System.out.println("Combinaison validée : Tour Glace");
                return true;
            }
        }
        return false;
    }

    // CORRECTION : Les deux méthodes fusionnées proprement
    public Tour CombinaisonGetTour(int x, int y) {
        if (this.combinaison != null && !this.combinaison.isEmpty()) {

            if (combinaison.equals(combinaisonValables.tourOeil)) {
                return new TourOeil(x, y);
            }
            else if (combinaison.equals(combinaisonValables.tourHeal)) {
                return new TourHeal(x, y);
            }
            else if (combinaison.equals(combinaisonValables.tourMusic)) {
                return new TourMusique(x, y);
            }
            else if (combinaison.equals(combinaisonValables.tourGlace)) {
                return new TourGlace(x, y);
            }
            else if (combinaison.equals(combinaisonValables.tourTesla)) {
                return new TourTesla(x, y);
            }
        }
        return null;
    }

    public String CombinaisonGetTourString() {
        // CORRECTION : && (ET) au lieu de || (OU)
        if (this.combinaison != null && !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)) {
                return "tourOeil";
            }
            else if (combinaison.equals(combinaisonValables.tourHeal)) {
                return "tourHeal";
            }
            else if (combinaison.equals(combinaisonValables.tourMusic)) {
                return "tourMusique";
            }
            else if (combinaison.equals(combinaisonValables.tourGlace)) {
                return "tourGlace";
            }
            else if (combinaison.equals(combinaisonValables.tourTesla)) {
                return "tourTesla";
            }
        }
        return "rien";
    }
}