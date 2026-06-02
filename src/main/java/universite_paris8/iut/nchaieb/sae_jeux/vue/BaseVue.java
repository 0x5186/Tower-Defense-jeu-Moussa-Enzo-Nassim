package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

public class BaseVue {
    private Pane pane;
    Image tour_magicien = new Image(Main.class.getResourceAsStream("images/tour_magicien.png"));

    public BaseVue(Pane pane) {
        this.pane = pane;
    }
    public void ajouterSprite (Base base) {
        ImageView imageView=  new ImageView(tour_magicien);
        imageView.setViewport(new Rectangle2D(0,0,219,375));
        imageView.setLayoutX(base.getPosX());
        imageView.setLayoutY(base.getPosY());
        pane.getChildren().add(imageView);
    }

}
