package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    private int[][] codeTuiles;
    private boolean[][] casesBloquees;

    public Terrain() {
        codeTuiles = new int[25][60];
        casesBloquees = new boolean[25][60];

        // Ligne du haut (Spawn 1)
        for (int colonne = 0; colonne <= 45; colonne++) codeTuiles[8][colonne] = 2;
        for (int colonne = 0; colonne <= 45; colonne++) codeTuiles[7][colonne] = 1;
        // Ligne du milieu
        for (int colonne = 10; colonne <= 45; colonne++) codeTuiles[14][colonne] = 1;
        for (int colonne = 10; colonne <= 45; colonne++) codeTuiles[15][colonne] = 2;
        // Ligne du bas (spawn 3)
        for (int colonne = 0; colonne <= 10; colonne++) codeTuiles[21][colonne] = 1;
        for (int colonne = 0; colonne <= 10; colonne++) codeTuiles[22][colonne] = 2;
        // Ligne finale vers la base
        for (int colonne = 45; colonne <= 59; colonne++) codeTuiles[11][colonne] = 1;
        for (int colonne = 45; colonne <= 59; colonne++) codeTuiles[12][colonne] = 2;

        for (int colonne = 0; colonne <= 51; colonne++) codeTuiles[21][colonne] = 1;
        for (int colonne = 0; colonne <= 51; colonne++) codeTuiles[22][colonne] = 2;

        // Descente du Spawn 2
        for (int ligne = 0; ligne <= 15; ligne++) codeTuiles[ligne][24] = 8;
        for (int ligne = 0; ligne <= 15; ligne++) codeTuiles[ligne][25] = 7;

        // Remontée depuis le trait noir du bas
        for (int ligne = 8; ligne <= 15; ligne++) codeTuiles[ligne][11] = 7;
        for (int ligne = 8; ligne <= 15; ligne++) codeTuiles[ligne][10] = 8;

        // Descente vers la base
        for (int ligne = 8; ligne <= 14; ligne++) codeTuiles[ligne][44] = 8;
        for (int ligne = 8; ligne <= 14; ligne++) codeTuiles[ligne][45] = 7;

        for (int ligne = 11; ligne <= 22; ligne++) codeTuiles[ligne][50] = 8;
        for (int ligne = 11; ligne <= 22; ligne++) codeTuiles[ligne][51] = 7;

        codeTuiles[7][24] = 3;
        codeTuiles[7][25] = 4;
        codeTuiles[8][24] = 5;
        codeTuiles[8][25] = 6;
    }

    public int hauteur() { return codeTuiles.length; }
    public int largeur() { return codeTuiles[0].length; }
    public int codeTuile(int ligne, int col) { return codeTuiles[ligne][col]; }

    public boolean estCheminNaturel(int colonne, int ligne) {
        if (colonne < 0 || colonne >= largeur() || ligne < 0 || ligne >= hauteur()) return false;
        if (this.casesBloquees[ligne][colonne]) return false;
        int tuile = codeTuiles[ligne][colonne];
        return tuile == 1 || tuile == 2 || tuile == 4 || tuile == 5 || tuile == 6 || tuile == 7 || tuile == 8;
    }

    public boolean estPraticable(int colonne, int ligne) {
        if (colonne < 0 || colonne >= largeur() || ligne < 0 || ligne >= hauteur()) return false;
        if (casesBloquees[ligne][colonne]) return false;
        return estCheminNaturel(colonne, ligne);
    }

    public void setCaseBloquee(int colonne, int ligne, boolean bloquee) {
        if (colonne >= 0 && colonne < largeur() && ligne >= 0 && ligne < hauteur()) {
            casesBloquees[ligne][colonne] = bloquee;
        }
    }
}