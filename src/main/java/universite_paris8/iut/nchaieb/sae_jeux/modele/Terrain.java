package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    private int[][] codeTuiles = new int[52][120];

    public void terrainPlainesCode() {
        for (int i = 0; i < hauteur(); i++) {
            for (int j = 0; j < largeur(); j++) {
                codeTuiles[i][j] = Math.random() > 0.5 ? 0 : 1;
            }
        }

        // tracerLigne(0, 8, 30, 8);
        for (int i = 8; i <= 9; i++) { for (int j = 0; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 0; j <= 31; j++) { codeTuiles[8][j] = 3; }

        // tracerLigne(30, 8, 30, 26);
        for (int i = 8; i <= 27; i++) { for (int j = 30; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 8; i <= 27; i++) { codeTuiles[i][30] = 3; }

        // tracerLigne(10, 38, 10, 51);
        for (int i = 38; i <= 51; i++) { for (int j = 10; j <= 11; j++) { if (i < hauteur()) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } } }
        for (int i = 38; i <= 51; i++) { if (i < hauteur()) codeTuiles[i][10] = 3; }

        // tracerLigne(10, 38, 30, 38);
        for (int i = 38; i <= 39; i++) { for (int j = 10; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 10; j <= 31; j++) { codeTuiles[38][j] = 3; }

        // tracerLigne(30, 26, 30, 38);
        for (int i = 26; i <= 39; i++) { for (int j = 30; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 39; i++) { codeTuiles[i][30] = 3; }

        // tracerLigne(30, 26, 50, 26);
        for (int i = 26; i <= 27; i++) { for (int j = 30; j <= 51; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 30; j <= 51; j++) { codeTuiles[26][j] = 3; }

        // tracerLigne(50, 0, 50, 26);
        for (int i = 0; i <= 27; i++) { for (int j = 50; j <= 51; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 0; i <= 27; i++) { codeTuiles[i][50] = 3; }

        // tracerLigne(50, 26, 65, 26);
        for (int i = 26; i <= 27; i++) { for (int j = 50; j <= 66; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 50; j <= 66; j++) { codeTuiles[26][j] = 3; }

        // tracerLigne(65, 26, 65, 42);
        for (int i = 26; i <= 43; i++) { for (int j = 65; j <= 66; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 43; i++) { codeTuiles[i][65] = 3; }

        // tracerLigne(65, 42, 80, 42);
        for (int i = 42; i <= 43; i++) { for (int j = 65; j <= 81; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 65; j <= 81; j++) { codeTuiles[42][j] = 3; }

        // tracerLigne(80, 12, 80, 42);
        for (int i = 12; i <= 43; i++) { for (int j = 80; j <= 81; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 12; i <= 43; i++) { codeTuiles[i][80] = 3; }

        // tracerLigne(80, 12, 95, 12);
        for (int i = 12; i <= 13; i++) { for (int j = 80; j <= 96; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 80; j <= 96; j++) { codeTuiles[12][j] = 3; }

        // tracerLigne(95, 12, 95, 26);
        for (int i = 12; i <= 27; i++) { for (int j = 95; j <= 96; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 12; i <= 27; i++) { codeTuiles[i][95] = 3; }

        // tracerLigne(95, 26, 119, 26);
        for (int i = 26; i <= 27; i++) { for (int j = 95; j <= 119; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 95; j <= 119; j++) { codeTuiles[26][j] = 3; }

        // tracerLigne(6, 8, 6, 38);
        for (int i = 8; i <= 39; i++) { for (int j = 6; j <= 7; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 8; i <= 39; i++) { codeTuiles[i][6] = 3; }

        // tracerLigne(6, 38, 10, 38);
        for (int i = 38; i <= 39; i++) { for (int j = 6; j <= 11; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 6; j <= 11; j++) { codeTuiles[38][j] = 3; }

        // tracerLigne(30, 16, 50, 16);
        for (int i = 16; i <= 17; i++) { for (int j = 30; j <= 51; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 30; j <= 51; j++) { codeTuiles[16][j] = 3; }

        // tracerLigne(50, 16, 80, 16);
        for (int i = 16; i <= 17; i++) { for (int j = 50; j <= 81; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 50; j <= 81; j++) { codeTuiles[16][j] = 3; }

        // tracerLigne(60, 16, 60, 26);
        for (int i = 16; i <= 27; i++) { for (int j = 60; j <= 61; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 16; i <= 27; i++) { codeTuiles[i][60] = 3; }

        // tracerLigne(30, 38, 30, 46);
        for (int i = 38; i <= 47; i++) { for (int j = 30; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 38; i <= 47; i++) { codeTuiles[i][30] = 3; }

        // tracerLigne(30, 46, 40, 46);
        for (int i = 46; i <= 47; i++) { for (int j = 30; j <= 41; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 30; j <= 41; j++) { codeTuiles[46][j] = 3; }

        // tracerLigne(40, 26, 40, 46);
        for (int i = 26; i <= 47; i++) { for (int j = 40; j <= 41; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 47; i++) { codeTuiles[i][40] = 3; }

        // tracerLigne(65, 26, 80, 26);
        for (int i = 26; i <= 27; i++) { for (int j = 65; j <= 81; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 65; j <= 81; j++) { codeTuiles[26][j] = 3; }

        // tracerLigne(72, 26, 72, 42);
        for (int i = 26; i <= 43; i++) { for (int j = 72; j <= 73; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 43; i++) { codeTuiles[i][72] = 3; }

        // tracerLigne(18, 32, 30, 32);
        for (int i = 32; i <= 33; i++) { for (int j = 18; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 18; j <= 31; j++) { codeTuiles[32][j] = 3; }

        // tracerLigne(18, 32, 18, 43);
        for (int i = 32; i <= 44; i++) { for (int j = 18; j <= 19; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 32; i <= 44; i++) { codeTuiles[i][18] = 3; }

        // tracerLigne(18, 43, 30, 43);
        for (int i = 43; i <= 44; i++) { for (int j = 18; j <= 31; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 18; j <= 31; j++) { codeTuiles[43][j] = 3; }

        // tracerLigne(45, 26, 45, 38);
        for (int i = 26; i <= 39; i++) { for (int j = 45; j <= 46; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 39; i++) { codeTuiles[i][45] = 3; }

        // tracerLigne(40, 38, 45, 38);
        for (int i = 38; i <= 39; i++) { for (int j = 40; j <= 46; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 40; j <= 46; j++) { codeTuiles[38][j] = 3; }

        // tracerLigne(50, 8, 68, 8);
        for (int i = 8; i <= 9; i++) { for (int j = 50; j <= 69; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 50; j <= 69; j++) { codeTuiles[8][j] = 3; }

        // tracerLigne(68, 8, 68, 16);
        for (int i = 8; i <= 17; i++) { for (int j = 68; j <= 69; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 8; i <= 17; i++) { codeTuiles[i][68] = 3; }

        // tracerLigne(88, 26, 88, 36);
        for (int i = 26; i <= 37; i++) { for (int j = 88; j <= 89; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 26; i <= 37; i++) { codeTuiles[i][88] = 3; }

        // tracerLigne(80, 36, 88, 36);
        for (int i = 36; i <= 37; i++) { for (int j = 80; j <= 89; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int j = 80; j <= 89; j++) { codeTuiles[36][j] = 3; }

        // tracerLigne(90, 12, 90, 26);
        for (int i = 12; i <= 27; i++) { for (int j = 90; j <= 91; j++) { if (codeTuiles[i][j] < 2) codeTuiles[i][j] = 2; } }
        for (int i = 12; i <= 27; i++) { codeTuiles[i][90] = 3; }
    }

    private void tracerLigne(int col1, int ligne1, int col2, int ligne2) {
        // La méthode originale peut être conservée ou supprimée selon tes besoins.
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