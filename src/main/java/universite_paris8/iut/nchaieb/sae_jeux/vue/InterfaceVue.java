package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.StringProperty;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

import java.awt.*;

public class InterfaceVue {
    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfaceBas.png"));
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


    private StackPane stackPane;

    private StackPane contientSymbole;


    public InterfaceVue(StackPane stackPane) {
        this.stackPane = stackPane;
        this.contientSymbole = new StackPane();
    }

    public void dessinMenu () {

        ImageView feuillePentacle = new ImageView(FeuilleSort);
        ImageView interfaceDuBas = new ImageView(InterfaceBas);
//        ImageView  tiroir= new ImageView(tiroirDeSymboles);


        if( this.stackPane!=null){

            this.stackPane.getChildren().add(interfaceDuBas);

            //Interface du bas
            interfaceDuBas.setTranslateY(750);
            interfaceDuBas.setTranslateX(500);
            interfaceDuBas.setScaleX(1.25);
            interfaceDuBas.setScaleX(2.5);

            //Feuille pentacle
            feuillePentacle.setTranslateX(1400); // position X en pixels
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
            int positionDeBaseX = 600;
            int positionDeBaseY = 450;
            int positionSuivante =0;

            if(emplacement==0 || emplacement==3){
                positionSuivante = 0;
            } else  if(emplacement==1 || emplacement==4){
                positionSuivante = 80;
            } else  if(emplacement==2 || emplacement==5){
                positionSuivante =2 * 80;
            }

            if (emplacement>=3){
                positionDeBaseY=520;
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


}
