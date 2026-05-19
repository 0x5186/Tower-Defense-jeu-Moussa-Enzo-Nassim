package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class MonstreDeBase implements EntiteAlliee{

    protected String type;
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
//    protected String biome;
    private String id;
    private IntegerProperty PosX;
    private IntegerProperty PosY;
    protected int vitesse;// multiplicateur de vitesse ou pixels par sec ?
    public static int compteurID = 0;
    private int recompenseArgent;
    protected int actionActuel; //0=rien 1=fixe 2=avance  3=frappe 4=meurt


    public MonstreDeBase(int pvMax, int atq, int PosX, int PosY, int vitesse){
        this.pvMax = pvMax;
        this.nombreDePV = pvMax;
        this.atq = atq;
        this.actionActuel=0;

//        this.biome = biome;
        this.id ="M"+ this.compteurID;
        this.compteurID++;
//        this.recompenseArgent = recompenseArgent;
        this.PosX = new SimpleIntegerProperty(PosX);
        this.PosY = new SimpleIntegerProperty(PosY);
        this.vitesse = vitesse;
    }

    protected MonstreDeBase() {
    }

    public  void infligerDegat(EntiteAllieeDeBase alliee){
        if (alliee.nombreDePV != 0){
            alliee.retirerPV(this.atq);
        }
    }


    public void ajouterPV(int soin){
        this.nombreDePV += soin;
        if (this.nombreDePV > this.pvMax){
            this.nombreDePV = this.pvMax;
        }
    }

    public void agir() {


    }


    public boolean estVivant() {
        if (this.nombreDePV != 0){
            return true;
        }
        return false;
    }

    public int getPV(){
        return this.nombreDePV;
    }

    public String getId(){
        return this.id;
    }

    public void setPosX(int posX) {
        this.PosX.set(posX);
    }

    public int getPosX() { return this.PosX.get(); }

    public int getPosY() { return this.PosY.get(); }

    public int getActionActuel() {
        return actionActuel;
    }

    public String getType() {
        return type;
    }
}
