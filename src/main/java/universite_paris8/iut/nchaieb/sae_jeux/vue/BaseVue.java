package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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


        int largeurCase = 219;
        int hauteurCase = 375;
        int[] frameIndex = {0};



        Timeline baseAnim = new Timeline(

                new KeyFrame(Duration.millis(100), e -> {

                    int x, y;
                    if (frameIndex[0] < 8) {
                        x = frameIndex[0] % 4;
                        y= frameIndex[0] / 4;
                    } else {
                        x = frameIndex[0] - 8;
                        y = 2;
                    }



                    frameIndex[0]++;
                    if (frameIndex[0] == 11) frameIndex[0] = 0;
                    imageView.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                })
        );

        baseAnim.setCycleCount(Animation.INDEFINITE);
        baseAnim.play();






    }


}
