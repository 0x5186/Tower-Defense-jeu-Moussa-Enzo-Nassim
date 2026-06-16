package universite_paris8.iut.nchaieb.sae_jeux.modele;

import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Sorcier;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Nargacuga;

public class Fleur extends Decor {

    private double tempsRestantAvantPerir;

    public Fleur(double x, double y) {
        super(x, y, 110, "normale", 0.5, 2);
        this.tempsRestantAvantPerir = 0;
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

//        if (sorcierEstProche) {
//            this.setEtat("phasePerir");
//            this.tempsRestantAvantPerir = 15;
//
//        } else if (this.tempsRestantAvantPerir >13.5) {
//            this.tempsRestantAvantPerir -= 0.01;
//            this.setEtat("phasePerir");
//        } else if (this.tempsRestantAvantPerir > 0) {
//            this.tempsRestantAvantPerir-= 0.01;
//            this.setEtat("perir");
//        } else {
//            this.setEtat("normale");
//        }

        if(sorcierEstProche && this.tempsRestantAvantPerir == 0) {
            this.setEtat("phasePerir");
            this.tempsRestantAvantPerir =15;

        } else {
            if (this.tempsRestantAvantPerir > 14){
                this.tempsRestantAvantPerir -= 0.01;
                this.setEtat("phasePerir");
            } else if (this.tempsRestantAvantPerir >0) {
                this.tempsRestantAvantPerir-= 0.01;
                this.setEtat("perir");
            } else {
                this.tempsRestantAvantPerir = 0; 
                this.setEtat("normale");
            }
        }

    }

}
