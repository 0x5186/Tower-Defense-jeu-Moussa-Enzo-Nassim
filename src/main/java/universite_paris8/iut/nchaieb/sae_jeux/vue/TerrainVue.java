package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class TerrainVue {
    Image herbeBasse = new Image(Main.class.getResourceAsStream("images/herbe-basse.png"));
    Image herbeHaute = new Image(Main.class.getResourceAsStream("images/herbe-haute.png"));
    Image terrainChemin = new Image(Main.class.getResourceAsStream("images/terrain.png"));

    private TilePane tilePane;
    private Terrain terrain;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void dessine(int map) {
        // Sécurité pour le bug du menu noir
        if (map == 1) { return; }

        terrain.terrainPlainesCode();
        this.tilePane.getChildren().clear();

        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                ImageView imageView = new ImageView();

                // Retour à la taille normale
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);

                switch (this.terrain.codeTuile(l, col)) {
                    case 0: imageView.setImage(herbeBasse); break;
                    case 1: imageView.setImage(herbeHaute); break;
                    case 2: imageView.setImage(terrainChemin); break;
                }

                if (imageView.getImage() != null) {
                    this.tilePane.getChildren().add(imageView);
                }
            }
        }
    }
}