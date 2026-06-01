package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
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



    private void enleverSprite(Entite entite) {


    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Tour> change) {

        while (change.next()) {
            if (change.wasAdded()) {


                for (Tour nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                    nouveau.modePlacementTourProperty().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals(true)) {

                            System.out.println("ohhhhhh mince");
                            this.tourVue.ajouterImageSouris(nouveau);
                        }
                        if (newValue.equals(false)) {
                            this.tourVue.retirerImageSouris(nouveau);
                        }
                    });


                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals("fixe")) {

                            this.tourVue.stopAnimation(nouveau);
                        }
                        if (newValue.equals("attaque")) {
                            this.tourVue.animationAttaque(nouveau);
                        }
                    });





                }
            }
            if(change.wasRemoved()) {
                System.out.println("je suis morttttttttttttt");
                for (Entite mort : change.getRemoved()) {
                    enleverSprite(mort);
                }
            }

        }
    }
}
