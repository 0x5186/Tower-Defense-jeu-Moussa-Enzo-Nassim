package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourHeal;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourOeil;

public class Symboles {
    private ObservableList<String> combinaison;
    private CombinaisonValables combinaisonValables;

    public Symboles() {
        this.combinaison = FXCollections.observableArrayList();
        this.combinaisonValables= new CombinaisonValables();
    }

    public ObservableList<String> getCombinaison() {
        return combinaison;
    }

    public void ajouterSymbole(String symbole){
        if (this.combinaison.size() != 6){
            this.combinaison.add(symbole);
        } else {
            System.out.println("ce symbole ne sera pas comptabilisé");

        }
    }


    public void retirerSymbole (){
        this.combinaison.remove(combinaison.size()-1);
    }
    public void reset (){
        this.combinaison.clear();
    }

    public Tour verifierCombinaison(){ //vérifie la combinaison et invoquie le monstre si elle est bonne
        Tour tour = null;


        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)){
                System.out.println("combinaison validé");
                tour= new TourOeil();

            }
            else if(combinaison.equals(combinaisonValables.tourHeal)){
                tour= new TourHeal();

            }
        }

        return tour;

    }

}
