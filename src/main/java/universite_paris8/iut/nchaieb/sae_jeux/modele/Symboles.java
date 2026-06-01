package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
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
        this.combinaison.add(symbole);
    }


    public void retirerSymbole (){
        // pr éviter un bug d'index invalide
         if (!this.combinaison.isEmpty()){
             this.combinaison.remove(combinaison.size()-1);
         }
    }
    public void reset (){
        this.combinaison.clear();
    }

    public Tour verifierCombinaison(){ //vérifie la combinaison et invoquie le monstre si elle est bonne
        Tour tour = null;
        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){
            for(int i=0;i<combinaison.size();i++)
                System.out.println(combinaison.get(i));
            if (combinaison.equals(combinaisonValables.tourOeil)){
                System.out.println("combinaison validé");
                tour= new TourOeil();

            }
        }

        return tour;

    }

}
