package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
// 🟢 L'import est réparé !
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.*;

import java.util.HashMap;

public class MonstreVue {
    private Pane pane;

    private HashMap<Entite, ImageView> hashMap = new HashMap<>();
    private HashMap<Entite, Timeline> hashMapAnimation = new HashMap<>();
    private HashMap<Entite, Rectangle[]> hashMapBarres = new HashMap<>();

    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette(3).png"));
    Image sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));
    Image nargacuga = new Image(Main.class.getResourceAsStream("images/nargacuga.png"));
    Image Dino = new Image(Main.class.getResourceAsStream("images/dino.png"));
    Image Armure = new Image(Main.class.getResourceAsStream("images/armure.png"));
    Image Kyryn = new Image(Main.class.getResourceAsStream("images/kyryn.png"));

    public MonstreVue(Pane pane) {
        this.pane = pane;
    }

    public void ajouterSprite(Monstre monstre) {
        ImageView iv = new ImageView();
        int offsetYBarre = 0;

        if (monstre instanceof Squelette) {
            iv = new ImageView(squelette);
            iv.setViewport(new Rectangle2D(0, 0, 50, 50));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(17));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(17));
            offsetYBarre = 25;
        }
        else if (monstre instanceof Sorcier) {
            iv = new ImageView(sorcier);
            iv.setViewport(new Rectangle2D(0, 0, 72, 72));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(32));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(32));
            offsetYBarre = 40;
        }
        else if (monstre instanceof Nargacuga) {
            iv = new ImageView(nargacuga);
            iv.setViewport(new Rectangle2D(0, 0, 100, 100));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
            offsetYBarre = 55;
        }
        else if (monstre instanceof Dino) {
            iv = new ImageView(Dino);
            iv.setViewport(new Rectangle2D(0,0,80,80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
            offsetYBarre = 55;
        }
        else if (monstre instanceof Armure) {
            iv = new ImageView(Armure);
            iv.setViewport(new Rectangle2D(0,0,80,80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
            offsetYBarre = 55;
        }
        else if (monstre instanceof Kyryn) {
            iv = new ImageView(Kyryn);
            int largeurCaseK = (int) (Kyryn.getWidth() / 2);
            int hauteurCaseK = (int) (Kyryn.getHeight() / 3);
            iv.setViewport(new Rectangle2D(0, 0, largeurCaseK, hauteurCaseK));
            iv.translateXProperty().bind(monstre.posXProperty().subtract((largeurCaseK - 32) / 2.0));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(hauteurCaseK - 32));
            offsetYBarre = 55;
        }

        this.hashMap.put(monstre, iv);
        this.pane.getChildren().add(iv);

        double largeurBarre = 36;
        double hauteurBarre = 6;
        Rectangle fondBarre = new Rectangle(largeurBarre, hauteurBarre);
        fondBarre.setFill(Color.rgb(40, 40, 40));
        fondBarre.setStroke(Color.BLACK);
        fondBarre.setStrokeWidth(1);

        Rectangle vieBarre = new Rectangle(largeurBarre, hauteurBarre);
        vieBarre.setFill(Color.LIMEGREEN);

        fondBarre.translateXProperty().bind(monstre.posXProperty().subtract(largeurBarre / 2.0));
        fondBarre.translateYProperty().bind(monstre.posYProperty().subtract(offsetYBarre));
        vieBarre.translateXProperty().bind(monstre.posXProperty().subtract(largeurBarre / 2.0));
        vieBarre.translateYProperty().bind(monstre.posYProperty().subtract(offsetYBarre));
        vieBarre.widthProperty().bind(monstre.pvProperty().multiply(largeurBarre).divide(monstre.getPvMax()));

        monstre.pvProperty().addListener((obs, oldVal, newVal) -> {
            double ratio = newVal.doubleValue() / monstre.getPvMax();
            if (ratio > 0.50) vieBarre.setFill(Color.LIMEGREEN);
            else if (ratio > 0.20) vieBarre.setFill(Color.YELLOW);
            else vieBarre.setFill(Color.RED);
        });

        this.hashMapBarres.put(monstre, new Rectangle[]{fondBarre, vieBarre});
        this.pane.getChildren().addAll(fondBarre, vieBarre);
    }

    public void retirer(Entite entite) {
        ImageView iv = this.hashMap.get(entite);
        if (iv != null) {
            iv.setImage(null);
            this.pane.getChildren().remove(iv);
            this.hashMap.remove(entite);
        }

        if (this.hashMapBarres.containsKey(entite)) {
            Rectangle[] barres = this.hashMapBarres.get(entite);
            this.pane.getChildren().removeAll(barres[0], barres[1]);
            this.hashMapBarres.remove(entite);
        }
    }

    public void stopAnimation(Monstre monstre) {
        if (this.hashMapAnimation.containsKey(monstre)) {
            Timeline timeline = this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }
    }

    public void animationMarche(Entite monstre) {
        stopAnimation((Monstre) monstre);

        ImageView iv = this.hashMap.get(monstre);
        int largeurCase = 50;
        int hauteurCase = 50;

        if (monstre instanceof Squelette) {
            int[] frameIndex = {0};
            Timeline squeletteMarche = new Timeline(
                    new KeyFrame(Duration.millis(100), e -> {
                        int x, y;
                        if (frameIndex[0] < 12) {
                            x = frameIndex[0] % 6;
                            y = frameIndex[0] / 6;
                        } else {
                            x = frameIndex[0] - 12;
                            y = 2;
                        }
                        frameIndex[0]++;
                        if (frameIndex[0] == 15) frameIndex[0] = 0;
                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    })
            );
            this.hashMapAnimation.put(monstre, squeletteMarche);
            squeletteMarche.setCycleCount(Animation.INDEFINITE);
            squeletteMarche.play();
        }
        else if (monstre instanceof Nargacuga) {
            int[] frameIndex = {0};
            Timeline nargacugaMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;
                        iv.setViewport(new Rectangle2D(x * 100, y * 100, 100, 100));
                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) frameIndex[0] = 0;
                    })
            );
            this.hashMapAnimation.put(monstre, nargacugaMarche);
            nargacugaMarche.setCycleCount(Animation.INDEFINITE);
            nargacugaMarche.play();
        }
        else if (monstre instanceof Dino) {
            int[] frameIndex = {0};
            int largD = (int)(Dino.getWidth() / 2);
            int hautD = (int)(Dino.getHeight() / 2);
            Timeline DinoMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;
                        iv.setViewport(new Rectangle2D(x * largD, y * hautD, largD, hautD));
                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) frameIndex[0] = 0;
                    })
            );
            this.hashMapAnimation.put(monstre, DinoMarche);
            DinoMarche.setCycleCount(Animation.INDEFINITE);
            DinoMarche.play();
        }
        else if (monstre instanceof Armure) {
            int[] frameIndex = {0};
            int largA = (int)(Armure.getWidth() / 2);
            int hautA = (int)(Armure.getHeight() / 3);
            Timeline ArmureMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;
                        iv.setViewport(new Rectangle2D(x * largA, y * hautA, largA, hautA));
                        frameIndex[0]++;
                        if (frameIndex[0] >= 4) frameIndex[0] = 0;
                    })
            );
            this.hashMapAnimation.put(monstre, ArmureMarche);
            ArmureMarche.setCycleCount(Animation.INDEFINITE);
            ArmureMarche.play();
        }
        else if (monstre instanceof Kyryn) {
            int[] frameIndex = {0};
            int largK = (int)(Kyryn.getWidth() / 2);
            int hautK = (int)(Kyryn.getHeight() / 3);

            Timeline kyrynMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;
                        iv.setViewport(new Rectangle2D(x * largK, y * hautK, largK, hautK));
                        frameIndex[0]++;
                        if (frameIndex[0] >= 6) frameIndex[0] = 0;
                    })
            );
            this.hashMapAnimation.put(monstre, kyrynMarche);
            kyrynMarche.setCycleCount(Animation.INDEFINITE);
            kyrynMarche.play();
        }
    }

    public void animationAttaque(Entite monstre) {
        stopAnimation((Monstre) monstre);
        ImageView iv = this.hashMap.get(monstre);
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
                    iv.setViewport(new Rectangle2D(x * 240, y * 240, 240, 240));
                })
        );
        this.hashMapAnimation.put(monstre, squeletteMarche);
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();
    }

    public void animationMort(Entite monstre) {
        ImageView iv = this.hashMap.get(monstre);

        if (this.hashMapBarres.containsKey(monstre)) {
            Rectangle[] barres = this.hashMapBarres.get(monstre);
            this.pane.getChildren().removeAll(barres[0], barres[1]);
            this.hashMapBarres.remove(monstre);
        }

        if (this.hashMapAnimation.containsKey(monstre)) {
            Timeline timeline = this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        if (monstre instanceof Nargacuga) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(fadeEvent -> {
                this.hashMap.remove(monstre);
                this.retirer(monstre);
            });
            fade.play();
            return;
        }

        int[] frameIndex = {27};

        Timeline squeletteMort = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x = frameIndex[0] % 6;
                    int y = frameIndex[0] / 6;
                    iv.setViewport(new Rectangle2D(x * 240, y * 240, 240, 240));
                    frameIndex[0]++;
                })
        );
        squeletteMort.setCycleCount(9);

        squeletteMort.setOnFinished(e -> {
            FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(fadeEvent -> {
                this.hashMap.remove(monstre);
                this.retirer(monstre);
            });
            fade.play();
        });
        squeletteMort.play();
    }
}