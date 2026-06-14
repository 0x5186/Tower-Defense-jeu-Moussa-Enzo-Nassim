package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import universite_paris8.iut.nchaieb.sae_jeux.Main;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

import java.util.HashMap;


public class TutorielVue {

    //affiche le parchemin
    Image parcheminTuto = new Image(Main.class.getResourceAsStream("images/ParcheminTuto.png"));
    Image imageCombinaison1 = new Image(Main.class.getResourceAsStream("images/imageCombinaison.png"));
    Image imageCombinaison2 = new Image(Main.class.getResourceAsStream("images/imageCombinaison2.png"));
    Image imageCombinaison3 = new Image(Main.class.getResourceAsStream("images/imageCombinaison.png"));
    Image imageTitreTuto = new Image(Main.class.getResourceAsStream("images/TitreTuto.png"));

    private StackPane stackPane;
    private BooleanProperty afficher;
    private VBox vbox;
    private VBox vBoxTitre;
    private int page;
    private Label titre;
    private Label paragraphe;
    private Label pageDefilee;
    private ImageView image;
    private ImageView imageCombi1;
    private ImageView imageCombi2;
    private ImageView imageCombi3;
    private ImageView titreTuto;

    public TutorielVue(StackPane stackPane){
        this.page= 0;
        this.image = new ImageView(this.parcheminTuto);
        this.imageCombi1 = new ImageView(this.imageCombinaison1);
        this.imageCombi2 = new ImageView(this.imageCombinaison2);
        this.imageCombi3 = new ImageView(this.imageCombinaison3);
        this.titreTuto = new ImageView(this.imageTitreTuto);
        this.stackPane =stackPane;
        this.vbox = new VBox();
        this.vBoxTitre = new VBox();
        this.titre = new Label();
        this.pageDefilee = new Label();
        this.paragraphe = new Label();
        this.afficher = new SimpleBooleanProperty(false);
        this.image.setVisible(false);
        definitionTailleDesObjets();
        ajoutCouleurDesObjets();
        definitionDesPositionsDesObjets();
        ajoutDesObjetsDansLavbox();
        ajoutObjetDansLeStackPane();
        afficherImage();
        this.titre.setStyle("-fx-font-family: 'Ransite Medieval PERSONAL USE' ; -fx-font-size: 19px ; -fx-color-label-visible: #94836b");
        this.paragraphe.setStyle("-fx-font-family: 'Ransite Medieval PERSONAL USE' ; -fx-font-size: 16px");
    }


    public void retirerImage(){
        this.titreTuto.setImage(null);
    }

    public void ajoutObjetDansLeStackPane() {
        this.stackPane.getChildren().add(image);
        this.stackPane.getChildren().add(vbox);
        this.stackPane.getChildren().add(vBoxTitre);
    }

    public void ajoutDesObjetsDansLavbox(){
        this.vbox.getChildren().add(this.titre);
        this.vbox.getChildren().add(this.pageDefilee);
        this.vbox.getChildren().add(this.paragraphe);
        this.vbox.getChildren().add(this.imageCombi1);
        this.vbox.getChildren().add(this.imageCombi2);
        this.vbox.getChildren().add(this.imageCombi3);
        this.vBoxTitre.getChildren().add(this.titreTuto);
    }

    public void definitionTailleDesObjets(){
        this.vbox.setPrefSize(800, 800);
        this.vBoxTitre.setPrefSize(800, 800);
        this.titre.setScaleY(2);
        this.titre.setScaleX(2);
        this.pageDefilee.setScaleX(2);
        this.pageDefilee.setScaleY(2);
        this.paragraphe.setScaleY(1.5);
        this.paragraphe.setScaleX(1.5);
        this.image.setScaleX(1.7);
        this.image.setScaleY(1.3);
        this.imageCombi1.setScaleX(0.5);
        this.imageCombi1.setScaleY(0.5);
        this.imageCombi2.setScaleX(0.5);
        this.imageCombi2.setScaleY(0.5);
        this.imageCombi3.setScaleX(0.5);
        this.imageCombi3.setScaleY(0.5);
        this.titreTuto.setScaleX(0.6);
        this.titreTuto.setScaleY(0.6);
    }

