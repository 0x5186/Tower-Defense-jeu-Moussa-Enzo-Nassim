package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Decor;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Fleur;

import java.util.HashMap;

public class DecorVue {

    private Pane pane;
    private Environnement environnement;
    private HashMap<Decor, ImageView> hashMap;
    private Image imageFleurNormale = new Image(Main.class.getResourceAsStream("images/fleurNormale.png"));
    private Image imageFleurFletrie = new Image(Main.class.getResourceAsStream("images/fleurPerie.png"));

    public DecorVue(Pane pane, Environnement environnement){
        this.pane = pane;
        this.environnement = environnement;
        this.hashMap = new HashMap<>();
        this.initialiserAffichage();
    }

    public void initialiserAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();

        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);

            if (decor instanceof Fleur){
                ImageView imageView = new ImageView(this.imageFleurNormale);
                imageView.setLayoutX(decor.getX());
                imageView.setLayoutY(decor.getY());
                this.hashMap.put(decor, imageView);
                this.pane.getChildren().add(imageView);
            }
        }
    }

    public void mettreAJourAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();

        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);
            ImageView imageView = this.hashMap.get(decor);

            if (imageView != null){
                if (decor.getEtat().equals("perir")){
                    imageView.setImage(this.imageFleurFletrie);
                } else {
                    imageView.setImage(this.imageFleurNormale);
                }
            }
        }
    }

}
