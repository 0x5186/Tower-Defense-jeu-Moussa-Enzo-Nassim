package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.w3c.dom.css.RGBColor;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourOeil;

public class SourisVue {
    private Circle cercle;

    private StackPane pane;
    Image tourOeilCurseur = new Image(
            Main.class.getResourceAsStream("images/tourOeilCurseur.png"),
            80, 80, true, true
    );
    Image tourTeslaCurseur = new Image(
            Main.class.getResourceAsStream("images/tourTeslaCurseur.png"),
            80, 80, true, true
    );


    public SourisVue(StackPane pane) {
        this.pane = pane;
        this.cercle= null;
    }




    public void ajouterImageSouris(String tour){// change l'image de la souris pour la tour qu'on veut placer
        DoubleProperty sourisX=new SimpleDoubleProperty();
        DoubleProperty sourisY=new SimpleDoubleProperty();
        double rayon=0;
        System.out.println("souris changée");
        if(tour.equals("tourOeil")) {
            pane.setCursor(new ImageCursor(tourOeilCurseur));
            rayon=150;
        }
        else if(tour.equals("tourTesla")) {
            pane.setCursor(new ImageCursor(tourTeslaCurseur));
            rayon=100;
        }

        this.cercle= new Circle(rayon);
//        this.cercle.setFill(Color.TRANSPARENT);
        this.cercle.setFill(Color.rgb(0,0,0,0.4));
        this.cercle.setStroke(Color.BLACK);


        pane.setOnMouseMoved(event -> {

            cercle.setTranslateX(event.getX()-this.cercle.getRadius());
            cercle.setTranslateY(event.getY()-this.cercle.getRadius());
//            sourisX.set(event.getX());
//            sourisY.set(event.getY());

//            System.out.println(this.cercle.getParent());

                }
        );
//        this.cercle.centerXProperty().bind(sourisX);
//        this.cercle.centerYProperty().bind(sourisY);
        this.pane.getChildren().add(cercle);
    }



    public void retirerImageSouris() {// change l'image de la souris pour la tour qu'on veut placer

        pane.setCursor(Cursor.DEFAULT);
        this.cercle.setVisible(false);

        this.pane.getChildren().remove(cercle);

    }
}
