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
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class BaseVue {
    private Pane pane;
    private Base base;
    Image tour_magicien = new Image(Main.class.getResourceAsStream("images/tour_magicien.png"));
    Image tour_magicien_part2 = new Image(Main.class.getResourceAsStream("images/tour_magicien_part2.png"));
    ImageView part2 =new ImageView(tour_magicien_part2);



    public BaseVue(Pane pane,Base base) {
        this.pane = pane;
        this.base=base;
    }


    public void ajouterSprite () {
        ImageView imageView=  new ImageView(tour_magicien);

        imageView.setViewport(new Rectangle2D(0,0,219,375));

        part2.setLayoutX(base.getPosX());
        part2.setLayoutY(base.getPosY());
        imageView.setLayoutX(base.getPosX());
        imageView.setLayoutY(base.getPosY());
        pane.getChildren().add(imageView);
        pane.getChildren().add(part2);



        int largeurCase = 219;
        int hauteurCase = 400;
        int[] frameIndex = {0};



        Timeline baseAnim = new Timeline(

                new KeyFrame(Duration.seconds(0.15), e -> {



                    frameIndex[0]++;
                    if (frameIndex[0] == 11) frameIndex[0] = 0;
                    imageView.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));

                })
        );

        baseAnim.setCycleCount(Animation.INDEFINITE);
        baseAnim.play();






    }
    public void rechargerpart2(){
        part2.toFront();


    }



}
