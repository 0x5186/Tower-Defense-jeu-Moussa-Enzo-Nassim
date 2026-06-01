package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.AlgorithmeAEtoile;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Noeud;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

import java.util.ArrayList;

public abstract class Monstre extends Entite {

    public static int compteurID = 0;

    private String id;

    protected int nombreDePV;
    protected int pvMax;
    private int atq;
//    protected String biome;


    protected int vitesse;// multiplicateur de vitesse ou pixels par sec ?
    protected int portee;


    private ArrayList<Noeud> chemin;
    private final int TAILLE_TUILE = 16;

    private int targetX;
    private int targetY;


    public Monstre(int pvMax, int atq, int posX, int posY, int vitesse){

        this.atq=atq;
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.vitesse = vitesse;
        this.actionActuelle.set("fixe");
        this.targetX = 119;
        this.targetY = 26;
        this.setPosX(posX);
        this.setPosY(posY);

    }
//    public void setTerrain(Terrain terrain) {
//        this.terrain = terrain;
//    }

    public  void infligerDegat(Monstre monstre){

        if (monstre.nombreDePV != 0){
            monstre.retirerPV(this.atq);

        }
    }

    public void ajouterPV(int soin){
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax){
            this.nombreDePV = this.pvMax;
        }
    }
    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV <= 0){
            this.nombreDePV = 0;
        }
    }


    public int getPortee() {
        return portee;
    }




    public void setSpawnEnnemi(Terrain terrain) {
        int portailAleatoire = (int) (Math.random() * 3);
//        this.setPosX(0);
//        this.setPosY(0);
        if (portailAleatoire == 0) { // Haut-Gauche
            this.setPosX(0);
            this.setPosY(8 * TAILLE_TUILE);
        } else if (portailAleatoire == 1) { // Bas-Gauche
            this.setPosX(10 * TAILLE_TUILE);
            this.setPosY(51 * TAILLE_TUILE);
        } else { // Haut-Milieu
            this.setPosX(50 * TAILLE_TUILE);
            this.setPosY(0);

        }

        // Base
        this.targetX = terrain.largeur() - 1;
        this.targetY = 26;
    }



    public void agir(ObservableList<Monstre> collegues, Terrain terrain) {

//
//        if (monstrePlusProche != null) {
//                this.infligerDegat(  monstrePlusProche);
//                return;
//        }
        if (!estBloqueParAllie(collegues)) {
            System.out.println(estBloqueParAllie(collegues));

            this.setActionActuelle("marche");
            this.avancer(terrain);
        }
    }



    private boolean estBloqueParAllie(ObservableList<Monstre> collegues) {
        for (Monstre collegue : collegues) {
            if (collegue != this && collegue.estVivant()) {
                int distanceX = Math.abs(collegue.getPosX() - this.getPosX());
                int distanceY = Math.abs(collegue.getPosY() - this.getPosY());

                if ((distanceX + distanceY) < 25) {
                    if (collegue.getPosX() > this.getPosX()) return true;

                    if (collegue.getPosX() == this.getPosX() && collegue.getPosY() == this.getPosY()) {
                        if (this.hashCode() > collegue.hashCode()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

//version de agir au cas ou le joueur peut invoquer des monstres
//    public void agir(ArrayList<Monstre> collegues) {
//        if (!ennemis.isEmpty()) {
//            MonstreDeBase monstrePlusProche = plusProche(ennemis);
//            if (monstrePlusProche != null) {
//                this.setAttaque(true);
//                this.infligerDegat(monstrePlusProche);
//                return;
//            }
//        }
//
//        this.setAttaque(false);
//        if (!estBloqueParAllie(collegues)) {
//            this.avancer();
//        }
//    }


    private void avancer(Terrain terrain) {
        if (terrain != null) {
            if (this.chemin == null || this.chemin.isEmpty()) {
                int departGridX = this.getPosX() / TAILLE_TUILE;
                int departGridY = this.getPosY() / TAILLE_TUILE;

                this.chemin = AlgorithmeAEtoile.trouverChemin(terrain, departGridX, departGridY, this.targetX, this.targetY);

                if (this.chemin == null || this.chemin.isEmpty()) return;
            }

            Noeud prochaineEtape = this.chemin.get(0);

            int ciblePixelX = prochaineEtape.x * TAILLE_TUILE;
            int ciblePixelY = prochaineEtape.y * TAILLE_TUILE;

            int dx = ciblePixelX - this.getPosX();
            int dy = ciblePixelY - this.getPosY();

            int deplacementX = 0;
            int deplacementY = 0;

            if (dx > 0) deplacementX = Math.min(this.vitesse, dx);
            else if (dx < 0) deplacementX = Math.max(-this.vitesse, dx);

            if (dy > 0) deplacementY = Math.min(this.vitesse, dy);
            else if (dy < 0) deplacementY = Math.max(-this.vitesse, dy);

            this.setPosX(this.getPosX() + deplacementX);
            this.setPosY(this.getPosY() + deplacementY);

            if (this.getPosX() == ciblePixelX && this.getPosY() == ciblePixelY) {
                this.chemin.remove(0);
            }
        }


    }



    public Monstre plusProche(ArrayList<Monstre> listeMonstre){

        Monstre monstrePlusProche= null;
        for(int i=0; i <listeMonstre.size(); i++){
            if(this.estDansLeRayon(listeMonstre.get(i))){
                if( monstrePlusProche==null || calculDistance(listeMonstre.get(i))<calculDistance(monstrePlusProche)){
                    monstrePlusProche=listeMonstre.get(i);

                }
            }
        }
        return monstrePlusProche;

    }

    private int calculDistance(Monstre monstre) {
        int distance = (monstre.getPosX()+ monstre.getPosY())-(getPosY()+getPosX());
        if (distance<0)
            distance=distance*-1;
        return distance;
    }

    public boolean estDansLeRayon (Monstre monstre){
        //on va calculer la distance entre la tour et le mosntre
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());

        //on va multiplier la distance de monstre*tour(x) et monstre*tour(y)
        int distance = distanceX+distanceY;


        //on compare la distance a la porte mais on doit les mettre à unité égale
        if (distance <= this.portee) {
            return true;
        }

        return false;
    }

    public boolean estVivant() {
//        if(this.nombreDePV==0){
//            setActionActuel(0);
//        }

        return this.nombreDePV > 0;
    }


    public int getVitesse() {
        return vitesse;
    }

    public int getPV(){
        return this.nombreDePV;
    }

    public String getId(){
        return this.id;
    }


    public void setSpawnAllie(){
        this.setPosX(700);
        this.setPosY(120);

    }


}
