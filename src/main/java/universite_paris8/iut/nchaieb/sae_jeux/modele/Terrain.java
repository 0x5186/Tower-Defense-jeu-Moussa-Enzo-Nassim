package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.jar.JarEntry;

public class Terrain {

    private int[][] codeTuiles= new int[25][48];

    public void terrainPlainesCode(){
        for(int i=0; i<6; i++){
            for(int j=0; j<48; j++) {
                codeTuiles[i][j] = 1;
            }
        }
        for(int j=0; j<48; j++) {
            codeTuiles[6][j] = 5;
        }

        for(int i=7; i<25; i++){
            for(int j=0; j<48; j++) {
                codeTuiles[i][j] = 3;
            }
        }

        for(int i=0; i<19; i++){
            int iAleatoire = (int)(Math.random() * 4);
            int jAleatoire = (int)(Math.random() * 48);
            codeTuiles[iAleatoire][jAleatoire] = 2;

        }
        for(int i=0; i<25; i++) {
            int iAleatoire = (int) ((Math.random() * 9)+7);
            int jAleatoire = (int) (Math.random() * 48);
            codeTuiles[iAleatoire][jAleatoire] = 4;
        }

        for(int i=11; i<13; i++){
            for(int j=0; j<48; j++) {
                codeTuiles[i][j] = 6;
            }
        }
        for(int j=0; j<48; j++) {
            codeTuiles[10][j] = 7;
        }
        for(int j=0; j<48; j++) {
            codeTuiles[13][j] = 8;
        }

    }

    public int hauteur() {return this.codeTuiles.length;}
    public int largeur() {return this.codeTuiles[0].length;}
    public int codeTuile(int ligne, int col) {return codeTuiles[ligne][col];}

    public void test() {

        for(int i=0; i<25; i++){
            for(int j=0; j<48; j++) {
                codeTuiles[i][j] = 0;
            }
        }
    }
}
