package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;

public class Fleur extends Decor {

    private double tempsRestant;

    public Fleur(double x, double y) {
        super(x, y, 60.0, "normale");
        this.tempsRestant = 0;
    }

    public void mettreAjour(ObservableList<Monstre> lesMonstres){
        boolean sorcierEstProche = false;

        for (int i = 0; i < lesMonstres.size(); i++){
            Monstre monstre = lesMonstres.get(i);

            if (monstre instanceof Nargacuga) {
                double distanceX = Math.abs(monstre.getPosX() - this.getX());
                double distanceY = Math.abs(monstre.getPosY() - this.getY());
                double distanceTotale = distanceX + distanceY;

                if (distanceTotale <= this.getRayonDetection()){
                    sorcierEstProche = true;
                }
            }
        }

        if (sorcierEstProche){
            this.setEtat("perir");
            this.tempsRestant = 15.0;
        } else {
            if (this.tempsRestant > 0){
                this.tempsRestant -= 0.01;
            } else {
                this.setEtat("normale");
            }
        }

    }

}
