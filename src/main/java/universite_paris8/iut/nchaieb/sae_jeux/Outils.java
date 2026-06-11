package universite_paris8.iut.nchaieb.sae_jeux;

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
}
