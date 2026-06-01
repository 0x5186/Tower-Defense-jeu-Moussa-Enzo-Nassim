package universite_paris8.iut.nchaieb.sae_jeux.vue;

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
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourOeil;

import java.util.HashMap;

public class TourVue {
    private Pane pane;
    private HashMap hashMap= new HashMap<Tour, ImageView>();
    private HashMap hashMapAnimation= new HashMap<Tour, Timeline>();
    Image tourOeil = new Image(Main.class.getResourceAsStream("images/tourOeil.png"));
    Image tourOeilCurseur = new Image(Main.class.getResourceAsStream("images/tourOeilCurseur.png"));




    public TourVue(Pane pane) {
        this.pane= pane;
    }

    public void ajouterSprite(Tour tour){

        ImageView  iv= new ImageView();
        iv.setViewport(new Rectangle2D(0,0,240,240));
        if(tour instanceof TourOeil){
            iv=new ImageView(tourOeil);
            iv.setViewport(new Rectangle2D(0,0,80,80));
        }
        iv.translateXProperty().bind(tour.posXProperty());
        iv.translateYProperty().bind(
                tour.posYProperty()
        );
        this.hashMap.put(tour, iv);




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

    public void ajouterImageSouris(Tour tour){// change l'image de la souris pour la tour qu'on veut placer
        if(tour instanceof TourOeil) {
            pane.setCursor(new ImageCursor(tourOeilCurseur));
        }

    }

    public void retirerImageSouris(Tour tour) {// change l'image de la souris pour la tour qu'on veut placer

        pane.setCursor(Cursor.DEFAULT);

    }


        public void animationAttaque(Entite monstre) {

        ImageView iv = (ImageView) this.hashMap.get(monstre);


        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {13};



        Timeline squeletteMarche = new Timeline(

                new KeyFrame(Duration.millis(100), e -> {

                    int x, y;
                    if (frameIndex[0] < 25) {
                        x = frameIndex[0] % 6;
                        y = frameIndex[0] / 6;
                    } else {
                        x = frameIndex[0] - 24;
                        y = 4;
                    }
                    frameIndex[0]++;
                    if (frameIndex[0] == 27) frameIndex[0] = 12;
                    iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                })
        );
        this.hashMapAnimation.put(monstre, squeletteMarche);
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();






    }

}
