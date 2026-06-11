package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

import java.util.HashMap;

public class MonstreVue {
    private Pane pane;
    private HashMap hashMap = new HashMap<Monstre, ImageView>();
    private HashMap hashMapAnimation = new HashMap<Monstre, Timeline>();
    Image squelette = new Image(Main.class.getResourceAsStream("images/squelette(3).png"));
    Image sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));
    Image nargacuga = new Image(Main.class.getResourceAsStream("images/nargacuga.png"));

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
            // Sprite 80x80, tuile 16x16 → décalage (80-16)/2 = 32px pour centrer
            iv.translateXProperty().bind(monstre.posXProperty().subtract(32));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(32));
        }
        if (monstre instanceof Nargacuga) {
            iv = new ImageView(nargacuga);
            iv.setViewport(new Rectangle2D(0, 0, 100, 100));
            iv.translateXProperty().bind(monstre.posXProperty().subtract(48));
            iv.translateYProperty().bind(monstre.posYProperty().subtract(48));
        }
        this.hashMap.put(monstre, iv);
        this.pane.getChildren().add(iv);
    }


    public void retirer(Monstre monstre){
        ImageView  iv= (ImageView) hashMap.get(monstre);
        iv.setImage(null);
        this.pane.getChildren().remove(iv);
        this.hashMap.remove(monstre, iv);
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
            this.hashMapAnimation.put(monstre, squeletteMarche);
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
            this.hashMapAnimation.put(monstre, sorcierMarche);
            sorcierMarche.setCycleCount(Animation.INDEFINITE);
            sorcierMarche.play();


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


    }

    public void animationMort(Entite monstre) {
        ImageView iv = (ImageView) this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

        if(this.hashMapAnimation.containsKey(monstre)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        // CORRECTION 2 : Le Nargacuga DOIT être traité avant le squelette, avec un return à la fin !
        if (monstre instanceof Nargacuga) {
            FadeTransition fade = new FadeTransition(Duration.seconds(1), iv);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(fadeEvent -> {
                this.hashMap.remove(monstre);
                this.retirer(monstre);
            });
            fade.play();
            return; // INDISPENSABLE pour empêcher l'exécution de l'animation du Squelette juste en dessous
        }

        // Si le code arrive ici, c'est que ce n'est PAS un Nargacuga
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
                this.hashMap.remove(iv);
                this.retirer(monstre);
            });
            fade.play();
        });
        squeletteMort.play();
    }
}