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
import universite_paris8.iut.nchaieb.sae_jeux.vue.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControleurJeu implements Initializable {
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
                (ev -> {
                    temps.setValue(temps.getValue() + 1);
                    this.environnement.unTour();
                    if (environnement.getBase().getPv() <= 0) {
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
        // Musique de fond (Boucle 1000 fois selon la V2)
        try {
            JouerSon musiqueFond = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/musiqueJeu.wav", 1000);
            musiqueFond.setVolume(0.85f);
            musiqueFond.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        this.terrain = new Terrain();
        this.fioleVue = new FioleVue(stackPane);
        this.sourisVue = new SourisVue(stackPane);
        this.monstreVue = new MonstreVue(this.pane);
        this.interfaceVue = new InterfaceVue(stackPane);
        this.terrainVue = new TerrainVue(terrain, tilePane);

        terrainVue.dessine(Main.map, this.pane);

        environnement = new Environnement(this.terrain);

        // V2 : Nouveaux constructeurs et ajout des Projectiles
        this.baseVue = new BaseVue(this.pane);
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane, this.baseVue);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);
        MonObservateurProjectiles monObservateurProjectiles = new MonObservateurProjectiles(pane);

        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        environnement.getLesProjectiles().addListener(monObservateurProjectiles);

        baseVue.ajouterSprite(this.environnement.getBase());

        this.fioleVue.setFiole(fiole, this.environnement.getArgent());

        this.environnement.argentProperty().addListener((observable, oldValue, newValue) -> {
            int ancienneValeur = (int) oldValue;
            int nouvelleValeur = (int) newValue;
            if (nouvelleValeur != ancienneValeur) {
                this.fioleVue.setFiole(fiole, nouvelleValeur);
            }
        });

        initAnimation();

        if (stackPane != null) {
            stackPane.setOnMouseClicked(event -> {
                if (environnement.isModePlacementTour()) {
                    if (this.environnement.tourPosable(event.getX(), event.getY())) {
                        try {
                            JouerSon sonInvocation = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/invocationTour.wav", 0);
                            sonInvocation.play();
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            e.printStackTrace();
                        }

                        // V1 : Conservation du snapping pour aligner les tours sur la grille !
                        int snappedX = ((int) event.getX() / 32) * 32;
                        int snappedY = ((int) event.getY() / 32) * 32;

                        this.environnement.ajouterTour(this.environnement.getSymboles().CombinaisonGetTour(snappedX, snappedY));
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

        // Partie symbole
        this.monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
        this.environnement.getSymbolesProperty().addListener(monObservateurSymbole);
        this.interfaceVue.dessinMenu();
    }

    @FXML
    public void actionsDesSymboles(Event event) {
        Button boutonSymbole = (Button) event.getSource();
        String symboleTexte = boutonSymbole.getText();
        String symbole = null;

        // Son d'écriture aléatoire (sécurisé avec try/catch)
        try {
            String fichierSon = Math.random() >= 0.5
                    ? "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo1.wav"
                    : "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo2.wav";
            new JouerSon(fichierSon, 0).play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        // Fusion de tous vos symboles (V1 + V2)
        switch (symboleTexte) {
            case "croix":    symbole = "croix";    break;
            case "goutte":   symbole = "goutte";   break;
            case "spirale":  symbole = "spirale";  break;
            case "oeil":     symbole = "oeil";     break;
            case "eclipse":  symbole = "eclipse";  break;
            case "oiseau":   symbole = "oiseau";   break;
            case "pic":      symbole = "pic";      break;
            case "crystal":  symbole = "crystal";  break;
            case "fleche":   symbole = "fleche";   break;
            case "tomoe":    symbole = "tomoe";    break;
            case "triangle": symbole = "triangle"; break;
            case "corne":    symbole = "corne";    break;
            case "feu":      symbole = "feu";      break;
        }

        if (symbole != null) {
            this.environnement.getSymboles().ajouterSymbole(symbole);
        }
    }

    @FXML
    public void validerPentacle() {
        if (this.environnement.getSymboles().verifierCombinaison()) {
            this.environnement.validerSymboles();
            this.sourisVue.ajouterImageSouris(this.environnement.getSymboles().CombinaisonGetTourString());
            try {
                new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/fiole.wav", 0).play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        } else {
            this.interfaceVue.viderSumbolesAffiches();
            this.monObservateurSymbole.setCompteur(0);
            this.environnement.getSymboles().reset();
            try {
                new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/erreur.wav", 0).play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
}