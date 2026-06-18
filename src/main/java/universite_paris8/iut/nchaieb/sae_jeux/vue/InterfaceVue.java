package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.html.HTMLBaseElement;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

import java.awt.*;

public class InterfaceVue {

    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfacebas.png"));
    Image symboleGoutte = new Image(Main.class.getResourceAsStream("images/symboleGoutteDeau.png"));
    Image symboleCroix = new Image(Main.class.getResourceAsStream("images/symboleCroix.png"));
    Image symboleSpirale = new Image(Main.class.getResourceAsStream("images/symboleSpirale.png"));
    Image symboleOeil = new Image(Main.class.getResourceAsStream("images/symboleOeil.png"));
    Image symboleEclipse = new Image(Main.class.getResourceAsStream("images/symboleEclipse.png"));
    Image symboleCrystal = new Image(Main.class.getResourceAsStream("images/symboleCrystal.png"));
    Image symboleFleche = new Image(Main.class.getResourceAsStream("images/symboleFleche.png"));
    Image symboleTomoe = new Image(Main.class.getResourceAsStream("images/symboleTomoe.png"));
    Image symboleTriangle = new Image(Main.class.getResourceAsStream("images/symboleTriangle.png"));
    Image symboleCorne = new Image(Main.class.getResourceAsStream("images/Corne.png"));
    Image symboleFeu = new Image(Main.class.getResourceAsStream("images/symboleFeu.png"));
    Image symboleNote = new Image(Main.class.getResourceAsStream("images/symboleNote.png"));
    Image symboleFlocon = new Image(Main.class.getResourceAsStream("images/symboleFlocon.png"));
    Image symboleLimiteAtteinte = new Image(Main.class.getResourceAsStream("images/cercleLimiteAtteinte.png"));
    Image bullePourAlerte = new Image(Main.class.getResourceAsStream("images/bulle.png"));

    private Label limiteAtteinte;

    private HBox hbox;

    private ImageView cercleDeLaLimite;

    private ImageView bulleAlerte;

    ImageView livre;
    private StackPane stackPane;

    private StackPane contientSymbole;


    public InterfaceVue(StackPane stackPane, ImageView livre, Pane paneSymboles) {
        this.stackPane = stackPane;
        this.contientSymbole = new StackPane();
        this.livre=livre;
        this.limiteAtteinte = new Label("La limite est atteinte, \nle max est de 6 symboles. ");
        this.cercleDeLaLimite = new ImageView(this.symboleLimiteAtteinte);
        this.bulleAlerte = new ImageView(this.bullePourAlerte);
        this.hbox = new HBox(this.bulleAlerte, this.cercleDeLaLimite, this.limiteAtteinte);
        this.hbox.setVisible(false);
        this.stackPane.getChildren().add(this.hbox);
    }

    public void dessinMenu () {

        ImageView feuillePentacle = new ImageView(FeuilleSort);
        ImageView interfaceDuBas = new ImageView(InterfaceBas);







        if( this.stackPane!=null){


            this.stackPane.getChildren().add(interfaceDuBas);

            interfaceDuBas.setTranslateY(750);
            interfaceDuBas.setTranslateX(0);
            interfaceDuBas.setFitWidth(1950);

            //Feuille pentacle
            feuillePentacle.setTranslateX(700); // position X en pixels
            feuillePentacle.setTranslateY(650);
            feuillePentacle.setScaleX(0.65);
            feuillePentacle.setScaleY(0.65);
            this.stackPane.getChildren().add(feuillePentacle);
            this.stackPane.getChildren().add(this.contientSymbole);
        }


    }

