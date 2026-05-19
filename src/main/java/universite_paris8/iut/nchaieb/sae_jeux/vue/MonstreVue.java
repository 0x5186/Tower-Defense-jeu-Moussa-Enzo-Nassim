package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Squelette;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

import java.util.ArrayList;


public class MonstreVue {
    private StackPane stackPane;
    private ArrayList<MonstreDeBase> listeMonstre= new ArrayList<>();;
    private static int[] positionDeSpawn={-700,-100};
    private Environnement environnement;
    int temps;


    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));


    public MonstreVue(StackPane stackPane, Environnement environnement) {

        this.stackPane= stackPane;
        this.environnement= environnement;
    }


//    public void lancerAnimations (){
//        for(int i=0; i< listeMonstre.size(); i++){
//            if(listeMonstre.get(i).getActionActuel()==0){
//                animationMarche(listeMonstre.get(i));
//
//            }
//        }
//    }


    //    methode 1 pas fluide:
//    public void animationMonstre(MonstreDeBase monstre)  {
//
//        int largeurCase = 240;
//        int hauteurCase = 240;
//        int totalFrames = 17;
//        int compt=0;
//        temps=0;
//
//
//        int[] frameIndex = {12};
//
//        ImageView iv = new ImageView(verifMonstre(monstre.getType()));
//        int positionMonstre[]=positionDeSpawn;
//
//        this.stackPane.getChildren().add(iv);
//        iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));
//
//        iv.setTranslateX(positionDeSpawn[0]); // position X en pixels
//        iv.setTranslateY(positionDeSpawn[1]);
//        iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));
//        Timeline squeletteMarche = new Timeline(
//                new KeyFrame(Duration.millis(120), e -> {
//                    int x, y;
//                    if (frameIndex[0] < 25) {
//
//                        x = frameIndex[0] % 5;
//                        y = frameIndex[0] / 5;
//                    } else {
//
//                        x = frameIndex[0] - 25;
//                        y = 5;
//                    }
//
//
//
//
//
//                    iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
//                    frameIndex[0] = (frameIndex[0] + 1) ;
//                    if (frameIndex[0]==28){
//                        frameIndex[0]=12;
//                    }
//
//                    if (temps%2==0){
//                        iv.setTranslateX(positionDeSpawn[0] + monstre.getPosX()*3);
//                    }
//
//                    temps++;
//
//
//                })
//        );
//
//        squeletteMarche.setCycleCount(Timeline.INDEFINITE);
//        squeletteMarche.play();
//
//
//
//        int actionMonstre = monstre.getActionActuel();
//
//
//    }


    public void animationMarche(MonstreDeBase monstre) {





        if(monstre instanceof Squelette){
            ImageView iv = new ImageView(squelette);
            this.stackPane.getChildren().add(iv);
//            iv.setTranslateX(positionDeSpawn[0]);
//            iv.setTranslateY(positionDeSpawn[1]);
            int largeurCase = 240;
            int hauteurCase = 240;
            int[] frameIndex = {12};
            iv.setViewport(new Rectangle2D(2 * largeurCase, 3 * hauteurCase, largeurCase, hauteurCase));


            Timeline squeletteMarche = new Timeline(
                    new KeyFrame(Duration.millis(90), e -> {
                        int x, y;
                        if (frameIndex[0] < 25) {
                            x = frameIndex[0] % 5;
                            y = frameIndex[0] / 5;
                        } else {
                            x = frameIndex[0] - 25;
                            y = 5;
                        }
                        iv.setViewport(new Rectangle2D(x * largeurCase, y * hauteurCase, largeurCase, hauteurCase));
                        frameIndex[0]++;
                        if (frameIndex[0] == 27) frameIndex[0] = 12;
                        iv.setTranslateX(monstre.getPosX());
                        iv.setTranslateY(monstre.getPosY());
                    })
            );
            squeletteMarche.setCycleCount(Timeline.INDEFINITE);
            squeletteMarche.play();
        }

    }

    public Image verifMonstre(String typeMonstre) {
        if (typeMonstre.equals("squelette")) {
            return squelette;
        }

        return null;
    }

    public void unTour() {
        for(int i=0; i<listeMonstre.size(); i++){
            avancer(listeMonstre.get(i));
        }
    }
    public void avancer(MonstreDeBase monstre){
        monstre.setPosX(monstre.getPosX()+5);
        System.out.println(monstre.getPosX());
    }
    public void ajouterMonstre(MonstreDeBase monstre){
        listeMonstre.add(monstre);
//        lancerAnimations();
    }
}
