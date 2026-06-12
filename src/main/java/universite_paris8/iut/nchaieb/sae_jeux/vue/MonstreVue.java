package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.*;

import java.util.HashMap;

public class MonstreVue {
    private Pane pane;
    private HashMap hashMap = new HashMap<Monstre, ImageView>();
    private HashMap hashMapAnimation = new HashMap<Monstre, Timeline>();
    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette(3).png"));
    Image sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));
    Image nargacuga = new Image(Main.class.getResourceAsStream("images/nargacuga.png"));
    Image Dino = new Image(Main.class.getResourceAsStream("images/dino.png"));
    Image Armure = new Image(Main.class.getResourceAsStream("images/armure.png"));

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
        if (monstre instanceof Sorcier) {
            iv = new ImageView(sorcier);
            iv.setViewport(new Rectangle2D(0, 0, 72, 72));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(32));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(32));
        }
        if (monstre instanceof Nargacuga) {
            iv = new ImageView(nargacuga);
            iv.setViewport(new Rectangle2D(0, 0, 100, 100));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        if(monstre instanceof Dino) {
            iv = new ImageView(Dino);
            iv.setViewport(new Rectangle2D(0,0,80,80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        if(monstre instanceof Armure) {
            iv = new ImageView(Armure);
            iv.setViewport(new Rectangle2D(0,0,80,80));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }

        this.hashMap.put(monstre, iv);
        this.pane.getChildren().add(iv);
    }

    public void retirer(Entite entite) {
        ImageView iv = (ImageView) hashMap.get(entite);
        if (iv != null) {
            iv.setImage(null);
            this.pane.getChildren().remove(iv);
            this.hashMap.remove(entite);
        }
    }

    public void stopAnimation(Monstre monstre) {
        if (this.hashMapAnimation.containsKey(monstre)) {
            Timeline timeline = (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }
    }

    public void animationMarche(Entite monstre) {
        ImageView iv = (ImageView) this.hashMap.get(monstre);
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

        if (monstre instanceof Nargacuga) {
            int[] frameIndex = {0};
            int largeurCaseNargacuga = 100;
            int hauteurCaseNargacuga = 100;

            Timeline nargacugaMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;

                        iv.setViewport(new Rectangle2D(x * largeurCaseNargacuga, y * hauteurCaseNargacuga, largeurCaseNargacuga, hauteurCaseNargacuga));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) {
                            frameIndex[0] = 0;
                        }
                    })
            );
            this.hashMapAnimation.put(monstre, nargacugaMarche);
            nargacugaMarche.setCycleCount(Animation.INDEFINITE);
            nargacugaMarche.play();
        }

        if (monstre instanceof Dino) {
            int[] frameIndex = {0};
            int largeurCaseDino = (int)(Dino.getWidth() / 2);
            int hauteurCaseDino = (int)(Dino.getHeight()) / 2;

            Timeline DinoMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;

                        iv.setViewport(new Rectangle2D(x * largeurCaseDino, y * hauteurCaseDino, largeurCaseDino, hauteurCaseDino));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 3) {
                            frameIndex[0] = 0;
                        }
                    })
            );
            this.hashMapAnimation.put(monstre, DinoMarche);
            DinoMarche.setCycleCount(Animation.INDEFINITE);
            DinoMarche.play();
        }

        if(monstre instanceof Armure) {
            int[] frameIndex = {0};
            int largeurCaseArmure = (int)(Armure.getWidth() / 2);
            int hauteurCaseArmure = (int)(Armure.getHeight()) / 3;

            Timeline ArmureMarche = new Timeline(
                    new KeyFrame(Duration.millis(150), event -> {
                        int x = frameIndex[0] % 2;
                        int y = frameIndex[0] / 2;

                        iv.setViewport(new Rectangle2D(x * largeurCaseArmure, y * hauteurCaseArmure, largeurCaseArmure, hauteurCaseArmure));

                        frameIndex[0]++;
                        if (frameIndex[0] >= 4) {
                            frameIndex[0] = 0;
                        }
                    })
            );
            this.hashMapAnimation.put(monstre, ArmureMarche);
            ArmureMarche.setCycleCount(Animation.INDEFINITE);
            ArmureMarche.play();
        }
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
                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                })
        );
        this.hashMapAnimation.put(monstre, squeletteMarche);
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();
    }

    public void animationMort(Entite monstre) {
        ImageView iv = (ImageView) this.hashMap.get(monstre);

        if (this.hashMapAnimation.containsKey(monstre)) {
            Timeline timeline = (Timeline) this.hashMapAnimation.get(monstre);
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

        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

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
                this.hashMap.remove(monstre);
                this.retirer(monstre);
            });
            fade.play();
        });
        squeletteMort.play();
    }
}