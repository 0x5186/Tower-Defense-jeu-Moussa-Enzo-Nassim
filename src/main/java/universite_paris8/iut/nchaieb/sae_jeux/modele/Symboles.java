package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;

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

    public boolean ajouterSymbole(String symbole){

        if (this.combinaison.size() <6 ){
            this.combinaison.add(symbole);
            return true;
        } else {
            return false;
        }
    }

    public boolean listeEstComplet(){
        if (this.combinaison.size() == 6){
            return true;
        }
        return false;
    }


    public void retirerSymbole (){
        this.combinaison.remove(combinaison.size()-1);
    }
    public void reset (){
        this.combinaison.clear();
    }


    public boolean verifierCombinaison(){

        if (this.combinaison!=null ||    !this.combinaison.isEmpty()){

            if (combinaison.equals(combinaisonValables.tourOeil)
                    || combinaison.equals(combinaisonValables.tourHeal)
                    || combinaison.equals(combinaisonValables.tourMusic)
                    || combinaison.equals(combinaisonValables.tourTesla)){
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
            else if( combinaison.equals(combinaisonValables.tourMusic)){
                tour= new TourMusique(x,y);

            }
            else if( combinaison.equals(combinaisonValables.tourTesla)){
                tour= new TourTesla(x,y);

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
            else if(combinaison.equals(combinaisonValables.tourMusic)){
                return "tourMusique";

            }
            else if(combinaison.equals(combinaisonValables.tourTesla)){
                return "tourTesla";

            }
        }

        return "rien";
    }
}
