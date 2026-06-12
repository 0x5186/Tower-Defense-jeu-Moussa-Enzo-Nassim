package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Outils {



    public boolean estDansLeRayon (int aX,int aY, int bX, int bY,int portee){
        int distanceX = Math.abs(bX - aX);
        int distanceY = Math.abs( bY - aY);
        int distance = distanceX+distanceY;
        if (distance <= portee) {
            return true;
        }

        return false;
    }


    public DoubleProperty calculAngle(int aX, int aY, int bX, int bY, int cX, int cY){

        double angle1=Math.atan2(aY-bY,aX-bY);
        double angle2=Math.atan2(cY-bY,cX-bY);;
        DoubleProperty angle=  new SimpleDoubleProperty( Math.toDegrees(angle2-angle1));
//        double longueurBA;
//        double longueurBC;


        return angle;




    }
}
