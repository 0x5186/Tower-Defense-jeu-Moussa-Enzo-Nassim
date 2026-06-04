package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class FioleVue {

    int largeurCase = 90;
    int hauteurCase = 130;

    StackPane stackPane;

    public FioleVue(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setFiole(ImageView fiole, int montant){
        if(fiole !=null){
            if(montant==100){
                fiole.setViewport(new Rectangle2D(0, 0 , largeurCase, hauteurCase));
            }
            else if(montant<99 && montant>=90){
                fiole.setViewport(new Rectangle2D(largeurCase,  0, largeurCase, hauteurCase));
            }
            else if(montant<90 && montant>=80){
                fiole.setViewport(new Rectangle2D(2*largeurCase, 0, largeurCase, hauteurCase));
            }
            else if(montant<80 && montant>=70){
                fiole.setViewport(new Rectangle2D(3*largeurCase, 0, largeurCase, hauteurCase));
            }
            else if(montant<70 && montant>=60){
                fiole.setViewport(new Rectangle2D(0, hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<60 && montant>=50){
                fiole.setViewport(new Rectangle2D(largeurCase, hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<50 && montant>=40){
                fiole.setViewport(new Rectangle2D(2*largeurCase,  hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<40 && montant>=30){
                fiole.setViewport(new Rectangle2D(3*largeurCase,  hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<30 && montant>=20){
                fiole.setViewport(new Rectangle2D(0, 2* hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<20 && montant>=10){
                fiole.setViewport(new Rectangle2D(largeurCase, 2* hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant<10 && montant>=1){
                fiole.setViewport(new Rectangle2D(2*largeurCase, 2* hauteurCase, largeurCase, hauteurCase));
            }
            else if(montant==0){
                fiole.setViewport(new Rectangle2D(3*largeurCase, 2* hauteurCase, largeurCase, hauteurCase));

            }
        }



    }
}
