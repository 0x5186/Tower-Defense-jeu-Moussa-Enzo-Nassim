package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    private int[][] codeTuiles = new int[52][120];

    public void terrainPlainesCode() {
        for (int i = 0; i < hauteur(); i++) {
            for (int j = 0; j < largeur(); j++) {
                codeTuiles[i][j] = Math.random() > 0.5 ? 0 : 1;
            }
        }

        tracerLigne(0, 8, 30, 8);
        tracerLigne(30, 8, 30, 26);
        tracerLigne(10, 38, 10, 51);
        tracerLigne(10, 38, 30, 38);
        tracerLigne(30, 26, 30, 38);
        tracerLigne(30, 26, 50, 26);
        tracerLigne(50, 0, 50, 26);
        tracerLigne(50, 26, 65, 26);
        tracerLigne(65, 26, 65, 42);
        tracerLigne(65, 42, 80, 42);
        tracerLigne(80, 12, 80, 42);
        tracerLigne(80, 12, 95, 12);
        tracerLigne(95, 12, 95, 26);
        tracerLigne(95, 26, 119, 26);


        tracerLigne(6, 8, 6, 38);


        tracerLigne(6, 38, 10, 38);

        tracerLigne(30, 16, 50, 16);
        tracerLigne(50, 16, 80, 16);
        tracerLigne(60, 16, 60, 26);
        tracerLigne(30, 38, 30, 46);
        tracerLigne(30, 46, 40, 46);
        tracerLigne(40, 26, 40, 46);
        tracerLigne(65, 26, 80, 26);
        tracerLigne(72, 26, 72, 42);

        tracerLigne(18, 32, 30, 32);
        tracerLigne(18, 32, 18, 43);
        tracerLigne(18, 43, 30, 43);

        tracerLigne(45, 26, 45, 38);
        tracerLigne(40, 38, 45, 38);

        tracerLigne(50, 8, 68, 8);
        tracerLigne(68, 8, 68, 16);

        tracerLigne(88, 26, 88, 36);
        tracerLigne(80, 36, 88, 36);

        tracerLigne(90, 12, 90, 26);
    }
    private void tracerLigne(int col1, int ligne1, int col2, int ligne2) {
        int minX = Math.min(col1, col2);
        int maxX = Math.max(col1, col2);
        int minY = Math.min(ligne1, ligne2);
        int maxY = Math.max(ligne1, ligne2);

        // épaisseur de 2 tuiles)
        for (int i = minY; i <= maxY + 1; i++) {
            for (int j = minX; j <= maxX + 1; j++) {
                if (i >= 0 && i < hauteur() && j >= 0 && j < largeur()) {
                    if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2;
                }
            }
        }

        // Rail invisible pour l'algorithme A*
        if (minY == maxY) {
            for (int j = minX; j <= maxX + 1; j++) {
                if (minY >= 0 && minY < hauteur() && j >= 0 && j < largeur()) {
                    codeTuiles[minY][j] = 3;
                }
            }
        } else if (minX == maxX) {
            for (int i = minY; i <= maxY + 1; i++) {
                if (i >= 0 && i < hauteur() && minX >= 0 && minX < largeur()) {
                    codeTuiles[i][minX] = 3;
                }
            }
        }
    }

    public int hauteur() { return this.codeTuiles.length; }
    public int largeur() { return this.codeTuiles[0].length; }

    public int codeTuile(int ligne, int col) {
        int val = codeTuiles[ligne][col];
        if (val == 3) return 2;
        return val;
    }

    public void test() {
        for (int i = 0; i < hauteur(); i++) {
            for (int j = 0; j < largeur(); j++) { codeTuiles[i][j] = 0; }
        }
    }

    public boolean estPraticable(int x, int y) {
        if (x < 0 || x >= largeur() || y < 0 || y >= hauteur()) return false;
        return codeTuiles[y][x] == 3;
    }
}