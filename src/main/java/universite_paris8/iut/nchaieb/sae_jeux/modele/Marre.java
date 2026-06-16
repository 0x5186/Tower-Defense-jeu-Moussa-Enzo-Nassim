package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;

public class Marre extends Decor{

    private double tempsReflet;

    public Marre (double x, double y){
        super(x, y, 10, "normale", 1.5, 115);
        this.tempsReflet = 0;
    }

    public void mettreAjour(ObservableList<Monstre> lesMonstres){
        boolean sorcierEstProche = false;

        for (int i = 0; i < lesMonstres.size(); i++){
            Monstre monstre = lesMonstres.get(i);

            if (monstre instanceof Nargacuga || monstre instanceof Sorcier) {
                double distanceX = Math.abs(monstre.getPosX() - this.getX());
                double distanceY = Math.abs(monstre.getPosY() - this.getY());
                double distanceTotale = distanceX + distanceY;

                if (distanceTotale <= this.getRayonDetection()){
                    sorcierEstProche = true;
                }
            }
        }


        if(sorcierEstProche && this.tempsReflet == 0) {
            this.setEtat("afficheRefletDebut");
            this.tempsReflet  =15;
        } else {
            if (this.tempsReflet > 14.8 ){
                this.setEtat("afficheRefletMilieu");
                this.tempsReflet -= 0.01;
            } else if (this.tempsReflet > 13.8){
                this.setEtat("afficheRefletFin");
                this.tempsReflet-=0.01;
            } else {
                this.setEtat("normale");
            }
        }

    }

}
