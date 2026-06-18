package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Noeud {
    public int x;
    public int y;
    public int coutD;
    public int coutA;
    public Noeud parent;

    public Noeud(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int coutF() {
        return coutD + coutA;
    }

    public boolean estMemePosition(int autreX, int autreY) {
        return this.x == autreX && this.y == autreY;
    }
}