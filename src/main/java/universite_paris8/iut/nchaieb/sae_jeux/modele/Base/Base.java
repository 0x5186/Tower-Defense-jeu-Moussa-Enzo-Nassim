package universite_paris8.iut.nchaieb.sae_jeux.modele.Base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Base {
    private int pvMax;
    private IntegerProperty pv;
    private int posX;
    private int posY;

    public Base() {
        this.posX = 1856; // 58 * 32px
        this.posY = 384;  // 12 * 32px
        this.pvMax = 100;
        this.pv = new SimpleIntegerProperty(this.pvMax);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty(){
        return pv;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void ajouterPv(int pvAjoutes){
        if(this.pv.get()+pvAjoutes >this.pvMax){
            this.pv.set(this.pvMax);
        }
        else {
            this.pv.set(this.pv.get() + pvAjoutes);
        }
    }
    public void retirerPv(int pvRetires){
        if (this.pv.get() - pvRetires<0){
            this.pv.set(0);
        }
        else{
            this.pv.set(this.pv.get() - pvRetires);
        }
    }
}
