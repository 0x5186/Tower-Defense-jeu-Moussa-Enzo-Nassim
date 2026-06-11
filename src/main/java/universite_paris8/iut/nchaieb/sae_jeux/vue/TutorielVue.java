package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.nchaieb.sae_jeux.Main;


public class TutorielVue {

    //affiche le parchemin
    Image parcheminTuto = new Image(Main.class.getResourceAsStream("images/ParcheminTuto.png"));
    Image imageCombinaison1 = new Image(Main.class.getResourceAsStream("images/imageCombinaison.png"));
    Image imageCombinaison2 = new Image(Main.class.getResourceAsStream("images/imageCombinaison2.png"));


    private StackPane stackPane;
    private BooleanProperty afficher;
    private HBox hbox;
    private int page;
    private Label titre;
    private Label paragraphe;
    private Label pageDefilee;
    private ImageView image;
    private ImageView imageCombi1;
    private ImageView imageCombi2;

    public TutorielVue(StackPane stackPane){
        this.image = new ImageView(parcheminTuto);
        this.imageCombi1 = new ImageView(imageCombinaison1);
        this.imageCombi2 = new ImageView(imageCombinaison2);
        this.stackPane =stackPane;
        this.hbox = new HBox();
        this.titre = new Label();
        this.pageDefilee = new Label();
        this.paragraphe = new Label();
        this.afficher = new SimpleBooleanProperty(false);
        //definit taille + couleur titre + position
        this.titre.setScaleY(2);
        this.titre.setScaleX(2);
        this.titre.setTextFill(Color.BLACK);
        //definit taille + couleur pour les pages  + position
        this.pageDefilee.setScaleX(2);
        this.pageDefilee.setScaleY(2);
        this.pageDefilee.setTextFill(Color.BLACK);
        this.pageDefilee.setTranslateX(-170);
        this.pageDefilee.setTranslateY(620);
        //paragraphe
        this.paragraphe.setTextFill(Color.BLACK);
        this.paragraphe.setScaleY(1.5);
        this.paragraphe.setScaleX(1.5);
        this.paragraphe.setTranslateY(90);
        this.paragraphe.setTranslateX(-180);
        //ajout du titre/paragraphe/pagedefilee dans le hbox + position hbox
        this.hbox.getChildren().add(this.titre);
        this.hbox.getChildren().add(this.pageDefilee);
        this.hbox.getChildren().add(this.paragraphe);
        this.hbox.getChildren().add(this.imageCombi1);
        this.hbox.getChildren().add(this.imageCombi2);
        this.hbox.setTranslateX(1420);
        this.hbox.setTranslateY(50);
        //images positions + deja caché
        this.image.setScaleX(1.7);
        this.image.setScaleY(1.3);
        this.image.setTranslateX(1360);
        this.image.setTranslateY(90);
        this.image.setVisible(false);
        this.imageCombi1.setScaleX(0.5);
        this.imageCombi1.setScaleY(0.5);
        this.imageCombi1.setTranslateX(-705);
        this.imageCombi1.setTranslateY(140);
        this.imageCombi1.setVisible(false);
        this.imageCombi2.setScaleX(0.5);
        this.imageCombi2.setScaleY(0.5);
        this.imageCombi2.setTranslateX(-1370);
        this.imageCombi2.setTranslateY(400);
        this.imageCombi2.setVisible(false);
        //ajout rectangle dans hbox + ajout hbox dans stackpane
        this.stackPane.getChildren().add(image);
        this.stackPane.getChildren().add(hbox);
    }

    public void afficherTutot(){
        //à chaque fois qu'on ouvre le tuto on met la page à 0
        this.page = 0;
        //le paragraphes/pageDefilee est toujours vidé sinon il restera afficher même après avoir fermer le tuto
        this.paragraphe.setText("");
        this.pageDefilee.setText("");
        this.imageCombi1.setVisible(false);
        this.imageCombi2.setVisible(false);
        //vu que le simpleProperty est sur false, on veut s'assurer de le mettre en true avant
        this.afficher.set(!this.afficher.get());
        //si maintenant il est true, il affiche
        if (this.afficher.get()){

            this.titre.setText("Witch Hat Atelier ~ Tutoriel");
        } else {
            this.titre.setText("");
        }
    }

    public BooleanProperty tutoProperty(){
        return this.afficher;
    }


    public ImageView getImage(){
        return this.image;
    }

    public void changerPage(){
        page++;
        switch (this.page){
            case 1:
                this.pageDefilee.setText("Page " + this.page);
                paragraphes();
                break;
            case 2:
                this.pageDefilee.setText("Page " + this.page);
                paragraphes();
                break;
        }
    }

    public void paragraphes() {
        if (this.page == 1) {
            this.paragraphe.setText("            Premièrement, pour faire apparaître des tours, \n            vous devez suivre des combinaisons de symboles spécifique \n             et dans l'ordre." +
                    "\n\n            Essayez avec cette combinaison ci-dessous." +
                    "\n\n\n\nMaintenant grâce à ce sort, vous pouvez faire apparaitre une \nou plusieurs tours d'attaques basique" +
                    "\n\n\nEssayez à présent avec ces combinaisons suivantes : ");
            this.imageCombi1.setVisible(true);
            this.imageCombi2.setVisible(true);
        } else if (this.page == 2) {
            this.paragraphe.setText("lolo, \n ~Nassim");
            this.imageCombi1.setVisible(false);
            this.imageCombi2.setVisible(false);
        }
    }

}
