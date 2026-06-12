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
import java.util.Scanner;

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
    private ImageView fiole;
    @FXML
    private Button boutonPageSuivante;



    private Documentation documentation;
    private Timeline gameLoop;
    protected IntegerProperty temps= new SimpleIntegerProperty(0);
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    TutorielVue tutorielVue;
    private BaseVue baseVue;
    private FioleVue fioleVue;



    private MonObservateurMonstre observateur;





    private MonObservateurTutoriel monObservateurTutoriel;
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

        this.documentation=new Documentation();


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



        this.terrain = new Terrain();





        this.fioleVue= new FioleVue(stackPane);
        this.sourisVue= new SourisVue(stackPane);
        this.monstreVue= new MonstreVue(this.pane);
        this.interfaceVue = new InterfaceVue(stackPane);

        this.terrainVue = new TerrainVue(terrain, tilePane);
        this.tutorielVue = new TutorielVue(stackPane);


        System.out.println(Main.map);
        terrainVue.dessine(Main.map, this.pane);
        environnement= new Environnement(this.terrain);
        this.baseVue= new BaseVue(this.pane, this.environnement.getBase());
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane, this.baseVue);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);
        MonObservateurSortsTours monObservateurSortsTours = new MonObservateurSortsTours(pane);

        System.out.println(this.baseVue);




        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        environnement.getLesProjectiles().addListener(monObservateurSortsTours);




        this.fioleVue.setFiole(fiole,this.environnement.getArgent());


        baseVue.ajouterSprite(this.environnement.getBase());
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


        //partie tuto
        MonObservateurTutoriel monObservateurTutoriel = new MonObservateurTutoriel(this.tutorielVue);
        this.tutorielVue.tutoProperty().addListener(monObservateurTutoriel);
        this.boutonPageSuivante.setVisible(false);

    }





    @FXML
    public void AjouterMonstreEnnemi() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.environnement.ajouterMonstre();
    }

    @FXML
    public void actionsDesSymboles(Event event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Button boutonSymbole = (Button) event.getSource();
        String symboleTexte = boutonSymbole.getText();
        double ecriture= Math.random();
        String symbole = null;
        JouerSon sonEcriture;
        if(environnement.getArgent()>0){
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
                case "note":
                    symbole = "note";
                    break;
                case "flocon":
                    symbole = "flocon";
                    break;
            }

            if (symbole != null){
                this.environnement.getSymboles().ajouterSymbole(symbole);
            }
        }

    }




    @FXML
    public void validerPentacle() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//
        if (this.environnement.getSymboles().verifierCombinaison() &&  this.environnement.getArgent()>= this.documentation.prix(this.environnement.getSymboles().CombinaisonGetTourString())) {
            this.environnement.validerSymboles();
            this.sourisVue.ajouterImageSouris(this.environnement.getSymboles().CombinaisonGetTourString());
            JouerSon sonFiole = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/fiole.wav",0);
            sonFiole.play();

        }
        else { this.interfaceVue.viderSumbolesAffiches();
            JouerSon sonErreur = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/erreur.wav",0);
            sonErreur.play();
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
    public void tournerDePage(){
        this.tutorielVue.changerPage();
    }




}

