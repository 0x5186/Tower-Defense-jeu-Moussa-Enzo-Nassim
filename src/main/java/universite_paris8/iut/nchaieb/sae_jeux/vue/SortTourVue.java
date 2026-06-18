package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.MurGlace;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Projectile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Rayon;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.SortTour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours.Zone;

import java.util.HashMap;

public class SortTourVue {

    private HashMap<SortTour, Node> hashMap = new HashMap<>();
    private Pane pane;

    Image note = new Image(Main.class.getResourceAsStream("images/note.png"));
    Image rayonView = new Image(Main.class.getResourceAsStream("images/rayon.png"));
    Image eclair = new Image(Main.class.getResourceAsStream("images/eclaireZone.png"));

    Image murGlace = new Image(Main.class.getResourceAsStream("images/mur-de-glace.png"));

    public SortTourVue(Pane pane) {
        this.pane = pane;
    }

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
            line.setStartX(rayon.getX());
            line.setStartY(rayon.getY());
            line.endXProperty().bind(rayon.xProperty());
            line.endYProperty().bind(rayon.yProperty());
            line.setStroke(Color.GOLD);
            line.setStrokeWidth(4);
            hashMap.put(sortTour, line);
            pane.getChildren().add(line);

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
                        imageView.setViewport(new Rectangle2D(frameIndex[0] * 384, 0, 384, 384));
                        frameIndex[0]++;
                    })
            );
            squeletteMarche.setCycleCount(Animation.INDEFINITE);
            squeletteMarche.play();
            hashMap.put(sortTour, imageView);
            pane.getChildren().add(imageView);

        }

        else if (sortTour instanceof MurGlace) {
            MurGlace mur = (MurGlace) sortTour;
            javafx.scene.Group groupeMur = new javafx.scene.Group();
            int tailleBloc = 48;
            int decalageX = 8;
            int decalageY = 16;
            ImageView iv1 = new ImageView(murGlace);
            iv1.setFitWidth(tailleBloc);
            iv1.setFitHeight(tailleBloc);
            iv1.setTranslateX((mur.getCaseX1() * 32) - decalageX);
            iv1.setTranslateY((mur.getCaseY1() * 32) - decalageY);
            ImageView iv2 = new ImageView(murGlace);
            iv2.setFitWidth(tailleBloc);
            iv2.setFitHeight(tailleBloc);
            iv2.setTranslateX((mur.getCaseX2() * 32) - decalageX);
            iv2.setTranslateY((mur.getCaseY2() * 32) - decalageY);
            groupeMur.getChildren().addAll(iv1, iv2);
            hashMap.put(sortTour, groupeMur);
            pane.getChildren().add(groupeMur);
        }
    }

    public void retirerSprite(SortTour sortTour){
        Node node = hashMap.get(sortTour);
        if(node != null){
            pane.getChildren().remove(node);
            hashMap.remove(sortTour);
        }
    }
}