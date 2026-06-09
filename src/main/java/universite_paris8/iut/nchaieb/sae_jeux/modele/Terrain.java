package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    private int[][] codeTuiles;

    public Terrain() {
        codeTuiles = new int[25][60];

        // Ligne du haut (Spawn 1)
        for (int x = 0; x <= 45; x++) codeTuiles[8][x] = 1;
        for (int x = 0; x <= 45; x++) codeTuiles[7][x] = 1;
        // Ligne du milieu
        for (int x = 10; x <= 45; x++) codeTuiles[14][x] = 1;
        for (int x = 10; x <= 45; x++) codeTuiles[15][x] = 1;
        // Ligne du bas (spawn 3)
        for (int x = 0; x <= 10; x++) codeTuiles[21][x] = 1;
        for (int x = 0; x <= 10; x++) codeTuiles[22][x] = 1;
        // Ligne finale vers la base
        for (int x = 45; x <= 59; x++) codeTuiles[11][x] = 1;
        for (int x = 45; x <= 59; x++) codeTuiles[12][x] = 1;


        // Descente du Spawn 2
        for (int y = 0; y <= 15; y++) codeTuiles[y][24] = 1;
        for (int y = 0; y <= 15; y++) codeTuiles[y][25] = 1;

        // Remontée depuis le trait noir du bas
        for (int y = 15; y <= 22; y++) codeTuiles[y][11] = 1;
        for (int y = 15; y <= 22; y++) codeTuiles[y][10] = 1;

        // Descente vers la base
        for (int y = 8; y <= 14; y++) codeTuiles[y][44] = 1;
        for (int y = 8; y <= 14; y++) codeTuiles[y][45] = 1;

    }

    public int hauteur() { return codeTuiles.length; }
    public int largeur() { return codeTuiles[0].length; }
    public int codeTuile(int ligne, int col) { return codeTuiles[ligne][col]; }

    public boolean estPraticable(int x, int y) {
        if (x < 0 || x >= largeur() || y < 0 || y >= hauteur()) return false;
        return codeTuiles[y][x] == 1;
    }
}