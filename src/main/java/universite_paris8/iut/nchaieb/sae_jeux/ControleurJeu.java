package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.CombinaisonValables;
import universite_paris8.iut.nchaieb.sae_jeux.vue.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControleurJeu implements Initializable{
    private Environnement environnement;
    private ArrayList<CombinaisonValables> lesSorts;



    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane pane;
    @FXML
    private ImageView fiole;



    private Timeline gameLoop;
    protected IntegerProperty temps= new SimpleIntegerProperty(0);
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    private BaseVue baseVue;
    private FioleVue fioleVue;


    private MonObservateurMonstre observateur;






    private MonObservateurSymbole monObservateurSymbole;
    private SourisVue sourisVue;

    private void initAnimation() {
        gameLoop = new Timeline();


        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.01),

                (ev ->{
                    temps.setValue(temps.getValue()+1);
                    this.environnement.unTour();
                    if (environnement.getBase().getPv()==0){
                        gameLoop.stop();
                        System.out.println("perdu");
                    }

                })

        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JouerSon musiqueFond = null;
        try {
            musiqueFond = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/musiqueJeu.wav",1000);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        musiqueFond.setVolume(0.85f);
        musiqueFond.play();

//        if(musiqueFond.currentFrame!=null && musiqueFond.currentFrame==8.5){
//            musiqueFond.currentFrame= Long.valueOf(5);
//        }




        //ajout du pane
        this.terrain = new Terrain();





        this.fioleVue= new FioleVue(stackPane);
        this.sourisVue= new SourisVue(stackPane);
        this.monstreVue= new MonstreVue(pane);
        this.interfaceVue = new InterfaceVue(stackPane);
        this.baseVue= new BaseVue(this.pane);
        this.terrainVue = new TerrainVue(terrain, tilePane);


        System.out.println(Main.map);
        terrainVue.dessine(Main.map, this.pane);
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);

        environnement= new Environnement(this.terrain);
        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        baseVue.ajouterSprite(this.environnement.getBase());

        this.fioleVue.setFiole(fiole,this.environnement.getArgent());

        this.environnement.argentProperty().addListener((observable, oldValue, newValue) -> {
            int nouvelleValeur=(int) newValue ;
            this.fioleVue.setFiole(fiole,nouvelleValeur);


        });
        initAnimation();



        if(stackPane!=null){
            stackPane.setOnMouseClicked(event -> {

                if (environnement.isModePlacementTour()) {
                    if(this.environnement.tourPosable(event.getX(), event.getY())){
                        JouerSon sonInvocation = null;
                        try {
                            sonInvocation = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/invocationTour.wav",0);
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (LineUnavailableException e) {
                            throw new RuntimeException(e);
                        }

                        sonInvocation.play();
                        this.environnement.ajouterTour(this.environnement.getSymboles().CombinaisonGetTour((int) event.getX(), (int) event.getY()));
                        this.environnement.getSymboles().reset();
                        this.environnement.setModePlacementTour(false);
                        this.interfaceVue.viderSumbolesAffiches();
                        this.monObservateurSymbole.setCompteur(0);
                        this.sourisVue.retirerImageSouris();



                    }
                }
            });
        }





        try {
            gameLoop.play();
        } catch (Exception e) {
            initAnimation();
        }

        //Partie symbole
        this.monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
        this.environnement.getSymbolesProperty().addListener(monObservateurSymbole);
        this.interfaceVue.dessinMenu();





    }





    @FXML
    public void AjouterMonstreEnnemi() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.environnement.ajouterMonstre();
    }

