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
import javafx.scene.control.TextField;
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
import java.util.Comparator;
import java.util.List;
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
    private ImageView fiole;
    @FXML
    private Button boutonPageSuivante;
    @FXML
    private ImageView livre;
    @FXML
    private Pane  paneSymboles;
    @FXML
    private Button boutonOuvrirLivre;
    @FXML
    private Button symbolesPageSuivante;
    @FXML
    private Button symbolesPagePrecedente;
    @FXML
    private Button tomoe;

    @FXML
    private Button triangle;
    @FXML
    private Button spirale;

    @FXML
    private Button note;

    @FXML
    private Button oeil;
    @FXML
    private Button corne;

    @FXML
    private Button croix;

    @FXML
    private Button crystal;

    @FXML
    private Button eclipse;

    @FXML
    private Button feu;

    @FXML
    private Button fleche;

    @FXML
    private Button flocon;

    @FXML
    private Button gouttedeau;

    @FXML
    private Text nombreEncre;



    @FXML
    private Button boutonCorneDeBrume;

    @FXML
    private Pane feuilleSymbole;

    private Documentation documentation;
    private Timeline gameLoop;
    protected IntegerProperty temps;
    TerrainVue terrainVue;
    Terrain terrain;
    MonstreVue monstreVue;
    InterfaceVue interfaceVue;
    TutorielVue tutorielVue;
    private BaseVue baseVue;
    private FioleVue fioleVue;
    private DecorVue decorVue;


    private  int page;

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
                    if (this.decorVue != null){
                        this.decorVue.mettreAJourAffichage();
                    }
                    if (environnement.getBase().getPv()==0){
                        gameLoop.stop();
                        System.out.println("perdu");
                    }
                    positionAJour();

                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(kf);
    }

    private void positionAJour() {

//        enfants.sort(Comparator.comparingDouble(Node::getLayoutY));


        List<Node> nodes = new ArrayList<>(pane.getChildren());

        nodes.sort(Comparator.comparingDouble(Node::getLayoutY));

        pane.getChildren().setAll(nodes);

//        int index=0;
//        Node yMin = null;
//        for (int i=0;i < pane.getChildren().size();i++){
//
//        }
//        for (int j=0;j < pane.getChildren().size();j++) {
//            for (int i=0;i < pane.getChildren().size();i++) {
//                if(pane.getChildren().get(j).getLayoutY()>pane.getChildren().get(i).getLayoutY()){
//                    yMin=pane.getChildren().get(i);
//                    index=i;
//
//                }
//
//            }
//            if (yMin!= null && yMin!=pane.getChildren().get(j)){
//                Node temp = pane.getChildren().get(j);
//                pane.getChildren().set(j, pane.getChildren().get(index));
//                pane.getChildren().set(index, temp);
//            }
//
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        temps= new SimpleIntegerProperty(0);

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


        this.page=0;
        symbolesPagePrecedente.setVisible(false);
        symbolesPageSuivante.setVisible(false);


        this.fioleVue= new FioleVue(stackPane);
        this.sourisVue= new SourisVue(stackPane);
        this.monstreVue= new MonstreVue(this.pane);
        this.interfaceVue = new InterfaceVue(stackPane, livre, paneSymboles);

        this.terrainVue = new TerrainVue(terrain, tilePane);
        this.tutorielVue = new TutorielVue(stackPane);


        System.out.println(Main.map);
        terrainVue.dessine(Main.map, this.pane);
        environnement= new Environnement(this.terrain,this.boutonCorneDeBrume);
        this.decorVue = new DecorVue(this.pane, this.environnement);
        this.baseVue= new BaseVue(this.pane, this.environnement.getBase());
        MonObservateurMonstre observateurMonstres = new MonObservateurMonstre(pane, this.baseVue);
        MonObservateurTour monObservateurTour = new MonObservateurTour(pane);
        MonObservateurSortsTours monObservateurSortsTours = new MonObservateurSortsTours(pane);

        this.interfaceVue.setLivre(this.livre);
        System.out.println(this.baseVue);
        this.symbolesPageSuivante.setVisible(false);



        environnement.getLesMonstres().addListener(observateurMonstres);
        environnement.getLesTours().addListener(monObservateurTour);
        environnement.getLesProjectiles().addListener(monObservateurSortsTours);




        this.fioleVue.setFiole(fiole,this.environnement.getArgent());
//        this.nombreEncre.textProperty().bindBidirectional(this.environnement.argentProperty().asObject(), new NumberStringConverter());
        Bindings.bindBidirectional(
                this.nombreEncre.textProperty(),
                this.environnement.argentProperty(),
                new NumberStringConverter()
        );
        baseVue.ajouterSprite(this.environnement.getBase());
        this.environnement.argentProperty().addListener((observable, oldValue, newValue) -> {
            int nouvelleValeur=(int) newValue ;
            this.fioleVue.setFiole(fiole,nouvelleValeur);


        });
        initAnimation();

//        if (this.environnement.getSymboles().verifierCombinaison()){
//            for(int i=0; i<stackPane.getChildren().size();i++){
//                if (stackPane.getChildren().get(i).equals("f")){
//                    stackPane.getChildren().get(i).
//                }
//            }
//        }
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
    public void actionsDesSymboles(Event event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("croix");
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
                    System.out.println("croix ajouté");
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

            boolean ajoutReussi = this.environnement.getSymboles().ajouterSymbole(symbole);

//            if (symbole != null){
//                this.environnement.getSymboles().ajouterSymbole(symbole);
//            } else {
//                this.interfaceVue.afficherLimiteAtteinte();
//            }

            if (!ajoutReussi){
                this.interfaceVue.afficherLimiteAtteinte();
            }
        }
        if (this.environnement.getSymboles().verifierCombinaison()){
            this.interfaceVue.brillerSymboles();
        }
        else {
            this.interfaceVue.assombrirSymboles();
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

            if (this.interfaceVue.getHbox() != null){
                this.interfaceVue.getHbox().setVisible(false);
            }

            JouerSon sonErreur = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/erreur.wav",0);
            sonErreur.play();
            this.monObservateurSymbole.setCompteur(0);
            this.environnement.getSymboles().reset();
        }
    }

    @FXML
    public void deroulerParcheminTutoriel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        JouerSon sonOuvrirParchemin = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/OuvrirParcheminTuto.wav", 0);
        this.tutorielVue.afficherTutot();
        this.boutonPageSuivante.setVisible(!this.boutonPageSuivante.isVisible());
        sonOuvrirParchemin.setVolume(0.85f);
        sonOuvrirParchemin.play();
    }

    @FXML
    public void tournerDePage() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.tutorielVue.changerPage();
        if (this.tutorielVue.getPage() >= 1 && this.tutorielVue.getPage() <= this.tutorielVue.getPageMax()){
            JouerSon sonTournerPage = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/tournerPage.wav", 0);
            sonTournerPage.setVolume(0.70f);
            sonTournerPage.play();
        }
    }


    @FXML
    public void couvertureLivre(){

        try {
            sonLivre();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Node p : paneSymboles.getChildren()) {
            p.setVisible(false);
        }

        if(page!=0) {
            System.out.println(2);
            this.fleche.setVisible(false);
            this.interfaceVue.animationLivrecouverture(null, this.boutonOuvrirLivre, this.symbolesPageSuivante);
            page=0;
            this.symbolesPagePrecedente.setVisible(false);
            this.symbolesPageSuivante.setVisible(false);
        }
        else {
            System.out.println(1);

            this.interfaceVue.animationLivrecouverture(this.fleche,this.boutonOuvrirLivre, this.symbolesPageSuivante);
            page=1;
            symbolesPagePrecedente.setVisible(false);

        }


    }

    public void sonLivre() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        JouerSon sonPage;
        sonPage = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/page.wav",0);
        sonPage.setVolume(0.8f);
        sonPage.play();
    }


    @FXML
    public void boutonGererPages(ActionEvent event){
        try {
            sonLivre();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Node p : paneSymboles.getChildren()) {
            p.setVisible(false);
        }

        if(event.getSource()==this.symbolesPageSuivante)
            this.page++;

        else if (event.getSource()==this.symbolesPagePrecedente) {
            page--;
        }

        switch (this.page) {
            case 1:


                this.interfaceVue.animationLivrepage(fleche, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 2:

                this.interfaceVue.animationLivrepage(oeil, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 3:

                this.interfaceVue.animationLivrepage(this.crystal, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 4:

                this.interfaceVue.animationLivrepage(this.note, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;

            case 5:

                this.interfaceVue.animationLivrepage(this.croix, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;

            case 6:

                this.interfaceVue.animationLivrepage(this.eclipse, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 7:

                this.interfaceVue.animationLivrepage(this.triangle, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 8:

                this.interfaceVue.animationLivrepage(this.tomoe, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;

            case 9:

                this.interfaceVue.animationLivrepage(this.corne, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 10:

                this.interfaceVue.animationLivrepage(this.feu, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;

            case 11:

                this.interfaceVue.animationLivrepage(this.gouttedeau, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 12:

                this.interfaceVue.animationLivrepage(this.flocon, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;
            case 13:

                this.interfaceVue.animationLivrepage(this.spirale, this.boutonOuvrirLivre, this.symbolesPageSuivante);
                break;






        }
        if(page==1){
            symbolesPagePrecedente.setVisible(false);
        }
        else{
            symbolesPagePrecedente.setVisible(true);
        }
        if(page>=13){
            symbolesPageSuivante.setVisible(false);
        }
        else{
            symbolesPageSuivante.setVisible(true);
        }
//        if(this.page.get()>0){
//            this.symbolesPageSuivante.setVisible(true);
//        }
//        else{
//            this.symbolesPageSuivante.setVisible(false);
//        }
    }

    @FXML
    public void lancerVague () throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        JouerSon sonCorne;
        sonCorne = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/corneDeBrumeSon.wav",0);
        sonCorne.play();


        this.environnement.setCompteurSpawn(0);
        this.environnement.preparerVague(this.environnement.getNumeroVague());
        this.environnement.setPauseEntreVagues(false);

    }




}

