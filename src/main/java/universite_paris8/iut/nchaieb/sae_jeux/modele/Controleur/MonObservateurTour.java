package universite_paris8.iut.nchaieb.sae_jeux.modele.Controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TourVue;


public class MonObservateurTour implements ListChangeListener<Tour>{

    private Pane pane;
    private TourVue tourVue;

    public MonObservateurTour(Pane panneauJeu) {
        super();
        this.pane = panneauJeu;
        this.tourVue = new TourVue(this.pane);
    }



    private void creerSprite(Tour tour) {
        this.tourVue.ajouterSprite(tour);
    }



    private void enleverSprite(Tour tour) {
        this.tourVue.retirerSprite(tour);
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Tour> change) {

        while (change.next()) {
            if (change.wasAdded()) {
                for (Tour nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                    nouveau.actionActuelleProperty().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals("charge")){
                            this.tourVue.animationChargeAttaque(nouveau);
                        }
                        else if (newValue.equals("attaque")){
                            this.tourVue.animationAttaque(nouveau);
                        }
                        else if (newValue.equals("fixe")){
                            this.tourVue.stopAnimation(nouveau);
                        }


                    });



                }
            }
            if(change.wasRemoved()) {
                System.out.println("je suis morttttttttttttt");
                for (Tour mort : change.getRemoved()) {
                    enleverSprite(mort);
                }
            }

        }
    }
}