//    @FXML
//    public void AppuyerSurSymboleCroix() {
//        this.environnement.getSymboles().ajouterSymbole("croix");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleGoutteDeau() {
//        this.environnement.getSymboles().ajouterSymbole("goutte");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleSpirale() {
//        this.environnement.getSymboles().ajouterSymbole("spirale");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleOeil(){
//        this.environnement.getSymboles().ajouterSymbole("oeil");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleEclipse() {
//        this.environnement.getSymboles().ajouterSymbole("eclipse");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleOiseau(){
//        this.environnement.getSymboles().ajouterSymbole("oiseau");
//    }
//
//    @FXML
//    public void AppuyerSurSymboleFleche() {
//        this.environnement.getSymboles().ajouterSymbole("fleche");
//    }
//
//    @FXML
//    public void AppuyerSurSymbolePic() { this.environnement.getSymboles().ajouterSymbole("pic");}
//
//    @FXML
//    public void AppuyerSurSymboleTriangle() { this.environnement.getSymboles().ajouterSymbole("triangle");}

    //élimination redondance
    @FXML
    public void actionsDesSymboles(Event event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Button boutonSymbole = (Button) event.getSource();
        String symboleTexte = boutonSymbole.getText();
        double ecriture= Math.random();
        String symbole = null;
        JouerSon sonEcriture;
        if(ecriture>=0.5){
            sonEcriture = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo1.wav",0);
        }
        else{
            sonEcriture = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo2.wav",0);
        }

        sonEcriture.play();

        switch (symboleTexte){
            case "croix":
                symbole = "croix";
                break;
            case "goutte":
                symbole = "goutte";
                break;
            case "spirale":
                symbole = "spirale";
                break;
            case "oeil":
                symbole = "oeil";
                break;
            case "eclipse":
                symbole = "eclipse";
                break;
            case "crystal":
                symbole = "crystal";
                break;
            case "fleche":
                symbole = "fleche";
                break;
            case "tomoe":
                symbole = "tomoe";
                break;
            case "triangle":
                symbole = "triangle";
                break;
            case "corne":
                symbole = "corne";
                break;
            case "feu":
                symbole = "feu";
                break;
        }

        if (symbole != null){
            this.environnement.getSymboles().ajouterSymbole(symbole);
        }
    }


//    @FXML
//    public void AppuyerSurOuvrirTiroir() {
//        System.out.println("bla");
//    }


//    @FXML
//    public void activerModePlacementTour() {
//        this.environnement.setModePlacementTour(true);
//        System.out.println("Mode placement de tour activé. Cliquez sur une case vide du terrain !");
//    }
//    private void placerTour(double xPixel, double yPixel) {
//        int TAILLE_TUILE = 16;
//        int gridX = (int) (xPixel / TAILLE_TUILE);
//        int gridY = (int) (yPixel / TAILLE_TUILE);
//
//        if (!this.terrain.estPraticable(gridX, gridY)) {
//
//            int posXGridPixel = gridX * TAILLE_TUILE;
//            int posYGridPixel = gridY * TAILLE_TUILE;
//
//            Tour nouvelleTour = new TourOeil();
//            this.environnement.ajouterTour(nouvelleTour);
//
//            // Tour ramenée à 32x32 pour un terrain en 16px
//            Rectangle rectTour = new Rectangle(32, 32, Color.DIMGRAY);
//            rectTour.setTranslateX(posXGridPixel);
//            rectTour.setTranslateY(posYGridPixel);
//            stackPane.getChildren().add(rectTour);
//
//            this.modePlacementTour = false;
//            System.out.println("Tour placée avec succès en X:" + gridX + " Y:" + gridY);
//        } else {
//            System.out.println("Impossible de placer une tour sur le chemin des monstres !");
//        }
//    }



    @FXML
    public void validerPentacle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        if (this.environnement.getSymboles().verifierCombinaison()) {
            this.environnement.validerSymboles();
            this.sourisVue.ajouterImageSouris(this.environnement.getSymboles().CombinaisonGetTourString());
            JouerSon sonFiole = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/fiole.wav",0);
            sonFiole.play();
//            try {
//                JouerSon sonFiole = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/fiole.wav"); // Note the .wav extension!
//                sonFiole.play();
//            } catch (Exception e) {
//                System.err.println("Could not play audio file: " + e.getMessage());
//                e.printStackTrace();
//            }
        }
        else { this.interfaceVue.viderSumbolesAffiches();
            JouerSon sonErreur = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/erreur.wav",0);
            sonErreur.play();
            this.monObservateurSymbole.setCompteur(0);
            this.environnement.getSymboles().reset();
        }
    }
}

