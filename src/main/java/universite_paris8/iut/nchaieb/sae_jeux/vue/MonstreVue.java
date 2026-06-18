package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.beans.binding.Bindings;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.*;

import java.util.HashMap;

public class MonstreVue {
    private Pane pane;
    private HashMap<Monstre, ImageView> hashMap = new HashMap<>();
    private HashMap<Monstre, Timeline> hashMapAnimation = new HashMap<>();
    private HashMap<Monstre, Rectangle[]> hashMapBarres = new HashMap<>();

    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette(3).png"));
    Image sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));
    Image nargacuga = new Image(Main.class.getResourceAsStream("images/nargacuga.png"));
    Image Dino = new Image(Main.class.getResourceAsStream("images/dino.png"));
    Image Armure = new Image(Main.class.getResourceAsStream("images/armure.png"));
    Image Kyryn = new Image(Main.class.getResourceAsStream("images/kyryn.png"));
    Image imageBossMarche = new Image(Main.class.getResourceAsStream("images/bossmarche.png"));
    Image imageBossAttaque = new Image(Main.class.getResourceAsStream("images/bossattaque.png"));

    public MonstreVue(Pane pane) {
        this.pane = pane;
    }

    public void ajouterSprite(Monstre monstre) {
        ImageView iv = new ImageView();

        if (monstre instanceof Squelette) {
            iv = new ImageView(squelette);
            iv.setViewport(new Rectangle2D(0, 0, 50, 50));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(17));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(17));
        }
        else if (monstre instanceof Sorcier) {
            iv = new ImageView(sorcier);
            iv.setViewport(new Rectangle2D(0, 0, 72, 72));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(32));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(32));
        }
        else if (monstre instanceof Nargacuga) {
            iv = new ImageView(nargacuga);
            iv.setViewport(new Rectangle2D(0, 0, 100, 100));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        else if (monstre instanceof Dino) {
            iv = new ImageView(Dino);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        else if (monstre instanceof Armure) {
            iv = new ImageView(Armure);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        else if (monstre instanceof Kyryn) {
            iv = new ImageView(Kyryn);
            iv.setViewport(new Rectangle2D(0, 0, 80, 80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        else if (monstre instanceof Boss) {
            iv = new ImageView(imageBossMarche);

            int tailleVisuelle = 120;
            iv.setFitWidth(tailleVisuelle);
            iv.setFitHeight(tailleVisuelle);


            int largeurCaseBoss = (int) (imageBossMarche.getWidth() / 2);
            int hauteurCaseBoss = (int) (imageBossMarche.getHeight() / 3);
            iv.setViewport(new Rectangle2D(0, 0, largeurCaseBoss, hauteurCaseBoss));

            int offset = (tailleVisuelle - 32) / 2;

            iv.translateXProperty().bind(monstre.posXProperty().subtract(tailleVisuelle/2.0));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(tailleVisuelle - 20));

            monstre.actionActuelleProperty().addListener((obs, oldAction, newAction) -> {
                animationMarche(monstre);
            });
        }

        this.hashMap.put(monstre, iv);
        this.pane.getChildren().add(iv);

        if (monstre instanceof Boss) {
            animationMarche(monstre);
        }

        double largeurBarre = 36;
        double hauteurBarre = 6;

        double offsetYBarre = 25;

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

        vieBarre.widthProperty().bind(
                Bindings.createDoubleBinding(
                        () -> (monstre.getPV() / (double) monstre.getPvMax()) * largeurBarre,
                        monstre.pvProperty()
                )
        );

        monstre.pvProperty().addListener((obs, oldVal, newVal) -> {
            double ratio = newVal.doubleValue() / monstre.getPvMax();
            if (ratio > 0.51)      vieBarre.setFill(Color.LIMEGREEN);
            else if (ratio > 0.21) vieBarre.setFill(Color.YELLOW);
            else                   vieBarre.setFill(Color.RED);
        });

        this.hashMapBarres.put(monstre, new Rectangle[]{fondBarre, vieBarre});
        this.pane.getChildren().addAll(fondBarre, vieBarre);
    }

    public void retirer(Monstre monstre){
        ImageView iv = this.hashMap.get(monstre);
        if (iv != null) {
            iv.setImage(null);
            this.pane.getChildren().remove(iv);
            this.hashMap.remove(monstre);
        }

        Rectangle[] barres = this.hashMapBarres.get(monstre);
        if (barres != null) {
            this.pane.getChildren().removeAll(barres[0], barres[1]);
            this.hashMapBarres.remove(monstre);
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
        ImageView iv = this.hashMap.get((Monstre)monstre);
        if (iv == null) return;

        stopAnimation((Monstre)monstre);

        int largeurCase;
        int hauteurCase;

        if (monstre instanceof Squelette){
            largeurCase = 50;
            hauteurCase = 50;
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
                        iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, squeletteMarche);
            squeletteMarche.setCycleCount(Animation.INDEFINITE);
            squeletteMarche.play();
        }
        else if (monstre instanceof Sorcier){
            largeurCase=72;
            hauteurCase=72;
            int[] frameIndex = {0};
            Timeline sorcierMarche = new Timeline(
                    new KeyFrame(Duration.millis(100), e -> {
                        int x;
                        x = frameIndex[0] % 14;
                        frameIndex[0]++;
                        if (frameIndex[0] == 15) frameIndex[0] = 0;
                        iv.setViewport(new Rectangle2D(x* largeurCase, 0, largeurCase, hauteurCase));
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, sorcierMarche);
            sorcierMarche.setCycleCount(Animation.INDEFINITE);
            sorcierMarche.play();
        }
        else if (monstre instanceof Nargacuga) {
            int[] frameIndex = {0};
            int largeurCaseNargacuga = 100;
            int hauteurCaseNargacuga = 100;

            Timeline nargacugaMarche = new Timeline(
                    new KeyFrame(Duration.millis(100), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;

                        iv.setViewport(new Rectangle2D(x * largeurCaseNargacuga, y * hauteurCaseNargacuga, largeurCaseNargacuga, hauteurCaseNargacuga));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) {
                            frameIndex[0] = 0;
                        }
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, nargacugaMarche);
            nargacugaMarche.setCycleCount(Animation.INDEFINITE);
            nargacugaMarche.play();
        }
        else if (monstre instanceof Dino) {
            int[] frameIndex = {0};
            int largeurCaseDino = 80;
            int hauteurCaseDino = 80;

            Timeline dinoMarche = new Timeline(
                    new KeyFrame(Duration.millis(100), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;

                        iv.setViewport(new Rectangle2D(x * largeurCaseDino, y * hauteurCaseDino, largeurCaseDino, hauteurCaseDino));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) {
                            frameIndex[0] = 0;
                        }
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, dinoMarche);
            dinoMarche.setCycleCount(Animation.INDEFINITE);
            dinoMarche.play();
        }
        else if (monstre instanceof Armure) {
            int largeurCaseArmure = 80;
            int hauteurCaseArmure = 80;
            int[] frameIndex = {0};

            Timeline armureMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), e -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;
                        iv.setViewport(new Rectangle2D(x * largeurCaseArmure, y * hauteurCaseArmure, largeurCaseArmure, hauteurCaseArmure));
                        frameIndex[0] = (frameIndex[0] + 1) % 4;
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, armureMarche);
            armureMarche.setCycleCount(Animation.INDEFINITE);
            armureMarche.play();
        }
        else if (monstre instanceof Kyryn) {
            int largeurCaseKyryn = 80;
            int hauteurCaseKyryn = 80;
            int[] frameIndex = {0};

            Timeline kyrynMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), e -> {
                        int x = frameIndex[0] % 3;
                        int y = frameIndex[0] / 3;
                        iv.setViewport(new Rectangle2D(x * largeurCaseKyryn, y * hauteurCaseKyryn, largeurCaseKyryn, hauteurCaseKyryn));
                        frameIndex[0] = (frameIndex[0] + 1) % 6;
                    })
            );
            this.hashMapAnimation.put((Monstre)monstre, kyrynMarche);
            kyrynMarche.setCycleCount(Animation.INDEFINITE);
            kyrynMarche.play();
        }
        else if (monstre instanceof Boss) {
            String action = monstre.getActionActuelle();

            if (action == null || action.equals("marche") || action.equals("fixe")) {
                iv.setImage(imageBossMarche);

                int largeurCaseBoss = (int) (imageBossMarche.getWidth() / 2);
                int hauteurCaseBoss = (int) (imageBossMarche.getHeight() / 3);
                int[] frameIndex = {0};

                Timeline bossMarche = new Timeline(
                        new KeyFrame(Duration.millis(150), e -> {
                            int x = frameIndex[0] % 2;
                            int y = frameIndex[0] / 2;
                            iv.setViewport(new Rectangle2D(x * largeurCaseBoss, y * hauteurCaseBoss, largeurCaseBoss, hauteurCaseBoss));
                            frameIndex[0] = (frameIndex[0] + 1) % 6;
                        })
                );
                this.hashMapAnimation.put((Monstre)monstre, bossMarche);
                bossMarche.setCycleCount(Animation.INDEFINITE);
                bossMarche.play();
            }

            else if (action.equals("attaque")) {
                iv.setImage(imageBossAttaque);

                int largeurCaseBoss = (int) (imageBossAttaque.getWidth() / 2);
                int hauteurCaseBoss = (int) (imageBossAttaque.getHeight() / 3);
                int[] frameIndex = {0};

                Timeline bossAttaque = new Timeline(
                        new KeyFrame(Duration.millis(120), e -> {
                            int x = frameIndex[0] % 2;
                            int y = frameIndex[0] / 2;
                            iv.setViewport(new Rectangle2D(x * largeurCaseBoss, y * hauteurCaseBoss, largeurCaseBoss, hauteurCaseBoss));
                            frameIndex[0] = (frameIndex[0] + 1) % 6;
                        })
                );
                this.hashMapAnimation.put((Monstre)monstre, bossAttaque);
                bossAttaque.setCycleCount(Animation.INDEFINITE);
                bossAttaque.play();
            }
        }
    }

    public void animationMort(Monstre monstre) {
        ImageView iv = this.hashMap.get(monstre);
        if (iv == null) return;

        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

        if(this.hashMapAnimation.containsKey(monstre)){
            Timeline timeline = this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        if (monstre instanceof Nargacuga) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(fadeEvent -> {
                this.retirer(monstre);
            });
            fade.play();
        }
        else if (monstre instanceof Squelette){
            Timeline squeletteMort = new Timeline(
                    new KeyFrame(Duration.millis(120), e -> {
                        int x = frameIndex[0] % 6;
                        int y = frameIndex[0] / 6;

                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                        frameIndex[0]++;
                    })
            );
            squeletteMort.setCycleCount(9);

            squeletteMort.setOnFinished(e -> {
                FadeTransition fade = new FadeTransition(Duration.seconds(2), iv);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(fadeEvent -> {
                    this.retirer(monstre);
                });
                fade.play();
            });
            squeletteMort.play();
        }
        else if (monstre instanceof Boss) {
            FadeTransition fade = new FadeTransition(Duration.seconds(3), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(fadeEvent -> {
                this.retirer(monstre);
            });
            fade.play();
        }
        else {
            this.retirer(monstre);
        }
    }
}