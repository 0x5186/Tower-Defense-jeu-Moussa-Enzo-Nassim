package universite_paris8.iut.nchaieb.sae_jeux.modele.Symbole;

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

    public void ajouterSymbole(String symbole) {
        if (this.combinaison.size() < 6) {
            this.combinaison.add(symbole);
        } else {
            System.out.println("Ce symbole ne sera pas comptabilisé");
        }
    }

    public void retirerSymbole() {
        // Sécurité conservée de la V1 pour éviter un crash si la liste est vide
        if (!this.combinaison.isEmpty()) {
            this.combinaison.remove(combinaison.size() - 1);
        }
    }

    public void reset() {
        this.combinaison.clear();
    }

    public boolean verifierCombinaison() {
        // Utilisation de && (V1) au lieu de || (V2) pour éviter un NullPointerException
        if (this.combinaison != null && !this.combinaison.isEmpty()) {
            if (combinaison.equals(combinaisonValables.tourOeil)) {
                System.out.println("Combinaison validée : Tour Oeil");
                return true;
            } else if (combinaison.equals(combinaisonValables.tourHeal)) {
                System.out.println("Combinaison validée : Tour Heal");
                return true;
            } else if (combinaison.equals(combinaisonValables.tourMusic)) { // Intégration V2
                System.out.println("Combinaison validée : Tour Musique");
                return true;
            }
            else if (combinaison.equals(combinaisonValables.tourGlace)){
                System.out.println("Combinaison validée : Tour Glace");
                return true;
            }
        }
        return false;
    }

    public Tour CombinaisonGetTour(int x, int y) {
        if (this.combinaison != null && !this.combinaison.isEmpty()) {
            if (combinaison.equals(combinaisonValables.tourOeil)) {
                return new TourOeil(x, y);
            } else if (combinaison.equals(combinaisonValables.tourHeal)) {
                return new TourHeal(x, y);
            } else if (combinaison.equals(combinaisonValables.tourMusic)) { // Intégration V2
                return new TourMusique(x, y);
            } else if (combinaison.equals(combinaisonValables.tourGlace)) {
                return new TourGlace(x, y);
            }
        }
        return null;
    }

    public String CombinaisonGetTourString() {
        if (this.combinaison != null && !this.combinaison.isEmpty()) {
            if (combinaison.equals(combinaisonValables.tourOeil)) {
                return "tourOeil";
            } else if (combinaison.equals(combinaisonValables.tourHeal)) {
                return "tourHeal";
            } else if (combinaison.equals(combinaisonValables.tourMusic)) { // Intégration V2
                return "tourMusique";
            } else if (combinaison.equals(combinaisonValables.tourGlace)) {
                return "tourGlace";
            }
        }
        return ""; // Retourne une chaîne vide propre si aucune correspondance
    }
}