package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.vue.ProjectilesVue;

public class MonObservateurSortsTours implements ListChangeListener<SortTour> {

    private Pane pane;
    private ProjectilesVue projectilesVue;

    public MonObservateurSortsTours(Pane pane) {
        super();
        this.pane = pane;
        this.projectilesVue = new ProjectilesVue(this.pane);
    }



    private void creerSprite(SortTour projectile) {
        this.projectilesVue.ajouterSprite(projectile);

    }



    private void enleverSprite(SortTour projectile) {

        this.projectilesVue.retirerSprite(projectile);

    }

    @Override
    public void onChanged(Change<? extends SortTour> change) {

        while (change.next()) {

            if(change.wasAdded()){
                for (SortTour nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                }

            }

            if(change.wasRemoved()){
                for (SortTour ancien : change.getRemoved()) {
                    enleverSprite(ancien);
                }

            }


        }
    }


}