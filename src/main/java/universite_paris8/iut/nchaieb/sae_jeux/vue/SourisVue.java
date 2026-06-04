package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourOeil;

public class SourisVue {

    private StackPane pane;
    Image tourOeilCurseur = new Image(
            Main.class.getResourceAsStream("images/tourOeilCurseur.png"),
            80, 80, true, true
    );


    public SourisVue(StackPane pane) {
        this.pane = pane;
    }



    public void ajouterImageSouris(String tour){// change l'image de la souris pour la tour qu'on veut placer
        System.out.println("souris changée");
        if(tour.equals("tourOeil")) {
            pane.setCursor(new ImageCursor(tourOeilCurseur));
        }

    }

    public void retirerImageSouris() {// change l'image de la souris pour la tour qu'on veut placer

        pane.setCursor(Cursor.DEFAULT);

    }
}
