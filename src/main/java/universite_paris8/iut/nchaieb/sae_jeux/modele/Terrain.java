package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    private int[][] codeTuiles;
    private boolean [][] casesBloquees;

    public Terrain() {
        codeTuiles = new int[25][60];
        casesBloquees = new boolean[25][60];

        // Ligne du haut (Spawn 1)
        for (int colonne = 0; colonne <= 45; colonne++) codeTuiles[8][colonne] = 1;
        for (int colonne = 0; colonne <= 45; colonne++) codeTuiles[7][colonne] = 1;
        // Ligne du milieu
        for (int colonne = 10; colonne <= 45; colonne++) codeTuiles[14][colonne] = 1;
        for (int colonne = 10; colonne <= 45; colonne++) codeTuiles[15][colonne] = 1;
        // Ligne du bas (spawn 3)
        for (int colonne = 0; colonne <= 10; colonne++) codeTuiles[21][colonne] = 1;
        for (int colonne = 0; colonne <= 10; colonne++) codeTuiles[22][colonne] = 1;
        // Ligne finale vers la base
        for (int colonne = 45; colonne <= 59; colonne++) codeTuiles[11][colonne] = 1;
        for (int colonne = 45; colonne <= 59; colonne++) codeTuiles[12][colonne] = 1;

        for (int colonne = 0; colonne <= 51; colonne++) codeTuiles[21][colonne] = 1;
        for (int colonne = 0; colonne <= 51; colonne++) codeTuiles[22][colonne] = 1;

        // Descente du Spawn 2
        for (int ligne = 0; ligne <= 15; ligne++) codeTuiles[ligne][24] = 1;
        for (int ligne = 0; ligne <= 15; ligne++) codeTuiles[ligne][25] = 1;

        //deuxieme voie de remontée possible du bas pour éviter problème avec la tour de glace
        for (int ligne = 16; ligne <= 22; ligne++) codeTuiles[ligne][30] = 1;
        for (int ligne = 16; ligne <= 22; ligne++) codeTuiles[ligne][31] = 1;

        for (int ligne = 8; ligne <= 15; ligne++) codeTuiles[ligne][11] = 1;
        for (int ligne = 8; ligne <= 15; ligne++) codeTuiles[ligne][10] = 1;

        // Descente vers la base
        for (int ligne = 8; ligne <= 14; ligne++) codeTuiles[ligne][44] = 1;
        for (int ligne = 8; ligne <= 14; ligne++) codeTuiles[ligne][45] = 1;

        for (int ligne = 11; ligne <= 22; ligne++) codeTuiles[ligne][50] = 8;
        for (int ligne = 11; ligne <= 22; ligne++) codeTuiles[ligne][51] = 7;
        codeTuiles[7][24] = 9;
        codeTuiles[7][25] = 9;
        codeTuiles[8][24] = 9;
        codeTuiles[8][25] = 9;


    }

    public int hauteur() { return codeTuiles.length; }
    public int largeur() { return codeTuiles[0].length; }
    public int codeTuile(int ligne, int col) { return codeTuiles[ligne][col]; }

    public boolean estPraticable(int colonne, int ligne) {
        if (colonne < 0 || colonne >= largeur() || ligne < 0 || ligne >= hauteur()) return false;
        return codeTuiles[ligne][colonne] == 1||codeTuiles[ligne][colonne] ==2
                ||codeTuiles[ligne][colonne] ==4
                ||codeTuiles[ligne][colonne] ==5
                ||codeTuiles[ligne][colonne] ==6
                ||codeTuiles[ligne][colonne] ==7
                ||codeTuiles[ligne][colonne] ==8;    }

    public boolean estCheminNaturel(int colonne, int ligne){
        if(colonne < 0 || colonne >= largeur() || ligne < 0 || ligne >= hauteur()) {
            return false;
        }
        return codeTuiles[ligne][colonne] == 1;
    }

    public void setCaseBloquee(int x, int y, boolean bloquee){
        if(x >= 0 && x < largeur() && y >= 0 && y < hauteur()) {
            casesBloquees[y][x] = bloquee;
        }
    }
}