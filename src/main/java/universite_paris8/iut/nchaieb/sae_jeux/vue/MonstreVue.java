package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.*;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
//import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;

import java.util.HashMap;

public class MonstreVue {
    private Pane pane;
    private HashMap hashMap= new HashMap<Monstre,ImageView>();
    private HashMap hashMapAnimation= new HashMap<Monstre, Timeline>();
    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette(3).png"));
    Image  sorcier = new Image(Main.class.getResourceAsStream("images/sorcier.png"));
    Image tourOeilCurseur = new Image(Main.class.getResourceAsStream("images/tourOeilCurseur.png"));



    public MonstreVue(Pane pane) {
        this.pane= pane;
    }

    public void ajouterSprite(Monstre monstre){


        Circle circle= new Circle(0,0,15);
        circle.setFill(Color.RED);
        pane.getChildren().add(circle);
        ImageView  iv= new ImageView();
        if (monstre instanceof Squelette) {
            iv = new ImageView(squelette);
        }
        if (monstre instanceof Sorcier) {
            iv = new ImageView(sorcier);
        }
        iv.translateXProperty().bind(monstre.posXProperty());
        iv.translateYProperty().bind(monstre.posYProperty()
        );


        this.hashMap.put(monstre, iv);

//        if (monstre instanceof Squelette){
//            iv.setScaleX(0.40);
//            iv.setScaleY(0.40);
//        }
        iv.setViewport(new Rectangle2D(0,0,50,50));

        this.pane.getChildren().add(iv);
    }

    public void retirer(Entite entite){
        ImageView  iv= (ImageView) hashMap.get(entite);
        iv.setImage(null);
        this.pane.getChildren().remove(iv);
        this.hashMap.remove(entite, iv);
    }

    public void stopAnimation(Entite entite){
        if(this.hashMapAnimation.containsKey(entite)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(entite);
            timeline.stop();
            this.hashMapAnimation.remove(entite);
        }
    }




    public void animationMarche(Entite monstre) {


        ImageView iv = (ImageView) this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;
        System.out.println(iv.getX());
        System.out.println(iv.getY());
        if (monstre instanceof Squelette){

            int[] frameIndex = {13};
            Timeline squeletteMarche = new Timeline(

                    new KeyFrame(Duration.millis(90), e -> {

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
            squeletteMarche.setCycleCount(15);
            squeletteMarche.play();


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
                    iv.setViewport(new Rectangle2D(x* largeurCase, y * hauteurCase, largeurCase, hauteurCase));

                })
        );
        this.hashMapAnimation.put(monstre, squeletteMarche);
        squeletteMarche.setCycleCount(10);
        squeletteMarche.play();






    }

    public void animationMort(Entite monstre) {


        ImageView iv=(ImageView) this.hashMap.get(monstre);
        int largeurCase = 240;
        int hauteurCase = 240;
        int[] frameIndex = {27};

        if(this.hashMapAnimation.containsKey(monstre)){
            Timeline timeline= (Timeline) this.hashMapAnimation.get(monstre);
            timeline.stop();
            this.hashMapAnimation.remove(monstre);
        }

        Timeline squeletteMort = new Timeline(
                new KeyFrame(Duration.millis(120), e -> {
                    int x = frameIndex[0] % 6;
                    int y = frameIndex[0] / 6;

                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                    frameIndex[0]++;
                })
        );
        squeletteMort.setCycleCount(7);


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
        System.out.println("animort fin");





    }

}
