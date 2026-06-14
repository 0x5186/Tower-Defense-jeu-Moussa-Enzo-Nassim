package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
    Image eclair = new Image(Main.class.getResourceAsStream("images/eclair.png"));

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

            Line line = new Line();

            line.startXProperty().bind(rayon.xProperty());
            line.startYProperty().bind(rayon.yProperty());

            line.endXProperty().bind(rayon.getCible().posXProperty());
            line.endYProperty().bind(rayon.getCible().posYProperty());

            line.setStroke(Color.GOLD);
            line.setStrokeWidth(8);

            hashMap.put(sortTour, line);
            pane.getChildren().add(line);
        }
        else if(sortTour instanceof Zone){
            Zone zone = (Zone) sortTour;
            ImageView imageView = new ImageView(eclair);
            imageView.setLayoutX(sortTour.getX());
            imageView.setLayoutY(sortTour.getY());
            imageView.setScaleX(((double) zone.getPortee() /32)*2);
            imageView.setScaleY(((double) zone.getPortee() /32)*2);


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
