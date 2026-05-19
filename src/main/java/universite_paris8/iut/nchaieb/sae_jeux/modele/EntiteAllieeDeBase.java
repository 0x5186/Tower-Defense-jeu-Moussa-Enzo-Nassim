package universite_paris8.iut.nchaieb.sae_jeux.modele;

public abstract class EntiteAllieeDeBase implements EntiteAlliee{
    protected int nombreDePV;
    protected int pvMax;
    protected int atq;
    protected String biome;
    private String id;
    public static int compteurID = 0;

    public EntiteAllieeDeBase(int pvMax, int atq, String biome){
//        this.pvMax = pvMax;
//        this.nombreDePV = pvMax;
//        this.atq = atq;
//        this.biome = biome;
        this.id ="E"+ this.compteurID;
        this.compteurID++;
    }

    public EntiteAllieeDeBase() {

    }

    public  void infligerDegat(MonstreDeBase ennemies){
    }



    public void retirerPV(int degat) {
        this.nombreDePV -= degat;
        if (this.nombreDePV <= 0){
            this.nombreDePV = 0;
        }
    }

    public int  getPV(){
        return this.nombreDePV;
    }

    public String getId(){
        return this.id;
    }






    //typeAnim 1= mouvement
    //typeAnim 2= attaque
    //typeAnim 3= mort
    //typeEntite 1=
//    public void animation(int typeAnim, int typeEntite){
//        switch (typeEntite){
//            case 1:
//                System.out.printf("truc");
//                break;
//                }
//
//
//
//        }
//    }
}
