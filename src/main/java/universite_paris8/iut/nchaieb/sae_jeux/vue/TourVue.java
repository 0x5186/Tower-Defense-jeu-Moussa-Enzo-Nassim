package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;

import java.util.HashMap;

public class TourVue {
    private Pane pane;
    private HashMap<Tour, ImageView> hashMap = new HashMap<>();
    private HashMap<Tour, Timeline> hashMapAnimation = new HashMap<>();

    Image tourOeil = new Image(Main.class.getResourceAsStream("images/tourOeil.png"));
    Image tourHeal = new Image(Main.class.getResourceAsStream("images/tourHeal.png"));
    Image tourMusic = new Image(Main.class.getResourceAsStream("images/tourMusic.png"));
    Image tourGlace = new Image(Main.class.getResourceAsStream("images/tour-de-glace.png"));
    Image murGlace = new Image(Main.class.getResourceAsStream("images/mur-de-glace.png"));

    public TourVue(Pane pane) {
        this.pane = pane;
    }

    public void ajouterSprite(Tour tour) {
        int decalageX = 0;
        int decalageY = 0;
        ImageView iv = new ImageView();

        if (tour instanceof TourOeil) {
            decalageX = 33;
            decalageY = 67;
            iv = new ImageView(tourOeil);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }

        if (tour instanceof TourHeal) {
            decalageX = 31;
            decalageY = 70;
            iv = new ImageView(tourHeal);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }

        if (tour instanceof TourMusique) {
            decalageX = 33;
            decalageY = 67;
            iv = new ImageView(tourMusic);
            iv.setViewport(new Rectangle2D(0, 0, 80, 100));
        }


        else if (tour instanceof TourGlace) {
            decalageX = 31;
            decalageY = 70;
            iv = new ImageView(tourGlace);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }
        else if (tour instanceof MurGlace) {
            decalageX = (int) (murGlace.getWidth() / 2);
            decalageY = (int) (murGlace.getHeight() / 2);
            iv = new ImageView(murGlace);
        }
        iv.translateXProperty().bind(tour.posXProperty().subtract(decalageX));
        iv.translateYProperty().bind(tour.posYProperty().subtract(decalageY));

        this.hashMap.put(tour, iv);
        System.out.println("tour affichée");
        this.pane.getChildren().add(iv);
    }

    public void retirer(Tour tour) {
        stopAnimation(tour);

        ImageView iv = hashMap.get(tour);
        if (iv != null) {
            iv.setImage(null);
            this.pane.getChildren().remove(iv);
            this.hashMap.remove(tour);
        }
    }

    public void stopAnimation(Tour tour) {
        if (this.hashMapAnimation.containsKey(tour)) {
            Timeline timeline = this.hashMapAnimation.get(tour);
            timeline.stop();
            this.hashMapAnimation.remove(tour);
        }
    }
}