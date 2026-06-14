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
    Image herbeHaute = new Image(Main.class.getResourceAsStream("images/herbe-haute.png"));
    Image herbeDefaut = new Image(Main.class.getResourceAsStream("images/herbe.png"));
    Image terrainChemin = new Image(Main.class.getResourceAsStream("images/terrain.png"));

    Image herbes = new Image(Main.class.getResourceAsStream("images/herbes.png"));

    private TilePane tilePane;
    private Terrain terrain;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;
    }

    public void dessine(int map, Pane pane) {


        if (map == 1) { return; }
        this.tilePane.getChildren().clear();

        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                ImageView imageView = new ImageView();


                switch (this.terrain.codeTuile(l, col)) {
                    case 0:
                        imageView.setImage(herbes);
                        imageView.setViewport(new Rectangle2D(32*2,32*2,32,32));
                        break;
                    case 1: imageView.setImage(herbes);
                        imageView.setViewport(new Rectangle2D(32,0,32,32));
                        break;
                    case 2: imageView.setImage(herbes);
                        imageView.setViewport(new Rectangle2D(32,32,32,32));
                        break;
                    case 3: imageView.setImage(herbes);//haut gauche
                        imageView.setViewport(new Rectangle2D(0,0,32,32));
                        break;
                    case 4: imageView.setImage(herbes);//haut droite
                        imageView.setViewport(new Rectangle2D(32*2,0,32,32));
                        break;
                    case 5: imageView.setImage(herbes);//bas gauche
                        imageView.setViewport(new Rectangle2D(32*2,32,32,32));
                        break;
                    case 6: imageView.setImage(herbes);//bas droite
                        imageView.setViewport(new Rectangle2D(0,32,32,32));
                        break;
                    case 7: imageView.setImage(herbes);// droite
                        imageView.setViewport(new Rectangle2D(32,32*2,32,32));
                        break;
                    case 8: imageView.setImage(herbes);//gauche
                        imageView.setViewport(new Rectangle2D(0,32*2,32,32));
                        break;
                    case 9: imageView.setImage(herbes);//pleinTerre
                        imageView.setViewport(new Rectangle2D(0,32*3,32,32));
                        break;
                }

                if (imageView.getImage() != null) {
                    this.tilePane.getChildren().add(imageView);
                }
            }
        }
    }
}