package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class SourisVue {

    private Circle cercle;
    private StackPane pane;

    // ── images curseur ────────────────────────────────────────────────────────
    Image tourOeilCurseur = new Image(
            Main.class.getResourceAsStream("images/tourOeilCurseur.png"),
            80, 80, true, true
    );
    Image tourGlaceCurseur = new Image(          // ← gardé depuis v1
            Main.class.getResourceAsStream("images/tour-de-glace.png"),
            80, 80, true, true
    );
    Image tourTeslaCurseur = new Image(          // ← ajouté depuis v2
            Main.class.getResourceAsStream("images/tourTeslaCurseur.png"),
            80, 80, true, true
    );

    // ── constructeur ──────────────────────────────────────────────────────────
    public SourisVue(StackPane pane) {
        this.pane   = pane;
        this.cercle = null;
    }

    // ── méthodes ──────────────────────────────────────────────────────────────
    public void ajouterImageSouris(String tour) {
        double rayon = 0;

        if (tour.equals("tourOeil")) {
            pane.setCursor(new ImageCursor(tourOeilCurseur));
            rayon = 150;
        } else if (tour.equals("tourGlace")) {   // ← cas gardé depuis v1
            pane.setCursor(new ImageCursor(tourGlaceCurseur));
            rayon = 120;                          // à ajuster selon ton besoin
        } else if (tour.equals("tourTesla")) {   // ← cas ajouté depuis v2
            pane.setCursor(new ImageCursor(tourTeslaCurseur));
            rayon = 100;
        }

        // cercle de portée (feature de v2, appliquée aux 3 tours)
        this.cercle = new Circle(rayon);
        this.cercle.setFill(Color.rgb(0, 0, 0, 0.4));
        this.cercle.setStroke(Color.BLACK);

        pane.setOnMouseMoved(event -> {
            cercle.setTranslateX(event.getX() - cercle.getRadius());
            cercle.setTranslateY(event.getY() - cercle.getRadius());
        });

        this.pane.getChildren().add(cercle);
    }

    public void retirerImageSouris() {
        pane.setCursor(Cursor.DEFAULT);
        if (cercle != null) {
            this.pane.getChildren().remove(cercle);
            this.cercle = null;
        }
    }
}