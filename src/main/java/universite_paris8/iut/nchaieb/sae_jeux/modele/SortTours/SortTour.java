package universite_paris8.iut.nchaieb.sae_jeux.modele.SortTours;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class SortTour {
    private IntegerProperty x;
    private IntegerProperty y;
    private int degats;
    private boolean attaqueFini;

    public SortTour(int départX, int départY, int degats) {
        this.x = new SimpleIntegerProperty(départX);
        this.y = new SimpleIntegerProperty(départY);
        this.degats = degats;
        this.attaqueFini=false;
    }


    public boolean isAttaqueFini() {
        return attaqueFini;
    }

    public void setAttaqueFini(boolean attaqueFini) {
        this.attaqueFini = attaqueFini;
    }

    public int getDegats() {
        return degats;
    }
    public abstract boolean verifPosition();
    public void sortAJour() {}

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }
}
