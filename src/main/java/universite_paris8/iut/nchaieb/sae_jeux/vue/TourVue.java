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
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.*;

import java.util.HashMap;

public class TourVue {

    private Pane pane;
    private HashMap<Tour, ImageView>  hashMap          = new HashMap<>();
    private HashMap<Tour, Timeline>   hashMapAnimation = new HashMap<>();

    // ── images ───────────────────────────────────────────────────────────────
    Image tourOeil  = new Image(Main.class.getResourceAsStream("images/tourOeil.png"));
    Image tourHeal  = new Image(Main.class.getResourceAsStream("images/tourHeal.png"));
    Image tourMusic = new Image(Main.class.getResourceAsStream("images/tourMusic.png"));
    Image tourGlace = new Image(Main.class.getResourceAsStream("images/tour-de-glace.png"));
    Image murGlace  = new Image(Main.class.getResourceAsStream("images/mur-de-glace.png"));
    Image tourTesla = new Image(Main.class.getResourceAsStream("images/tourTesla.png"));

    // ── constructeur ─────────────────────────────────────────────────────────
    public TourVue(Pane pane) {
        this.pane = pane;
    }

    // ── ajouterSprite ────────────────────────────────────────────────────────
    public void ajouterSprite(Tour tour) {
        int decalageX = 0;
        int decalageY = 0;
        ImageView iv  = new ImageView();

        // Tous les blocs sont des "if" indépendants — pas de else if accidentel
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
            iv.setScaleX(2);
            iv.setScaleY(2);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }
        if (tour instanceof TourMusique) {
            decalageX = 36;
            decalageY = 90;
            iv = new ImageView(tourMusic);
            iv.setViewport(new Rectangle2D(0, 0, 80, 100));
        }
        if (tour instanceof TourGlace) {         // ← ajouté depuis v2
            decalageX = 31;
            decalageY = 70;
            iv = new ImageView(tourGlace);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
        }
        if (tour instanceof TourTesla) {
            decalageX = 33;
            decalageY = 80;
            iv = new ImageView(tourTesla);
            iv.setViewport(new Rectangle2D(0, 0, 80, 90));
        }
        if (tour instanceof MurGlace) {          // ← ajouté depuis v2
            decalageX = (int) (murGlace.getWidth()  / 2);
            decalageY = (int) (murGlace.getHeight() / 2);
            iv = new ImageView(murGlace);
        }

        iv.translateXProperty().bind(tour.posXProperty().subtract(decalageX));
        iv.translateYProperty().bind(tour.posYProperty().subtract(decalageY));

        this.hashMap.put(tour, iv);
        System.out.println("tour affichée");
        this.pane.getChildren().add(iv);
    }

    // ── retirer ──────────────────────────────────────────────────────────────
    public void retirer(Tour tour) {
        ImageView iv = hashMap.get(tour);
        iv.setImage(null);
        this.pane.getChildren().remove(iv);
        this.hashMap.remove(tour, iv);
    }

    // ── stopAnimation ────────────────────────────────────────────────────────
    public void stopAnimation(Tour tour) {
        if (this.hashMapAnimation.containsKey(tour)) {
            Timeline timeline = this.hashMapAnimation.get(tour);
            timeline.stop();
            this.hashMapAnimation.remove(tour);
        }
    }

    // ── animationChargeAttaque ───────────────────────────────────────────────
    public void animationChargeAttaque(Tour tour) {
        ImageView iv        = this.hashMap.get(tour);
        int largeurCase     = 80;
        int[] frameIndex    = {0};

        if (tour instanceof TourTesla) {
            int hauteurCase = 90;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(150), e -> {
                frameIndex[0]++;
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));
            }));
            tl.setCycleCount(12);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
        else if (tour instanceof TourOeil) {
            int hauteurCase = 80;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(90), e -> {
                frameIndex[0]++;
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));
            }));
            tl.setCycleCount(27);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
        else if (tour instanceof TourHeal) {
            int hauteurCase = 77;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), e -> {
                frameIndex[0]++;
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));
                if (frameIndex[0] == 19) frameIndex[0] = 0;
            }));
            tl.setCycleCount(Animation.INDEFINITE);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
    }

    // ── animationAttaque ─────────────────────────────────────────────────────
    public void animationAttaque(Tour tour) {
        ImageView iv     = this.hashMap.get(tour);
        int largeurCase  = 80;
        int[] frameIndex = {0};

        if (tour instanceof TourTesla) {
            frameIndex[0] = 12;
            int hauteurCase = 90;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(40), e -> {
                frameIndex[0]++;
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 0, largeurCase, hauteurCase));
            }));
            tl.setCycleCount(4);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
        else if (tour instanceof TourOeil) {
            int hauteurCase = 80;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(90), e -> {
                frameIndex[0]++;
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, hauteurCase, largeurCase, hauteurCase));
            }));
            tl.setCycleCount(12);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
        else if (tour instanceof TourHeal) {
            int hauteurCase = 77;
            Timeline tl = new Timeline(new KeyFrame(Duration.millis(90), e -> {
                iv.setViewport(new Rectangle2D(frameIndex[0] * largeurCase, 1, largeurCase, hauteurCase));
                if (frameIndex[0] == 19) frameIndex[0] = 0;
                frameIndex[0]++;
            }));
            tl.setCycleCount(32);
            tl.play();
            this.hashMapAnimation.put(tour, tl);
        }
    }
}