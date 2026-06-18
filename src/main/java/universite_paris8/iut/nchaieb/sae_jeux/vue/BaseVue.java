package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;

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

    public void ajouterSprite(Base base) {
        int largeurCase = 219;
        int hauteurCase = 400;
        int nbFrames    = 13;

        ImageView imageView = new ImageView(tour_magicien);
        imageView.setViewport(new Rectangle2D(0, 0, largeurCase, hauteurCase));

        double ancreBasX = base.getPosX() + 16 - (largeurCase / 2.0);
        double ancreBasY = base.getPosY() + 32;

        imageView.setLayoutX(ancreBasX);
        imageView.setLayoutY(ancreBasY - hauteurCase);


        double largeurBarre = 120;
        double hauteurBarre = 18;

        Rectangle fondBarre = new Rectangle(largeurBarre, hauteurBarre);
        fondBarre.setFill(Color.rgb(40, 40, 40));
        fondBarre.setStroke(Color.BLACK);
        fondBarre.setStrokeWidth(2);

        Rectangle vieBarre = new Rectangle(largeurBarre, hauteurBarre);
        vieBarre.setFill(Color.LIMEGREEN);

        Text textePV = new Text();
        textePV.setFill(Color.WHITE);
        textePV.setStyle("-fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-font-size: 14px;");


        double barX = base.getPosX() + 16 - (largeurBarre / 2.0);
        double barY = ancreBasY + 10;

        fondBarre.setLayoutX(barX);
        fondBarre.setLayoutY(barY);
        vieBarre.setLayoutX(barX);
        vieBarre.setLayoutY(barY);
        textePV.setLayoutX(barX + 30);
        textePV.setLayoutY(barY + 14);

        vieBarre.widthProperty().bind(
                base.pvProperty().multiply(largeurBarre).divide(base.getPvMax())
        );
        textePV.textProperty().bind(
                base.pvProperty().asString().concat(" / ").concat(String.valueOf(base.getPvMax()))
        );

        pane.getChildren().addAll(imageView, fondBarre, vieBarre, textePV);

        int[] frameIndex = {0};

        Timeline baseAnim = new Timeline(
                new KeyFrame(Duration.seconds(0.12), e -> {
                    imageView.setViewport(new Rectangle2D(
                            frameIndex[0] * largeurCase,
                            0,
                            largeurCase,
                            hauteurCase
                    ));

                    imageView.setLayoutY(ancreBasY - hauteurCase);
                    imageView.setTranslateY(0);

                    frameIndex[0]++;
                    if (frameIndex[0] >= nbFrames) frameIndex[0] = 0;
                })
        );

        baseAnim.setCycleCount(Animation.INDEFINITE);
        baseAnim.play();
    }
    public void rechargerpart2(){
        part2.toFront();


    }
}