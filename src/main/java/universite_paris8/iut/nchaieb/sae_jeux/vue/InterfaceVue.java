package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

public class InterfaceVue {
    Image FeuilleSort = new Image(Main.class.getResourceAsStream("images/FeuillePourLesSorts.png"));
    Image InterfaceBas = new Image(Main.class.getResourceAsStream("images/interfaceBas.png"));
    Image symboleGoutte = new Image(Main.class.getResourceAsStream("images/symboleGoutteDeau.png"));
    Image symboleCroix = new Image(Main.class.getResourceAsStream("images/symboleCroix.png"));
    Image symboleSpirale = new Image(Main.class.getResourceAsStream("images/symboleSpirale.png"));
    Image symboleOeil = new Image(Main.class.getResourceAsStream("images/symboleOeil.png"));
    Image symboleEclipse = new Image(Main.class.getResourceAsStream("images/symboleEclipse.png"));
    Image symboleOiseau = new Image(Main.class.getResourceAsStream("images/symboleOiseau.png"));

    private StackPane stackPane;

    private StackPane contientSymbole;


    public InterfaceVue(StackPane stackPane) {
        this.stackPane = stackPane;
        this.contientSymbole = new StackPane();
    }

    public void dessinMenu () {

        ImageView feuillePentacle = new ImageView(FeuilleSort);
        ImageView interfaceDuBas = new ImageView(InterfaceBas);


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

    public void afficherUnSeulSymbole(String typeSymbole){
        ImageView image = null;



        switch(typeSymbole){
            case "spirale":
                image = new ImageView(symboleSpirale);
                image.setTranslateX(760);
                break;
            case "croix":
                image = new ImageView(symboleCroix);
                image.setTranslateX(600);
                break;
            case "goutte":
                image = new ImageView(symboleGoutte);
                image.setTranslateX(680);
                break;
            case "oeil":
                image = new ImageView(symboleOeil);
                image.setTranslateX(680);
                break;
            case "eclipse":
                image = new ImageView(symboleEclipse);
                image.setTranslateX(680);
                break;
            case "oiseau":
                image = new ImageView(symboleOiseau);
                image.setTranslateX(740);
        }


        if (image != null){
            if (image.getImage().equals(symboleOeil)){
                image.setTranslateY(440);
                image.setScaleY(0.05);
                image.setScaleX(0.05);
            } else if (image.getImage().equals(symboleEclipse)){
                image.setTranslateY(530);
                image.setScaleY(0.05);
                image.setScaleX(0.05);
            } else if (image.getImage().equals(symboleOiseau)){
                image.setTranslateY(550);
                image.setScaleY(0.05);
                image.setScaleX(0.05);
            } else {
                image.setTranslateY(485);
                image.setScaleY(0.1);
                image.setScaleX(0.1);
            }
            this.contientSymbole.getChildren().add(image);
        }
    }

    public void viderSumbolesAffiches(){
        if (this.contientSymbole != null){
            this.contientSymbole.getChildren().clear();
        }
    }


}