    public void afficherUnSeulSymbole(String typeSymbole, int emplacement){
        ImageView image = null;

        switch(typeSymbole){
            case "spirale":
                image = new ImageView(symboleSpirale);
                break;
            case "croix":
                image = new ImageView(symboleCroix);
                break;
            case "goutte":
                image = new ImageView(symboleGoutte);
                break;
            case "oeil":
                image = new ImageView(symboleOeil);
                break;
            case "eclipse":
                image = new ImageView(symboleEclipse);
                break;
            case "crystal":
                image = new ImageView(symboleCrystal);
                break;
            case "fleche":
                image = new ImageView(symboleFleche);
                break;
            case "tomoe":
                image = new ImageView(symboleTomoe);
                break;
            case "triangle":
                image = new ImageView(symboleTriangle);
                break;
            case "corne":
                image = new ImageView(symboleCorne);
                break;
            case "feu":
                image = new ImageView(symboleFeu);
                break;
            case "note":
                image = new ImageView(symboleNote);
                break;
            case "flocon":
                image = new ImageView(symboleFlocon);
                break;
        }

        if (image != null){
            int positionDeBaseX = -90;
            int positionDeBaseY = 430;
            int positionSuivante =0;

            if(emplacement==0 || emplacement==3){
                positionSuivante = 0;
            } else  if(emplacement==1 || emplacement==4){
                positionSuivante = 80;
            } else  if(emplacement==2 || emplacement==5){
                positionSuivante =2 * 80;
            }

            if (emplacement>=3){
                positionDeBaseY=500;
            }

            image.setTranslateX(positionDeBaseX + positionSuivante);
            image.setTranslateY(positionDeBaseY );
            image.setScaleY(0.1);
            image.setScaleX(0.1);
            //effet glow
            image.setEffect(new Glow(1));

            this.contientSymbole.getChildren().add(image);
        }

    }
    public void viderSumbolesAffiches(){
        if (this.contientSymbole != null){
            this.contientSymbole.getChildren().clear();
        }
    }

    public void brillerSymboles() {
        this.contientSymbole.setEffect(new Glow(9));

    }
    public void  assombrirSymboles() {
        this.contientSymbole.setEffect(new Glow(0));
    }



    public void animationLivrepage(Button bouton,Button boutonCouverture, Button boutonChangerPage){


        boutonChangerPage.setDisable(true);
        int[] frameIndex = {3};
        Timeline ouvrirLivre = new Timeline(

                new KeyFrame(Duration.millis(100), e -> {



                    this.livre.setViewport(new Rectangle2D(frameIndex[0] * 190, 0, 190, 160));
                    frameIndex[0]++;

                })
        );

        ouvrirLivre.setCycleCount(3);
        ouvrirLivre.play();
        ouvrirLivre.setOnFinished(event -> {
            this.livre.setViewport(new Rectangle2D(3 * 190, 0, 190, 160));

            boutonChangerPage.setDisable(false);
            ;
            bouton.setVisible(true);
            bouton.toFront();
        });
    }
    public void animationLivrecouverture(Button bouton,Button boutonCouverture, Button boutonPageSuivante){
        int[] frameIndex = {0};
        boutonCouverture.setDisable(true);
        boutonPageSuivante.setVisible(false);
        if(bouton!=null){
            System.out.println("pasnull");
            Timeline ouvrirLivre = new Timeline(

                    new KeyFrame(Duration.millis(100), e -> {



                        this.livre.setViewport(new Rectangle2D(frameIndex[0] * 190, 0, 190, 160));
                        frameIndex[0]++;

                    })
            );

            ouvrirLivre.setCycleCount(4);
            ouvrirLivre.play();
            ouvrirLivre.setOnFinished(event -> {
                boutonCouverture.setDisable(false);
                bouton.setVisible(true);
                bouton.toFront();
                boutonPageSuivante.setVisible(true);
                boutonCouverture.setLayoutX(boutonCouverture.getLayoutX()-150);
            });
        }
        else{
            System.out.println("null");
            frameIndex[0]=3;
            Timeline ouvrirLivre = new Timeline(


                    new KeyFrame(Duration.millis(100), e -> {



                        this.livre.setViewport(new Rectangle2D(frameIndex[0] * 190, 0, 190, 160));
                        frameIndex[0]--;

                    })
            );

            ouvrirLivre.setCycleCount(4);
            ouvrirLivre.play();
            ouvrirLivre.setOnFinished(event -> {

                boutonCouverture.setLayoutX(boutonCouverture.getLayoutX()+150);
                boutonCouverture.setDisable(false);
            });

        }



    }
    public void setLivre(ImageView livre){

        livre.setViewport(new Rectangle2D(0,0,190,160));
    }



    public void afficherLimiteAtteinte() {
        this.limiteAtteinte.setStyle("-fx-text-fill: red; -fx-font-size: 24px; -fx-font-weight: bold;");
        this.cercleDeLaLimite.setFitWidth(50);
        this.cercleDeLaLimite.setPreserveRatio(true);
        this.cercleDeLaLimite.setTranslateX(-5);
        this.cercleDeLaLimite.setTranslateY(10);
        this.bulleAlerte.setFitWidth(500);
        this.bulleAlerte.setPreserveRatio(true);
        this.bulleAlerte.setTranslateX(430);
        this.bulleAlerte.setTranslateY(-200);
        this.hbox.setTranslateX(1020);
        this.hbox.setTranslateY(590);

        this.hbox.setVisible(true);
    }

    public HBox getHbox(){
        return this.hbox;
    }

}
