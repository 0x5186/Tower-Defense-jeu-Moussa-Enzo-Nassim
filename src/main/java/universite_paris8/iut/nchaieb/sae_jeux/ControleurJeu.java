package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.CombinaisonValables;
import universite_paris8.iut.nchaieb.sae_jeux.vue.BaseVue;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TerrainVue;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {
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
    protected IntegerProperty temps = new SimpleIntegerProperty(0);
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    private BaseVue baseVue;
    private MonObservateurMonstre observateur;

    private boolean modePlacementTour = false;
    private MonObservateurSymbole monObservateurSymbole;

    private void initAnimation() {
        gameLoop = new Timeline();

        KeyFrame kf = new KeyFrame(
                Duration.millis(16),
                (ev -> {
                    temps.setValue(temps.getValue() + 1);
                    this.environnement.unTour();

                    if (environnement.getBase() != null && environnement.getBase().getPv() == 0) {
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
        this.terrain = new Terrain();

        this.monstreVue = new MonstreVue(pane);
        this.interfaceVue = new InterfaceVue(stackPane);
        this.baseVue = new BaseVue(this.pane);
        this.terrainVue = new TerrainVue(terrain, tilePane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map);

        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);

        environnement = new Environnement(this.terrain);
        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);

        if(this.environnement.getBase() != null) {
            baseVue.ajouterSprite(this.environnement.getBase());
        }

        if (labelArgent != null) {
            labelArgent.textProperty().bind(environnement.argentProperty().asString("%d"));
        }

        initAnimation();

        if (stackPane != null) {
            stackPane.setOnMouseClicked(event -> {
                if (environnement.tourAPlacer != null && environnement.tourAPlacer.isModePlacementTour()) {
                    System.out.println("Tour placée sur l'herbe !");
                    environnement.placerTour(event.getX(), event.getY());
                }
            });
        }

        try {
            gameLoop.play();
        } catch (Exception e) {
            initAnimation();
        }

        // Partie symbole
        this.monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
        this.environnement.getSymbolesProperty().addListener(monObservateurSymbole);
        this.interfaceVue.dessinMenu();
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
    public void AppuyerSurSymboleOeil() {
        this.environnement.getSymboles().ajouterSymbole("oeil");
        System.out.println("Oeil d'horus ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleEclipse() {
        this.environnement.getSymboles().ajouterSymbole("eclipse");
        System.out.println("Eclipse ajouté dans la liste");
    }

    @FXML
    public void AppuyerSurSymboleOiseau() {
        this.environnement.getSymboles().ajouterSymbole("oiseau");
        System.out.println("Oiseau ajouté dans la liste");
    }

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