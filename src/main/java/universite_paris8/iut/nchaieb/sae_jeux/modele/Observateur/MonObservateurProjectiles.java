package universite_paris8.iut.nchaieb.sae_jeux.modele.Observateur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.vue.ProjectilesVue;

public class MonObservateurProjectiles implements ListChangeListener<Projectile> {

    private Pane pane;
    private ProjectilesVue projectilesVue;

    public MonObservateurProjectiles(Pane pane) {
        super();
        this.pane = pane;
        this.projectilesVue = new ProjectilesVue(this.pane);
    }



    private void creerSprite(Projectile projectile) {
        this.projectilesVue.ajouterSprite(projectile);

    }



    private void enleverSprite(Projectile projectile) {

        this.projectilesVue.retirerSprite(projectile);

    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {

        while (change.next()) {

            if(change.wasAdded()){
                for (Projectile nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                }

            }

            if(change.wasRemoved()){
                for (Projectile ancien : change.getRemoved()) {
                    enleverSprite(ancien);
                }

            }


        }
    }


}