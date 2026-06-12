package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Projectile;

import java.util.HashMap;

public class ProjectilesVue {

    private HashMap hashMap= new HashMap<Projectile,ImageView>();
    private Pane pane;
    private Projectile projectile;
    Image note = new Image(Main.class.getResourceAsStream("images/note.png"));

    public ProjectilesVue(Pane pane) {
        this.pane = pane;

    }

    public void ajouterSprite(Projectile projectile){
        ImageView imageView=new ImageView();
        if (projectile.getLanceur().equals("tourmusique")){
            imageView=new ImageView(note);
        }
        imageView.translateXProperty().bind(projectile.xProperty());
        imageView.translateYProperty().bind(projectile.yProperty()
        );

        this.hashMap.put(projectile , imageView);
        this.pane.getChildren().add(imageView);
    }


    public void retirerSprite(Projectile projectile){
        ImageView  iv= (ImageView) hashMap.get(projectile);
        iv.setImage(null);
        this.pane.getChildren().remove(iv);
        this.hashMap.remove(projectile, iv);
    }

}
