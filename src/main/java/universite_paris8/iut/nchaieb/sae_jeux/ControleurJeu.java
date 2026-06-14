package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
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

    // ── FXML ─────────────────────────────────────────────────────────────────
    @FXML private TilePane   tilePane;
    @FXML private StackPane  stackPane;
    @FXML private Pane       pane;
    @FXML private ImageView  fiole;
    @FXML private Button     boutonPageSuivante;
    @FXML private ImageView  livre;                    // ← v2
    @FXML private Pane       paneSymboles;             // ← v2
    @FXML private Button     boutonOuvrirLivre;        // ← v2
    @FXML private Button     symbolesPageSuivante;     // ← v2
    @FXML private Button     symbolesPagePrecedente;   // ← v2
    @FXML private Button     tomoe;                    // ← v2
    @FXML private Button     triangle;                 // ← v2
    @FXML private Button     spirale;                  // ← v2
    @FXML private Button     note;                     // ← v2
    @FXML private Button     oeil;                     // ← v2
    @FXML private Button     corne;                    // ← v2
    @FXML private Button     croix;                    // ← v2
    @FXML private Button     crystal;                  // ← v2
    @FXML private Button     eclipse;                  // ← v2
    @FXML private Button     feu;                      // ← v2
    @FXML private Button     fleche;                   // ← v2
    @FXML private Button     flocon;                   // ← v2
    @FXML private Button     gouttedeau;               // ← v2
    @FXML private Text       nombreEncre;              // ← v2

    // ── Champs ───────────────────────────────────────────────────────────────
    private Documentation documentation;              // ← v2
    private Timeline gameLoop;
    protected IntegerProperty temps;                  // initialisé dans initialize() (v2)
    TerrainVue   terrainVue;
    Terrain      terrain;
    MonstreVue   monstreVue;
    InterfaceVue interfaceVue;
    TutorielVue  tutorielVue;
    private BaseVue  baseVue;
    private FioleVue fioleVue;
    private int page;                                 // ← v2

    private MonObservateurMonstre  observateur;
    private MonObservateurTutoriel monObservateurTutoriel;
    private MonObservateurSymbole  monObservateurSymbole;
    private SourisVue sourisVue;

    // ── initAnimation ────────────────────────────────────────────────────────
    private void initAnimation() {
        gameLoop = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.seconds(0.01), ev -> {
            temps.setValue(temps.getValue() + 1);
            this.environnement.unTour();
            if (environnement.getBase().getPv() == 0) {
                gameLoop.stop();
                System.out.println("perdu");
            }
        });
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);
    }

    // ── initialize ───────────────────────────────────────────────────────────
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        temps = new SimpleIntegerProperty(0);          // ← v2 (était un champ initialisé en v1)
        this.documentation = new Documentation();      // ← v2

        // Musique de fond
        JouerSon musiqueFond = null;
        try {
            musiqueFond = new JouerSon(
                    "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/musiqueJeu.wav", 1000);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        musiqueFond.setVolume(0.85f);
        musiqueFond.play();

        this.terrain = new Terrain();
        this.page = 0;                                 // ← v2
        symbolesPagePrecedente.setVisible(false);      // ← v2
        symbolesPageSuivante.setVisible(false);        // ← v2

        this.fioleVue    = new FioleVue(stackPane);
        this.sourisVue   = new SourisVue(stackPane);
        this.monstreVue  = new MonstreVue(this.pane);
        this.interfaceVue = new InterfaceVue(stackPane, livre); // ← v2 (v1 n'avait pas livre)
        this.terrainVue  = new TerrainVue(terrain, tilePane);
        this.tutorielVue = new TutorielVue(this.stackPane);

        System.out.println(Main.map);
        terrainVue.dessine(Main.map, this.pane);
        environnement = new Environnement(this.terrain);
        this.baseVue  = new BaseVue(this.pane, this.environnement.getBase());

        MonObservateurMonstre    observateurMonstres    = new MonObservateurMonstre(pane, this.baseVue);
        MonObservateurTour       monObservateurTour     = new MonObservateurTour(pane);
        MonObservateurSortsTours monObservateurSortsTours = new MonObservateurSortsTours(pane);

        this.interfaceVue.setLivre(this.livre);        // ← v2
        System.out.println(this.baseVue);
        this.symbolesPageSuivante.setVisible(false);   // ← v2 (double sécurité)

        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        environnement.getLesProjectiles().addListener(monObservateurSortsTours);

        this.fioleVue.setFiole(fiole, this.environnement.getArgent());

        // Binding encre ↔ texte (v2 ; v1 utilisait un listener simple)
        Bindings.bindBidirectional(
                this.nombreEncre.textProperty(),
                this.environnement.argentProperty(),
                new NumberStringConverter()
        );

        baseVue.ajouterSprite(this.environnement.getBase());

        this.environnement.argentProperty().addListener((observable, oldValue, newValue) -> {
            this.fioleVue.setFiole(fiole, (int) newValue);
        });

        initAnimation();

        if (stackPane != null) {
            stackPane.setOnMouseClicked(event -> {
                if (environnement.isModePlacementTour()) {
                    if (this.environnement.tourPosable(event.getX(), event.getY())) {
                        JouerSon sonInvocation;
                        try {
                            sonInvocation = new JouerSon(
                                    "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/invocationTour.wav", 0);
                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            throw new RuntimeException(e);
                        }
                        sonInvocation.play();
                        this.environnement.ajouterTour(
                                this.environnement.getSymboles().CombinaisonGetTour((int) event.getX(), (int) event.getY()));
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

        // Partie symbole
        this.monObservateurSymbole = new MonObservateurSymbole(this.interfaceVue);
        this.environnement.getSymbolesProperty().addListener(monObservateurSymbole);
        this.interfaceVue.dessinMenu();

        // Partie tuto
        MonObservateurTutoriel monObservateurTutoriel = new MonObservateurTutoriel(this.tutorielVue);
        this.tutorielVue.tutoProperty().addListener(monObservateurTutoriel);
        this.boutonPageSuivante.setVisible(false);
    }

    // ── Actions FXML ─────────────────────────────────────────────────────────
    @FXML
    public void AjouterMonstreEnnemi() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.environnement.ajouterMonstre();
    }

    @FXML
    public void actionsDesSymboles(Event event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("croix");
        Button boutonSymbole = (Button) event.getSource();
        String symboleTexte  = boutonSymbole.getText();
        String symbole = null;

        if (environnement.getArgent() > 0) {           // ← garde ajoutée en v2
            JouerSon sonEcriture;
            if (Math.random() >= 0.5) {
                sonEcriture = new JouerSon(
                        "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo1.wav", 0);
            } else {
                sonEcriture = new JouerSon(
                        "src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/stylo2.wav", 0);
            }
            sonEcriture.play();

            switch (symboleTexte) {
                case "croix":    System.out.println("croix ajouté"); symbole = "croix";    break;
                case "goutte":   symbole = "goutte";    break;
                case "spirale":  symbole = "spirale";   break;
                case "oeil":     symbole = "oeil";      break;
                case "eclipse":  symbole = "eclipse";   break;
                case "crystal":  symbole = "crystal";   break;
                case "fleche":   symbole = "fleche";    break;
                case "tomoe":    symbole = "tomoe";     break;
                case "triangle": symbole = "triangle";  break;
                case "corne":    symbole = "corne";     break;
                case "feu":      symbole = "feu";       break;
                case "note":     symbole = "note";      break;
                case "flocon":   symbole = "flocon";    break;
            }

            if (symbole != null) {
                this.environnement.getSymboles().ajouterSymbole(symbole);
            }
        }
    }

    @FXML
    public void validerPentacle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // v2 : vérifie aussi le prix avant de valider
        boolean combinaisonValide = this.environnement.getSymboles().verifierCombinaison();
        boolean argentSuffisant   = this.environnement.getArgent() >=
                this.documentation.prix(this.environnement.getSymboles().CombinaisonGetTourString());

        if (combinaisonValide && argentSuffisant) {
            this.environnement.validerSymboles();
            this.sourisVue.ajouterImageSouris(this.environnement.getSymboles().CombinaisonGetTourString());
            new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/fiole.wav", 0).play();
        } else {
            this.interfaceVue.viderSumbolesAffiches();
            new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/erreur.wav", 0).play();
            this.monObservateurSymbole.setCompteur(0);
            this.environnement.getSymboles().reset();
        }
    }

    @FXML
    public void deroulerParcheminTutoriel() {
        System.out.println("je suis ici");
        this.tutorielVue.afficherTutot();
        this.boutonPageSuivante.setVisible(!this.boutonPageSuivante.isVisible());
    }

    @FXML
    public void tournerDePage() {
        this.tutorielVue.changerPage();
    }

    // ── Livre / pages symboles (v2) ───────────────────────────────────────────
    @FXML
    public void couvertureLivre() {
        for (Node p : paneSymboles.getChildren()) {
            p.setVisible(false);
        }
        if (page != 0) {
            this.fleche.setVisible(false);
            this.interfaceVue.animationLivrecouverture(null, this.boutonOuvrirLivre, this.symbolesPageSuivante);
            page = 0;
            this.symbolesPagePrecedente.setVisible(false);
            this.symbolesPageSuivante.setVisible(false);
        } else {
            this.interfaceVue.animationLivrecouverture(this.fleche, this.boutonOuvrirLivre, this.symbolesPageSuivante);
            page = 1;
            symbolesPagePrecedente.setVisible(false);
        }
    }

    @FXML
    public void boutonGererPages(ActionEvent event) {
        for (Node p : paneSymboles.getChildren()) {
            p.setVisible(false);
        }

        if (event.getSource() == this.symbolesPageSuivante)   this.page++;
        else if (event.getSource() == this.symbolesPagePrecedente) page--;

        switch (this.page) {
            case 1:  this.interfaceVue.animationLivrepage(fleche,       this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 2:  this.interfaceVue.animationLivrepage(oeil,         this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 3:  this.interfaceVue.animationLivrepage(crystal,      this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 4:  this.interfaceVue.animationLivrepage(note,         this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 5:  this.interfaceVue.animationLivrepage(croix,        this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 6:  this.interfaceVue.animationLivrepage(eclipse,      this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 7:  this.interfaceVue.animationLivrepage(triangle,     this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 8:  this.interfaceVue.animationLivrepage(tomoe,        this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 9:  this.interfaceVue.animationLivrepage(corne,        this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 10: this.interfaceVue.animationLivrepage(feu,          this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 11: this.interfaceVue.animationLivrepage(gouttedeau,   this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 12: this.interfaceVue.animationLivrepage(flocon,       this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
            case 13: this.interfaceVue.animationLivrepage(spirale,      this.boutonOuvrirLivre, this.symbolesPageSuivante); break;
        }

        symbolesPagePrecedente.setVisible(page > 1);
        symbolesPageSuivante.setVisible(page < 13);
    }
}