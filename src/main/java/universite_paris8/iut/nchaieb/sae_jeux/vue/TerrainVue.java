package universite_paris8.iut.nchaieb.sae_jeux.vue;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.TilePane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.MonstreDeBase;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;
import universite_paris8.iut.nchaieb.sae_jeux.Main;

import java.util.ArrayList;

public class TerrainVue {



    Image bleu = new Image(Main.class.getResourceAsStream("images/bleu.png"));
    Image  herbe = new Image(Main.class.getResourceAsStream("images/herbe.png"));
    Image  tableau = new Image(Main.class.getResourceAsStream("images/tableau.png"));
    Image  nuage = new Image(Main.class.getResourceAsStream("images/bleunuage.png"));
    Image  herbecailloux = new Image(Main.class.getResourceAsStream("images/herbecailloux.png"));
    Image  barriere = new Image(Main.class.getResourceAsStream("images/barriere.png"));
    Image  solmilieu = new Image(Main.class.getResourceAsStream("images/solmilieu.png"));
    Image  solhaut = new Image(Main.class.getResourceAsStream("images/solhaut.png"));
    Image  solbas = new Image(Main.class.getResourceAsStream("images/solbas.png"));
    Image  tableauBase = new Image(Main.class.getResourceAsStream("images/tableauBase.png"));
    Image  tableauHaut1 = new Image(Main.class.getResourceAsStream("images/tableauHaut1.png"));
    Image  tableauHaut2 = new Image(Main.class.getResourceAsStream("images/tableauHaut2.png"));
    Image  menutest = new Image(Main.class.getResourceAsStream("images/menutest.png"));




    Image  squelette = new Image(Main.class.getResourceAsStream("images/squelette.png"));

    private TilePane tilePane;
    private Terrain terrain;
    private ArrayList<MonstreVue> monstreVues = new ArrayList<>();

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.tilePane = tilePane;


    }


    public void dessine(int map) {
        switch (map){
            case 1:
                terrain.test();
                break;

            case 2:
                terrain.terrainPlainesCode();
                break;
        }


        for (int l = 0; l < this.terrain.hauteur(); l++) {
            for (int col = 0; col < this.terrain.largeur(); col++) {
                switch (this.terrain.codeTuile(l, col)) {
                    case 1:
                        ImageView ciel = new ImageView(bleu);
                        this.tilePane.getChildren().add(ciel);
                        break;

                    case 2:
                        ImageView nuagebleu = new ImageView(nuage);
                        this.tilePane.getChildren().add(nuagebleu);
                        break;
                    case 3:
                        ImageView solHerbe = new ImageView(herbe);
                        this.tilePane.getChildren().add(solHerbe);
                        break;
                    case 4:
                        ImageView herberavecailloux = new ImageView(herbecailloux);
                        this.tilePane.getChildren().add(herberavecailloux);
                        break;
                    case 5:
                        ImageView barrieres = new ImageView(barriere);
                        this.tilePane.getChildren().add(barrieres);
                        break;
                    case 6:
                        ImageView solTerreMilieu = new ImageView(solmilieu);
                        this.tilePane.getChildren().add(solTerreMilieu);
                        break;
                    case 7:
                        ImageView solTerreHaut = new ImageView(solhaut);
                        this.tilePane.getChildren().add(solTerreHaut);
                        break;
                    case 8:
                        ImageView solTerreBas = new ImageView(solbas);
                        this.tilePane.getChildren().add(solTerreBas);
                        break;

                }
            }
        }
    }
//    public void ajouterMonstre(MonstreDeBase monstre) {
//        ImageView iv = ajouterEntite("squelette");
//        this.stackPane.getChildren().add(iv);
//
//        MonstreVue mv = new MonstreVue(monstre, iv);
//        monstreVues.add(mv);
//
//        mv.lancerAnimation(); // lance la Timeline
//    }
}
