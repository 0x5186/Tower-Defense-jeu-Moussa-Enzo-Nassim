package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Rayon;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Zone;

import java.util.HashMap;

public class SortTourVue {

    private HashMap hashMap= new HashMap<SortTour,ImageView>();
    private Pane pane;

    Image note = new Image(Main.class.getResourceAsStream("images/note.png"));
    Image rayonView = new Image(Main.class.getResourceAsStream("images/rayon.png"));
    Image eclair = new Image(Main.class.getResourceAsStream("images/eclaireZone.png"));

    public SortTourVue(Pane pane) {
        this.pane = pane;


    }
//            Rayon rayon= (Rayon) sortTour;
//            imageView= new ImageView(rayonView);
//            imageView.rotateProperty().bind(rayon.getAngle());
//
    ////            imageView.setFitWidth(Math.abs(rayon.getX()-rayon.getCible().getPosX()));
//            imageView.setFitWidth(100);
//            imageView.setLayoutX(rayon.getX());
//            imageView.setLayoutY(rayon.getY());
    public void ajouterSprite(SortTour sortTour){

        if(sortTour instanceof Projectile){

            Projectile projectile = (Projectile) sortTour;

            ImageView imageView = new ImageView(note);

            imageView.translateXProperty().bind(sortTour.xProperty());
            imageView.translateYProperty().bind(sortTour.yProperty());

            hashMap.put(sortTour, imageView);
            pane.getChildren().add(imageView);

        }
        else if(sortTour instanceof Rayon){

            Rayon rayon = (Rayon) sortTour;
//
//            ImageView imageView= new ImageView(rayonView);
//            imageView.rotateProperty().bind(rayon.angleRayonProperty());
//            System.out.println(rayon.angleRayonProperty()+" degrés");
//
//            imageView.setFitWidth(Math.abs(rayon.getX()-rayon.getCible().getPosX()));
//            imageView.fitWidthProperty().bind(rayon.getCible().posXProperty().subtract(rayon.getDepartX()).add(rayon.getCible().posYProperty().subtract(rayon.getDepartY())));
//
//
//            System.out.println(rayon.getAngleRayon());
//            imageView.setLayoutX(rayon.getDepartX());
//            imageView.setLayoutY(rayon.getDepartY()-90);
////            imageView.setLayoutX(0);
////            imageView.setLayoutY(0);
//
//            imageView.setViewport(new Rectangle2D(0, 0, 96, 30));
//            int[] frameIndex = {0};
//            Timeline squeletteMarche = new Timeline(
//
//                    new KeyFrame(Duration.millis(60), e -> {
//
//
//
////                        if (frameIndex[0] == 15) frameIndex[0] = 0;
//
//                        imageView.setViewport(new Rectangle2D(frameIndex[0] * 96, 0, 96, 30));
//                        frameIndex[0]++;
//
//
//                    })
//            );
//
//
//            squeletteMarche.setCycleCount(Animation.INDEFINITE);
//            squeletteMarche.play();
//
//

            Line line = new Line();

            line.setStartX(rayon.getX());
            line.setStartY(rayon.getY());

            line.endXProperty().bind(rayon.xProperty());
            line.endYProperty().bind(rayon.yProperty());

            line.setStroke(Color.GOLD);
            line.setStrokeWidth(4);


            hashMap.put(sortTour, line);
            pane.getChildren().add(line);
//            System.out.println(imageView.getParent());
        }
        else if(sortTour instanceof Zone){

            Zone zone = (Zone) sortTour;
            ImageView imageView = new ImageView(eclair);
            imageView.layoutXProperty().bind(sortTour.xProperty());
            imageView.layoutYProperty().bind(sortTour.yProperty());
            imageView.setX((double) -384 /2); //décalage
            imageView.setY((double) -384 /2);
            imageView.setScaleX(((double) zone.getPortee() /384)*2);
            imageView.setScaleY(((double) zone.getPortee() /384)*2 );
            imageView.setViewport(new Rectangle2D(0, 0, 384, 384));
            int[] frameIndex = {0};
            Timeline squeletteMarche = new Timeline(

                    new KeyFrame(Duration.millis(100), e -> {


//                        if (frameIndex[0] == 15) frameIndex[0] = 0;

                        imageView.setViewport(new Rectangle2D(frameIndex[0] * 384, 0, 384, 384));
                        frameIndex[0]++;

                    })
            );

            squeletteMarche.setCycleCount(Animation.INDEFINITE);
            squeletteMarche.play();
            hashMap.put(sortTour, imageView);
            pane.getChildren().add(imageView);

        }
    }

    public void retirerSprite(SortTour projectile){

        Object node = hashMap.get(projectile);

        if(node != null){
            pane.getChildren().remove(node);
            hashMap.remove(projectile);
        }
    }
//    public void retirerSprite(SortTour projectile){
//        ImageView  iv= (ImageView) hashMap.get(projectile);
//        iv.setImage(null);
//        this.pane.getChildren().remove(iv);
//        this.hashMap.remove(projectile, iv);
//    }

}
