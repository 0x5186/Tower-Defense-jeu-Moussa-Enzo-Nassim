package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class TerrainVue {
    Image herbeBasse = new Image(Main.class.getResourceAsStream("images/herbe-basse.png"));
    Image herbes = new Image(Main.class.getResourceAsStream("images/herbes.png"));
    Image herbeHaute = new Image(Main.class.getResourceAsStream("images/herbe-haute.png"));
    Image terrainChemin = new Image(Main.class.getResourceAsStream("images/terrain.png"));
    Image portail = new Image(Main.class.getResourceAsStream("images/portail.png"));

    private TilePane tilePane;
    private Terrain terrain;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void dessine(int map, Pane pane) {
        ImageView portailSpawn1 = new ImageView(portail);

        portailSpawn1.setLayoutX(0);
        portailSpawn1.setTranslateY(100);
        portailSpawn1.setViewport(new Rectangle2D(125,0,125,80));
        pane.getChildren().add(portailSpawn1);
        // Sécurité pour le bug du menu noir
        if (map == 1) { return; }

        terrain.terrainPlainesCode();
        this.tilePane.getChildren().clear();

        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                ImageView imageView = new ImageView();

                // Retour à la taille normale
                imageView.setFitWidth(24);
                imageView.setFitHeight(24);
                switch (this.terrain.codeTuile(l, col)) {
                    case 0: imageView.setImage(herbeBasse); break;
                    case 1: imageView.setImage(herbeHaute); break;
                    case 2: imageView.setImage(terrainChemin); break;

                }
//                switch (this.terrain.codeTuile(l, col)) {
//                    case 7: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(0,1*32,32,32)); break;
//                    case 1: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(0,32,32,32)); break;
//                    case 1: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(2*32,32,32,32)); break; //complet herbe
//                    case 2: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(32*3,32,32,32)); break;
//                    case 3: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(0,0,32,32)); break; //en haut a gauche
//                    case 4: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(2*32,0,32,32)); break; //en haut a droite
//                    case 5: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(0,32,32,32)); break; //en bas a gauche
//                    case 6: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(3*32,3*32,32,32)); break; //en bas a droite
//                    case 0: imageView.setImage(herbes); imageView.setViewport(new Rectangle2D(2*32,32,32,32)); break; //complet herbe
//                }

                if (imageView.getImage() != null) {
                    this.tilePane.getChildren().add(imageView);
                }
            }
        }

    }
}