    public void ajoutCouleurDesObjets(){
        this.titre.setTextFill(Color.BLACK);
        this.pageDefilee.setTextFill(Color.BLACK);
        this.paragraphe.setTextFill(Color.BLACK);
    }



    public void definitionDesPositionsDesObjets(){
        this.titre.setTranslateX(-130);
        this.titre.setTranslateY(20);
        this.pageDefilee.setTranslateX(-225);
        this.pageDefilee.setTranslateY(610);
        this.vbox.setTranslateX(1610);
        this.vbox.setTranslateY(50);
        this.vbox.setMaxHeight(1000);
        this.vBoxTitre.setTranslateX(1170);
        this.image.setTranslateX(1360);
        this.image.setTranslateY(90);
        this.imageCombi1.setTranslateX(-340);
        this.imageCombi1.setTranslateY(-95);
        this.imageCombi2.setTranslateX(-450);
        this.imageCombi2.setTranslateY(40);
        this.imageCombi3.setTranslateX(-340);
        this.imageCombi3.setTranslateY(20);
        this.titreTuto.setTranslateX(520);
        this.titreTuto.setTranslateX(20);
    }

    public void afficherImage(){
        this.titreTuto.setVisible(false);
        this.imageCombi1.setVisible(false);
        this.imageCombi2.setVisible(false);
        this.imageCombi3.setVisible(false);
        System.out.println("on est censé être à la page : " + this.page);

        if (this.page == 1){
            this.titreTuto.setImage(imageTitreTuto);
            this.titreTuto.setVisible(true);
        }
        if (this.page == 2){
            this.imageCombi1.setVisible(true);
            this.imageCombi2.setVisible(true);
            this.imageCombi3.setVisible(true);
        }


    }


    public void afficherTutot(){
        this.titreTuto.setImage(null);
        //faut que la position du paragraphe se reinitialise a chaque fois qu'on clique sur le bouton
        this.paragraphe.setTranslateY(90);
        this.paragraphe.setTranslateX(-180);
        //à chaque fois qu'on ouvre le tuto on met la page à 1
        this.page = 1;
        //le paragraphes/pageDefilee est toujours vidé sinon il restera afficher même après avoir fermé le tuto
        this.paragraphe.setText("");
        this.pageDefilee.setText("");
        afficherImage();
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
        String pageActuel = "Page " + this.page;
        switch (this.page){
            case 2:
                this.pageDefilee.setText(pageActuel);
                paragraphes();
                break;
            case 3:
                this.pageDefilee.setText(pageActuel);
                paragraphes();
                break;
        }
    }

    public void paragraphes() {

          if (this.page == 2) {
            this.paragraphe.setText("            Premièrement, pour faire apparaître des tours, \n            vous devez suivre des combinaisons de symboles spécifique \n            et dans l'ordre." +
                    "\n            Essayez avec cette combinaison ci-dessous." +
                    "\n\n\nMaintenant grâce à ce sort, vous pouvez faire apparaitre une \nou plusieurs tours d'attaques basique" +
                    "\n\nEssayez à présent avec ces combinaisons suivantes : ");
            afficherImage();
        } else if (this.page == 3) {
            this.paragraphe.setTranslateY(90);
            this.paragraphe.setTranslateX(-200);
            this.paragraphe.setText("                 A présent faut faire attention et dépenser sa peinture \n                 intélligemment. Lorsqu'il ya suffisamment d'encres dans \n                 " +
                    "le pot de peinture, vous pourrez alors tracer n'importe " +
                    "\n                 quel sort.\n\n      En revanche, si ce dernier est vide, il faudra attendre qu'il se \n      remplisse lorsque des monstres sont tués.");
            afficherImage();
        }
    }

}
