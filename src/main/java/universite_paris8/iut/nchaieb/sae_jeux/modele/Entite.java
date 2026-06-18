package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entite implements EntiteInterface{

    //    protected String biome;
    private String id;
    public static int compteurID = 0;


    private IntegerProperty posX;
    private IntegerProperty posY;


    protected StringProperty actionActuelle= new SimpleStringProperty();


    public Entite(){


//        this.biome = biome;
        this.id ="E"+ this.compteurID;
        this.compteurID++;
        this.actionActuelle.set("fixe");


    }

    public String getId(){
        return this.id;
    }

    public int getPosX() {
        return posX.get();
    }

    public IntegerProperty posXProperty() {
        return posX;
    }

    public int getPosY() {
        return posY.get();
    }

    public IntegerProperty posYProperty() {
        return posY;
    }


    public void setPosY(int posY) {
        this.posY.set(posY);
    }

    public void setPosX(int posX) {
        this.posX.set(posX);
    }



    public StringProperty getActionActuelle() {
        return actionActuelle;
    }


    public void setActionActuelle(String actionActuelle) {
        this.actionActuelle.set(actionActuelle);


    }
}
