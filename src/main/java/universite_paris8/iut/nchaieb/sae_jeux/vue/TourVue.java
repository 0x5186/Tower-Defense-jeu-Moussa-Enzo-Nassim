package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

import java.util.HashMap;

public class TourVue {
    private Pane pane;
    private HashMap hashMap= new HashMap<Tour, ImageView>();
    private HashMap hashMapAnimation= new HashMap<Tour, Timeline>();
    Image tourOeil = new Image(Main.class.getResourceAsStream("images/tourOeil.png"));
    Image tourHeal = new Image(Main.class.getResourceAsStream("images/tourHeal.png"));
    Image tourMusic = new Image(Main.class.getResourceAsStream("images/tourMusic.png"));
    Image tourTesla = new Image(Main.class.getResourceAsStream("images/tourTesla.png"));
    Image tourGlace = new Image(Main.class.getResourceAsStream("images/tour-de-glace.png"));

    public TourVue(Pane pane) {
        this.pane= pane;
    }

    public void ajouterSprite(Tour tour){

        int decalageX=0;
        int decalageY=0;
        ImageView  iv= new ImageView();

        if(tour instanceof TourOeil){
            decalageX=33;
            decalageY=67;
            iv=new ImageView(tourOeil);
            iv.setViewport(new Rectangle2D(0,0,80,80));

        }
        if(tour instanceof TourHeal){
            decalageX=31;
            decalageY=70;

            iv=new ImageView(tourHeal);
            iv.setScaleX(2);
            iv.setScaleY(2);
            iv.setViewport(new Rectangle2D(0,0,80,80));
        }
        if(tour instanceof TourMusique){
            decalageX=36;
            decalageY=90;
            iv=new ImageView(tourMusic);
            iv.setViewport(new Rectangle2D(0,0,80,100));

        }
        if(tour instanceof TourTesla){
            decalageX=33;
            decalageY=80;
            iv=new ImageView(tourTesla);
            iv.setViewport(new Rectangle2D(0,0,80,90));
        }
        if (tour instanceof TourGlace) {
            decalageX = 35;
            decalageY = 75;
            iv = new ImageView(tourGlace);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }

        iv.translateXProperty().bind(
                tour.posXProperty().subtract(decalageX)
        );

        iv.translateYProperty().bind(
                tour.posYProperty().subtract(decalageY)
        );
        this.hashMap.put(tour, iv);

        System.out.println("tour affichée");

        this.pane.getChildren().add(iv);
    }

    public void retirer(Tour tour){
        ImageView  iv= (ImageView) hashMap.get(tour);
        iv.setImage(null);
        this.pane.getChildren().remove(iv);
        this.hashMap.remove(tour, iv);
    }

    public void stopAnimation(Tour tour){
        if(this.hashMapAnimation.containsKey(tour)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(tour);
            timeline.stop();
            this.hashMapAnimation.remove(tour);
        }
    }

    public void animationChargeAttaque(Tour tour) {

        ImageView iv = (ImageView) this.hashMap.get(tour);

        int largeurCase = 80;
        int hauteurCase;
        int[] frameIndex = {0};

        if (tour instanceof TourTesla) {
            hauteurCase = 90;
            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(150), e -> {

                        frameIndex[0]++;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(12);
            tourTeslaAttaque.play();

        }
        else if (tour instanceof TourOeil) {
            hauteurCase = 80;
            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(90), e -> {

                        frameIndex[0]++;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(27);
            tourTeslaAttaque.play();

        }
        else if (tour instanceof TourHeal) {
            hauteurCase = 77;

            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(100), e -> {

                        frameIndex[0]++;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));
                        if(frameIndex[0]==19)   frameIndex[0]=0;

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(Animation.INDEFINITE);
            tourTeslaAttaque.play();

        }

        else if (tour instanceof TourGlace) {
            hauteurCase = 80;
            Timeline tourGlaceAnim = new Timeline(
                    new KeyFrame(Duration.millis(100), e -> {
                        int x = (frameIndex[0] % 3) * largeurCase;
                        int y = (frameIndex[0] / 3) * hauteurCase;

                        iv.setViewport(new Rectangle2D(x, y, largeurCase, hauteurCase));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 6) frameIndex[0] = 0;
                    })
            );
            this.hashMapAnimation.put(tour, tourGlaceAnim);
            tourGlaceAnim.setCycleCount(6);
            tourGlaceAnim.play();
        }

    }

    public void animationAttaque(Tour tour) {

        ImageView iv = (ImageView) this.hashMap.get(tour);

        int largeurCase = 80;
        int hauteurCase ;
        int[] frameIndex = {0};

        if (tour instanceof TourTesla) {
            frameIndex[0]=12;
            hauteurCase = 90;
            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(40), e -> {

                        frameIndex[0]++;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(4);
            tourTeslaAttaque.play();

        }
        else if (tour instanceof TourOeil) {
            hauteurCase = 80;
            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(90), e -> {

                        frameIndex[0]++;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, hauteurCase, largeurCase, hauteurCase));

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(12);
            tourTeslaAttaque.play();

        }
        else if (tour instanceof TourHeal) {
            hauteurCase = 77;

            Timeline tourTeslaAttaque = new Timeline(

                    new KeyFrame(Duration.millis(90), e -> {
                        int y;
                        y=1;

                        iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, y, largeurCase, hauteurCase));
                        if(frameIndex[0]==19){
                            frameIndex[0]=0;
                            y=2;
                        }
                        frameIndex[0]++;

                    })
            );
            this.hashMapAnimation.put(tour, tourTeslaAttaque);
            tourTeslaAttaque.setCycleCount(32);
            tourTeslaAttaque.play();

        }

    }

}