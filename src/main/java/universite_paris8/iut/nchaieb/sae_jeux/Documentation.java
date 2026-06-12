package universite_paris8.iut.nchaieb.sae_jeux;

public class Documentation {


    public int prix(String tour){
        int prix=100;
        if(tour.equals("tourOeil")){
            prix=40;
        }
        else if(tour.equals("tourTesla")){
            prix=15;
        }
        else if(tour.equals("tourMusique")){
            prix=10;
        }
        else if(tour.equals("tourHeal")){
            prix=70;
        }
        return prix;
    }
}
