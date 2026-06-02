package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Base {
    private int pvMax;
    private int pv;
    private int posX;
    private int posY;

    public Base() {
        this.posY = 26;
        this.posX = 105;
        this.pvMax = 100;
        this.pv = this.pvMax;

    }

    public int getPv() {
        return pv;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void ajouterPv(int pvAjoutes){
        if(this.pv+pvAjoutes >this.pvMax){
            this.pv= this.pvMax;
        }
        else {
            this.pv= this.pv+pvAjoutes;
        }
    }
    public void retirerPv(int pvRetires){
        if (this.pv-pvRetires<0){
            this.pv=0;
        }
        else{
            this.pv=this.pv-pvRetires;
        }
    }




}
