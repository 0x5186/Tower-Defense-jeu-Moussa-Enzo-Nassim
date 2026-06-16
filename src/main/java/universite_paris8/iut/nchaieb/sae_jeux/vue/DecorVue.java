package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Decor;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Fleur;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Marre;

import java.util.HashMap;

public class DecorVue {

    private Pane pane;
    private StackPane stackPane;
    private Environnement environnement;
    private HashMap<Decor, ImageView> hashMap;
    private Image imageFleurNormale = new Image(Main.class.getResourceAsStream("images/fleurNormale.png"));
    private Image imageFleurPerir = new Image(Main.class.getResourceAsStream("images/fleurPerie.png"));
    private Image imageFleurEnPhase= new Image(Main.class.getResourceAsStream("images/fleurEnPhase.png"));
    private Image imagePillier = new Image(Main.class.getResourceAsStream("images/decorPillier.png"));
    private Image imageRocher = new Image(Main.class.getResourceAsStream("images/decorRocher.png"));
    private Image imageArbre = new Image(Main.class.getResourceAsStream("images/decorArbre.png"));
    // pour la marre
//    private Image imageMarreNormale = new Image(Main.class.getResourceAsStream("images/MarreNormale.png"));
//    private Image imageMarreRefletDebut = new Image(Main.class.getResourceAsStream("images/MarreRefletDebut.png"));
//    private Image imageMarreRefletMilieu = new Image(Main.class.getResourceAsStream("images/MarreRefletMilieu.png"));
//    private Image imageMarreRefletFin = new Image(Main.class.getResourceAsStream("images/MarreRefletFin.png"));


    public DecorVue(Pane pane, Environnement environnement){
        this.pane = pane;
        this.environnement = environnement;
        this.hashMap = new HashMap<>();
        initialiserAffichage();
    }

    public void initialiserAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();


        //pour decor interactif
        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);

            if (decor instanceof Fleur){
                ImageView imageView = new ImageView(this.imageFleurNormale);
                imageView.setLayoutX(decor.getX());
                imageView.setLayoutY(decor.getY());
                imageView.setScaleX(decor.getTaille());
                imageView.setScaleY(decor.getTaille());
                this.hashMap.put(decor, imageView);
                this.pane.getChildren().add(imageView);
            } else  if (decor.getEtat().equals("pillier")){
                ImageView pillier = new ImageView(this.imagePillier);
                pillier.setLayoutX(decor.getX());
                pillier.setLayoutY(decor.getY());
                pillier.setScaleX(decor.getTaille());
                pillier.setScaleY(decor.getTaille());
                this.hashMap.put(decor, pillier);
                this.pane.getChildren().add(pillier);
            } else if (decor.getEtat().equals("rocher")){
                ImageView rocher = new ImageView(this.imageRocher);
                rocher.setLayoutX(decor.getX());
                rocher.setLayoutY(decor.getY());
                rocher.setScaleX(decor.getTaille());
                rocher.setScaleY(decor.getTaille());
                this.hashMap.put(decor, rocher);
                this.pane.getChildren().add(rocher);
            } else if (decor.getEtat().equals("arbre")){
                ImageView arbre = new ImageView(this.imageArbre);
                arbre.setLayoutX(decor.getX());
                arbre.setLayoutY(decor.getY());
                arbre.setScaleX(decor.getTaille());
                arbre.setScaleY(decor.getTaille());
                this.hashMap.put(decor, arbre);
                this.pane.getChildren().add(arbre);
            }



//                switch(decor.getEtat()){
//                    case "pillier":
//                        ImageView pillier = new ImageView(this.imagePillier);
//                        pillier.setLayoutX(decor.getX());
//                        pillier.setLayoutY(decor.getY());
//                        pillier.setScaleX(decor.getTaille());
//                        pillier.setScaleY(decor.getTaille());
//                        this.hashMap.put(decor, pillier);
//                        this.pane.getChildren().add(pillier);
//                        break;
//                    case "rocher":
//                        ImageView rocher = new ImageView(this.imageRocher);
//                        rocher.setLayoutX(decor.getX());
//                        rocher.setLayoutY(decor.getY());
//                        rocher.setScaleX(decor.getTaille());
//                        rocher.setScaleY(decor.getTaille());
//                        this.hashMap.put(decor, rocher);
//                        this.pane.getChildren().add(rocher);
//                }




//            if (decor instanceof Marre){
//                ImageView imageView = new ImageView(this.imageMarreNormale);
//                imageView.setLayoutX(decor.getX());
//                imageView.setLayoutY(decor.getY());
//                imageView.setScaleX(decor.getTaille());
//                imageView.setScaleY(decor.getTaille());
//                this.hashMap.put(decor, imageView);
//                this.pane.getChildren().add(imageView);
//            }
        }

    }

    public void mettreAJourAffichage(){
        ObservableList<Decor> listeDecors = this.environnement.getLesDecors();

        for(int i = 0; i < listeDecors.size(); i++){
            Decor decor = listeDecors.get(i);
            ImageView imageView = this.hashMap.get(decor);

            if (imageView != null && decor.getEtat() != null && decor instanceof Fleur){
                if (decor.getEtat().equals("phasePerir")){
                    imageView.setImage(this.imageFleurEnPhase);
                } else if (decor.getEtat().equals("perir")){
                    imageView.setImage(this.imageFleurPerir);
                } else {
                    imageView.setImage(this.imageFleurNormale);
                }
            }

//            if (decor instanceof Marre){
//                if (imageView != null) {
//                    if (decor.getEtat().equals("afficheRefletDebut")) {
//                        imageView.setImage(this.imageMarreRefletDebut);
//                    } else if (decor.getEtat().equals("afficheRefletMilieu")) {
//                        imageView.setImage(this.imageMarreRefletMilieu);
//                    } else if (decor.getEtat().equals("afficheRefletFin")){
//                        imageView.setImage(this.imageMarreRefletFin);
//                    } else {
//                        imageView.setImage(this.imageMarreNormale);
//                    }
//                }
//            }

        }
    }

}
