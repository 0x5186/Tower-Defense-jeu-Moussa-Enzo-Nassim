package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.modele.CombinaisonValables;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.Tour;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Tours.TourOeil;
import universite_paris8.iut.nchaieb.sae_jeux.vue.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable{
    private Environnement environnement;
    private ArrayList<CombinaisonValables> lesSorts;

    @FXML private TilePane tilePane;
    @FXML private StackPane stackPane;
    @FXML private Pane pane;
    @FXML private ImageView fiole;

    private Timeline gameLoop;
    protected IntegerProperty temps = new SimpleIntegerProperty(0);
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    private BaseVue baseVue;
    private FioleVue fioleVue;
    private MonObservateurSymbole monObservateurSymbole;
    private SourisVue sourisVue;

    private void initAnimation() {
        gameLoop = new Timeline();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.01),
                (ev ->{
                    temps.setValue(temps.getValue()+1);
                    this.environnement.unTour();
                    if (environnement.getBase().getPv() <= 0){
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
        this.fioleVue = new FioleVue(stackPane);
        this.sourisVue = new SourisVue(stackPane);
        this.monstreVue = new MonstreVue(pane);
        this.interfaceVue = new InterfaceVue(stackPane);
        this.baseVue = new BaseVue(this.pane);
        this.terrainVue = new TerrainVue(terrain, tilePane);

        terrainVue.dessine(Main.map);
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);

        environnement = new Environnement(this.terrain);
        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        baseVue.ajouterSprite(this.environnement.getBase());

        this.fioleVue.setFiole(fiole, this.environnement.getArgent());

        this.environnement.argentProperty().addListener((observable, oldValue, newValue) -> {
            int ancienneValeur = (int) oldValue ;
            int nouvelleValeur = (int) newValue ;
            if (nouvelleValeur != ancienneValeur) {
                this.fioleVue.setFiole(fiole, nouvelleValeur);
            }
        });

        initAnimation();

        if(stackPane != null){
            stackPane.setOnMouseClicked(event -> {
                if (environnement.isModePlacementTour()) {
                    if(this.environnement.tourPosable(event.getX(), event.getY())){
                        this.environnement.placerLaTourAttente(event.getX(), event.getY());
                        this.environnement.getSymboles().reset();
                        this.environnement.setModePlacementTour(false);
                        this.interfaceVue.viderSumbolesAffiches();
                        this.monObservateurSymbole.setCompteur(0);
                        this.sourisVue.retirerImageSouris();
                    } else {
                        System.out.println("Impossible de placer la tour sur le chemin !");
                    }
                }
            });
        }

        try {
            gameLoop.play();
        } catch (Exception e) {
            initAnimation();
        }

        this.monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
        this.environnement.getSymbolesProperty().addListener(monObservateurSymbole);
        this.interfaceVue.dessinMenu();
    }

    @FXML
    public void AjouterMonstreEnnemi() {
        this.environnement.ajouterMonstre();
    }

    @FXML
    public void actionsDesSymboles(Event event) {
        Button boutonSymbole = (Button) event.getSource();
        String symboleTexte = boutonSymbole.getText();
        String symbole = null;

        switch (symboleTexte){
            case "croix": symbole = "croix"; break;
            case "goutte": symbole = "goutte"; break;
            case "spirale": symbole = "spirale"; break;
            case "oeil": symbole = "oeil"; break;
            case "eclipse": symbole = "eclipse"; break;
            case "oiseau": symbole = "oiseau"; break;
            case "fleche": symbole = "fleche"; break;
            case "pic": symbole = "pic"; break;
            case "triangle": symbole = "triangle"; break;
        }

        if (symbole != null){
            this.environnement.getSymboles().ajouterSymbole(symbole);
        }
    }

    @FXML
    public void validerPentacle(){
        Tour nouvelleTour = this.environnement.getSymboles().verifierCombinaison();

        if (nouvelleTour != null){
            if (this.environnement.getArgent() >= nouvelleTour.getCout()) {
                this.environnement.setTourAPlacer(nouvelleTour);
                this.environnement.setModePlacementTour(true);

                if (nouvelleTour instanceof TourOeil) {
                    this.sourisVue.ajouterImageSouris("tourOeil");
                } else {
                    this.sourisVue.ajouterImageSouris("tourHeal");
                }
                System.out.println("Tour prête à être placée ! Cliquez sur l'herbe.");
            } else {
                System.out.println("Pas assez de joyaux pour cette tour !");
                this.environnement.getSymboles().reset();
                this.interfaceVue.viderSumbolesAffiches();
                this.monObservateurSymbole.setCompteur(0);
            }
        }
        else {
            System.out.println("Combinaison incorrecte !");
            this.interfaceVue.viderSumbolesAffiches();
            this.monObservateurSymbole.setCompteur(0);
            this.environnement.getSymboles().reset();
        }
    }
}