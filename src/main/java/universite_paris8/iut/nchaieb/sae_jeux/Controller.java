package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.CombinaisonValables;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    private Environnement environnement;
    private ArrayList<CombinaisonValables> lesSorts;



    @FXML
    private TilePane tilePane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Pane pane;
    @FXML
    private Label labelArgent;




    private Timeline gameLoop;
    private int temps;
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    private MonObservateurMonstre observateur;




    private boolean modePlacementTour = false;
    private MonObservateurSymbole monObservateurSymbole;

    private void initAnimation() {
        gameLoop = new Timeline();


        KeyFrame kf = new KeyFrame(
                Duration.millis(16),

                (ev ->{
                    temps++;
                    this.environnement.unTour();
//
                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ajout du pane
        this.terrain = new Terrain();
        this.terrainVue = new TerrainVue(terrain, tilePane);

        System.out.println(stackPane);

        this.monstreVue= new MonstreVue(pane);
        this.interfaceVue = new InterfaceVue(stackPane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map);
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);

        environnement= new Environnement(this.terrain);
        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        if (labelArgent != null) {
            labelArgent.textProperty().bind(environnement.argentProperty().asString("%d"));
        }





        initAnimation();



        if(stackPane!=null){
            stackPane.setOnMouseClicked(event -> {
                if (environnement.tourAPlacer.isModePlacementTour()) {
                    System.out.println("pasnulllllll");
                    environnement.placerTour(event.getX(), event.getY());
                }
            });
        }




        if (Main.map == 2) {
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




    }



    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");
    }

    @FXML
    public void AjouterMonstreEnnemi() {
        this.environnement.ajouterMonstre();
    }

    @FXML
    public void AppuyerSurSymboleCroix() {
        this.environnement.getSymboles().ajouterSymbole("croix");
        System.out.println("Croix ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleGoutteDeau() {
        this.environnement.getSymboles().ajouterSymbole("goutte");
        System.out.println("Goutte ajouté dans liste");
    }

    @FXML
    public void AppuyerSurSymboleSpirale() {
        this.environnement.getSymboles().ajouterSymbole("spirale");
        System.out.println("Spirale ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOeil(){
        this.environnement.getSymboles().ajouterSymbole("oeil");
        System.out.println("Oeil d'horus ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleEclipse() {
        this.environnement.getSymboles().ajouterSymbole("eclipse");
        System.out.println("Eclipse ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOiseau(){
        this.environnement.getSymboles().ajouterSymbole("oiseau");
        System.out.println("Oiseau ajouté dans la liste");
    }


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
    public void AppuyerSurValideePentacle() {
        System.out.println(this.environnement.getSymboles());
        Tour nouvelleTour = this.environnement.getSymboles().verifierCombinaison();

        if (nouvelleTour != null) {
            if (this.environnement.getArgent() >= nouvelleTour.getCout()) {
                this.environnement.ajouterTour(nouvelleTour);
                System.out.println("Prêt à placer la tour ! Cliquez sur l'herbe.");
            } else {
                System.out.println("Fonds insuffisants ! Il vous faut " + nouvelleTour.getCout() + " pièces.");
            }
        }

        this.environnement.getSymboles().reset();
        this.interfaceVue.viderSumbolesAffiches();
    }
}

