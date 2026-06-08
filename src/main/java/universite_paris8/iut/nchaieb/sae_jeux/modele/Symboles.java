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
        if (this.combinaison.size() <6 ){
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


    public boolean verifierCombinaison(){

        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil) || combinaison.equals(combinaisonValables.tourHeal)){
              return true;
            }
        }

        return false;
    }

    public Tour CombinaisonGetTour(int x, int y){ //vérifie la combinaison et invoquie le monstre si elle est bonne
        Tour tour = null;


        System.out.println(x + y);
        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)){
                tour= new TourOeil(x,y);

            }
            else if(combinaison.equals(combinaisonValables.tourHeal)){
                tour= new TourHeal(x,y);

            }
        }

        return tour;
    }







    public String CombinaisonGetTourString(){ //vérifie la combinaison et invoquie le monstre si elle est bonne
        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)){
                return "tourOeil";

            }
            else if(combinaison.equals(combinaisonValables.tourHeal)){
                return "tourHeal";

            }
        }

        return "rien";
    }
}
