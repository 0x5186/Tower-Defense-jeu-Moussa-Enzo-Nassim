package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Deco.Decor;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Deco.Fleur;

import java.util.HashMap;
import java.util.Map;

public class DecorVue {

    private Pane pane;
    private Environnement environnement;
    private Map<Decor, ImageView> imagesParDecor;

    private Image imageFleurNormale = new Image(Main.class.getResourceAsStream("images/fleurNormale.png"));
    private Image imageFleurPerir = new Image(Main.class.getResourceAsStream("images/fleurPerie.png"));
    private Image imageFleurEnPhase = new Image(Main.class.getResourceAsStream("images/fleurEnPhase.png"));
    private Image imagePillier = new Image(Main.class.getResourceAsStream("images/decorPillier.png"));
    private Image imageRocher = new Image(Main.class.getResourceAsStream("images/decorRocher.png"));
    private Image imageArbre = new Image(Main.class.getResourceAsStream("images/decorArbre.png"));


    public DecorVue(Pane pane, Environnement environnement){
        this.pane = pane;
        this.environnement = environnement;
        this.imagesParDecor = new HashMap<>();
        initialiserAffichage();
    }

    public void initialiserAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();

        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);
            Image image = choisirImageInteracctif(decor);

            if (image != null) {
                ImageView imageView = creerImage(decor, image);
                this.imagesParDecor.put(decor, imageView);
                this.pane.getChildren().add(imageView);
            }
        }

    }

    public void mettreAJourAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();

        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);
            ImageView imageView = this.imagesParDecor.get(decor);

            if (imageView != null) {
                imageView.setImage(choisirImageInteracctif(decor));
                positionImage(decor, imageView);
            }
        }
    }

    private ImageView creerImage(Decor decor, Image image) {
        ImageView imageView = new ImageView(image);
        positionImage(decor, imageView);
        return imageView;
    }

    private void positionImage(Decor decor, ImageView imageView) {
        imageView.setLayoutX(decor.getX());
        imageView.setLayoutY(decor.getY());
        imageView.setScaleX(decor.getTaille());
        imageView.setScaleY(decor.getTaille());
    }

    private Image choisirImageInteracctif(Decor decor) {
        if (decor instanceof Fleur) {
            return choisirImageFleur(decor.getEtat());
        }

        return choisirImageDecorStatique(decor.getEtat());
    }

    private Image choisirImageFleur(String etat) {
        if (etat.equals("phasePerir")) {
            return this.imageFleurEnPhase;
        }

        if (etat.equals("perir")){
            return this.imageFleurPerir;
        }
        return this.imageFleurNormale;
    }

    private Image choisirImageDecorStatique(String typeDecor) {
        if (typeDecor.equals("pillier")){
            return this.imagePillier;
        }
        if (typeDecor.equals("rocher")){
            return this.imageRocher;
        }

        if (typeDecor.equals("arbre")){
            return this.imageArbre;
        }

        return null;
    }

}
