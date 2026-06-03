package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Terrain {
    // Dimensions adaptées pour des tuiles de 24px (80x35)
    private int[][] codeTuiles = new int[35][80];

    public void terrainPlainesCode() {
        // --- tracerLigne(0, 5, 20, 5) [Anciennement (0, 8, 30, 8)] ---
        codeTuiles[5][0]=3; codeTuiles[5][1]=3; codeTuiles[5][2]=3; codeTuiles[5][3]=3; codeTuiles[5][4]=3; codeTuiles[5][5]=3; codeTuiles[5][6]=3; codeTuiles[5][7]=3; codeTuiles[5][8]=3; codeTuiles[5][9]=3; codeTuiles[5][10]=3; codeTuiles[5][11]=3; codeTuiles[5][12]=3; codeTuiles[5][13]=3; codeTuiles[5][14]=3; codeTuiles[5][15]=3; codeTuiles[5][16]=3; codeTuiles[5][17]=3; codeTuiles[5][18]=3; codeTuiles[5][19]=3; codeTuiles[5][20]=3;
        codeTuiles[6][0]=2; codeTuiles[6][1]=2; codeTuiles[6][2]=2; codeTuiles[6][3]=2; codeTuiles[6][4]=2; codeTuiles[6][5]=2; codeTuiles[6][6]=2; codeTuiles[6][7]=2; codeTuiles[6][8]=2; codeTuiles[6][9]=2; codeTuiles[6][10]=2; codeTuiles[6][11]=2; codeTuiles[6][12]=2; codeTuiles[6][13]=2; codeTuiles[6][14]=2; codeTuiles[6][15]=2; codeTuiles[6][16]=2; codeTuiles[6][17]=2; codeTuiles[6][18]=2; codeTuiles[6][19]=2; codeTuiles[6][20]=2;

        // --- tracerLigne(20, 5, 20, 17) [Anciennement (30, 8, 30, 26)] ---
        codeTuiles[5][20]=3; codeTuiles[5][21]=2; codeTuiles[6][20]=3; codeTuiles[6][21]=2; codeTuiles[7][20]=3; codeTuiles[7][21]=2; codeTuiles[8][20]=3; codeTuiles[8][21]=2; codeTuiles[9][20]=3; codeTuiles[9][21]=2; codeTuiles[10][20]=3; codeTuiles[10][21]=2; codeTuiles[11][20]=3; codeTuiles[11][21]=2; codeTuiles[12][20]=3; codeTuiles[12][21]=2; codeTuiles[13][20]=3; codeTuiles[13][21]=2; codeTuiles[14][20]=3; codeTuiles[14][21]=2; codeTuiles[15][20]=3; codeTuiles[15][21]=2; codeTuiles[16][20]=3; codeTuiles[16][21]=2; codeTuiles[17][20]=3; codeTuiles[17][21]=2;

        // --- tracerLigne(7, 25, 7, 34) [Anciennement (10, 38, 10, 51)] ---
        codeTuiles[25][7]=3; codeTuiles[25][8]=2; codeTuiles[26][7]=3; codeTuiles[26][8]=2; codeTuiles[27][7]=3; codeTuiles[27][8]=2; codeTuiles[28][7]=3; codeTuiles[28][8]=2; codeTuiles[29][7]=3; codeTuiles[29][8]=2; codeTuiles[30][7]=3; codeTuiles[30][8]=2; codeTuiles[31][7]=3; codeTuiles[31][8]=2; codeTuiles[32][7]=3; codeTuiles[32][8]=2; codeTuiles[33][7]=3; codeTuiles[33][8]=2; codeTuiles[34][7]=3; codeTuiles[34][8]=2;

        // --- tracerLigne(7, 25, 20, 25) [Anciennement (10, 38, 30, 38)] ---
        codeTuiles[25][7]=3; codeTuiles[25][8]=3; codeTuiles[25][9]=3; codeTuiles[25][10]=3; codeTuiles[25][11]=3; codeTuiles[25][12]=3; codeTuiles[25][13]=3; codeTuiles[25][14]=3; codeTuiles[25][15]=3; codeTuiles[25][16]=3; codeTuiles[25][17]=3; codeTuiles[25][18]=3; codeTuiles[25][19]=3; codeTuiles[25][20]=3;
        codeTuiles[26][7]=2; codeTuiles[26][8]=2; codeTuiles[26][9]=2; codeTuiles[26][10]=2; codeTuiles[26][11]=2; codeTuiles[26][12]=2; codeTuiles[26][13]=2; codeTuiles[26][14]=2; codeTuiles[26][15]=2; codeTuiles[26][16]=2; codeTuiles[26][17]=2; codeTuiles[26][18]=2; codeTuiles[26][19]=2; codeTuiles[26][20]=2;

        // --- tracerLigne(20, 17, 20, 25) [Anciennement (30, 26, 30, 38)] ---
        codeTuiles[17][20]=3; codeTuiles[17][21]=2; codeTuiles[18][20]=3; codeTuiles[18][21]=2; codeTuiles[19][20]=3; codeTuiles[19][21]=2; codeTuiles[20][20]=3; codeTuiles[20][21]=2; codeTuiles[21][20]=3; codeTuiles[21][21]=2; codeTuiles[22][20]=3; codeTuiles[22][21]=2; codeTuiles[23][20]=3; codeTuiles[23][21]=2; codeTuiles[24][20]=3; codeTuiles[24][21]=2; codeTuiles[25][20]=3; codeTuiles[25][21]=2;

        // --- tracerLigne(20, 17, 33, 17) [Anciennement (30, 26, 50, 26)] ---
        codeTuiles[17][20]=3; codeTuiles[17][21]=3; codeTuiles[17][22]=3; codeTuiles[17][23]=3; codeTuiles[17][24]=3; codeTuiles[17][25]=3; codeTuiles[17][26]=3; codeTuiles[17][27]=3; codeTuiles[17][28]=3; codeTuiles[17][29]=3; codeTuiles[17][30]=3; codeTuiles[17][31]=3; codeTuiles[17][32]=3; codeTuiles[17][33]=3;
        codeTuiles[18][20]=2; codeTuiles[18][21]=2; codeTuiles[18][22]=2; codeTuiles[18][23]=2; codeTuiles[18][24]=2; codeTuiles[18][25]=2; codeTuiles[18][26]=2; codeTuiles[18][27]=2; codeTuiles[18][28]=2; codeTuiles[18][29]=2; codeTuiles[18][30]=2; codeTuiles[18][31]=2; codeTuiles[18][32]=2; codeTuiles[18][33]=2;

        // --- tracerLigne(33, 0, 33, 17) [Anciennement (50, 0, 50, 26)] ---
        codeTuiles[0][33]=3; codeTuiles[0][34]=2; codeTuiles[1][33]=3; codeTuiles[1][34]=2; codeTuiles[2][33]=3; codeTuiles[2][34]=2; codeTuiles[3][33]=3; codeTuiles[3][34]=2; codeTuiles[4][33]=3; codeTuiles[4][34]=2; codeTuiles[5][33]=3; codeTuiles[5][34]=2; codeTuiles[6][33]=3; codeTuiles[6][34]=2; codeTuiles[7][33]=3; codeTuiles[7][34]=2; codeTuiles[8][33]=3; codeTuiles[8][34]=2; codeTuiles[9][33]=3; codeTuiles[9][34]=2; codeTuiles[10][33]=3; codeTuiles[10][34]=2; codeTuiles[11][33]=3; codeTuiles[11][34]=2; codeTuiles[12][33]=3; codeTuiles[12][34]=2; codeTuiles[13][33]=3; codeTuiles[13][34]=2; codeTuiles[14][33]=3; codeTuiles[14][34]=2; codeTuiles[15][33]=3; codeTuiles[15][34]=2; codeTuiles[16][33]=3; codeTuiles[16][34]=2; codeTuiles[17][33]=3; codeTuiles[17][34]=2;

        // --- tracerLigne(33, 17, 43, 17) [Anciennement (50, 26, 65, 26)] ---
        codeTuiles[17][33]=3; codeTuiles[17][34]=3; codeTuiles[17][35]=3; codeTuiles[17][36]=3; codeTuiles[17][37]=3; codeTuiles[17][38]=3; codeTuiles[17][39]=3; codeTuiles[17][40]=3; codeTuiles[17][41]=3; codeTuiles[17][42]=3; codeTuiles[17][43]=3;
        codeTuiles[18][33]=2; codeTuiles[18][34]=2; codeTuiles[18][35]=2; codeTuiles[18][36]=2; codeTuiles[18][37]=2; codeTuiles[18][38]=2; codeTuiles[18][39]=2; codeTuiles[18][40]=2; codeTuiles[18][41]=2; codeTuiles[18][42]=2; codeTuiles[18][43]=2;

        // --- tracerLigne(43, 17, 43, 28) [Anciennement (65, 26, 65, 42)] ---
        codeTuiles[17][43]=3; codeTuiles[17][44]=2; codeTuiles[18][43]=3; codeTuiles[18][44]=2; codeTuiles[19][43]=3; codeTuiles[19][44]=2; codeTuiles[20][43]=3; codeTuiles[20][44]=2; codeTuiles[21][43]=3; codeTuiles[21][44]=2; codeTuiles[22][43]=3; codeTuiles[22][44]=2; codeTuiles[23][43]=3; codeTuiles[23][44]=2; codeTuiles[24][43]=3; codeTuiles[24][44]=2; codeTuiles[25][43]=3; codeTuiles[25][44]=2; codeTuiles[26][43]=3; codeTuiles[26][44]=2; codeTuiles[27][43]=3; codeTuiles[27][44]=2; codeTuiles[28][43]=3; codeTuiles[28][44]=2;

        // --- tracerLigne(43, 28, 53, 28) [Anciennement (65, 42, 80, 42)] ---
        codeTuiles[28][43]=3; codeTuiles[28][44]=3; codeTuiles[28][45]=3; codeTuiles[28][46]=3; codeTuiles[28][47]=3; codeTuiles[28][48]=3; codeTuiles[28][49]=3; codeTuiles[28][50]=3; codeTuiles[28][51]=3; codeTuiles[28][52]=3; codeTuiles[28][53]=3;
        codeTuiles[29][43]=2; codeTuiles[29][44]=2; codeTuiles[29][45]=2; codeTuiles[29][46]=2; codeTuiles[29][47]=2; codeTuiles[29][48]=2; codeTuiles[29][49]=2; codeTuiles[29][50]=2; codeTuiles[29][51]=2; codeTuiles[29][52]=2; codeTuiles[29][53]=2;

        // --- tracerLigne(53, 8, 53, 28) [Anciennement (80, 12, 80, 42)] ---
        codeTuiles[8][53]=3; codeTuiles[8][54]=2; codeTuiles[9][53]=3; codeTuiles[9][54]=2; codeTuiles[10][53]=3; codeTuiles[10][54]=2; codeTuiles[11][53]=3; codeTuiles[11][54]=2; codeTuiles[12][53]=3; codeTuiles[12][54]=2; codeTuiles[13][53]=3; codeTuiles[13][54]=2; codeTuiles[14][53]=3; codeTuiles[14][54]=2; codeTuiles[15][53]=3; codeTuiles[15][54]=2; codeTuiles[16][53]=3; codeTuiles[16][54]=2; codeTuiles[17][53]=3; codeTuiles[17][54]=2; codeTuiles[18][53]=3; codeTuiles[18][54]=2; codeTuiles[19][53]=3; codeTuiles[19][54]=2; codeTuiles[20][53]=3; codeTuiles[20][54]=2; codeTuiles[21][53]=3; codeTuiles[21][54]=2; codeTuiles[22][53]=3; codeTuiles[22][54]=2; codeTuiles[23][53]=3; codeTuiles[23][54]=2; codeTuiles[24][53]=3; codeTuiles[24][54]=2; codeTuiles[25][53]=3; codeTuiles[25][54]=2; codeTuiles[26][53]=3; codeTuiles[26][54]=2; codeTuiles[27][53]=3; codeTuiles[27][54]=2; codeTuiles[28][53]=3; codeTuiles[28][54]=2;

        // --- tracerLigne(53, 8, 63, 8) [Anciennement (80, 12, 95, 12)] ---
        codeTuiles[8][53]=3; codeTuiles[8][54]=3; codeTuiles[8][55]=3; codeTuiles[8][56]=3; codeTuiles[8][57]=3; codeTuiles[8][58]=3; codeTuiles[8][59]=3; codeTuiles[8][60]=3; codeTuiles[8][61]=3; codeTuiles[8][62]=3; codeTuiles[8][63]=3;
        codeTuiles[9][53]=2; codeTuiles[9][54]=2; codeTuiles[9][55]=2; codeTuiles[9][56]=2; codeTuiles[9][57]=2; codeTuiles[9][58]=2; codeTuiles[9][59]=2; codeTuiles[9][60]=2; codeTuiles[9][61]=2; codeTuiles[9][62]=2; codeTuiles[9][63]=2;

        // --- tracerLigne(63, 8, 63, 17) [Anciennement (95, 12, 95, 26)] ---
        codeTuiles[8][63]=3; codeTuiles[8][64]=2; codeTuiles[9][63]=3; codeTuiles[9][64]=2; codeTuiles[10][63]=3; codeTuiles[10][64]=2; codeTuiles[11][63]=3; codeTuiles[11][64]=2; codeTuiles[12][63]=3; codeTuiles[12][64]=2; codeTuiles[13][63]=3; codeTuiles[13][64]=2; codeTuiles[14][63]=3; codeTuiles[14][64]=2; codeTuiles[15][63]=3; codeTuiles[15][64]=2; codeTuiles[16][63]=3; codeTuiles[16][64]=2; codeTuiles[17][63]=3; codeTuiles[17][64]=2;

        // --- tracerLigne(63, 17, 79, 17) [Anciennement (95, 26, 119, 26)] ---
        codeTuiles[17][63]=3; codeTuiles[17][64]=3; codeTuiles[17][65]=3; codeTuiles[17][66]=3; codeTuiles[17][67]=3; codeTuiles[17][68]=3; codeTuiles[17][69]=3; codeTuiles[17][70]=3; codeTuiles[17][71]=3; codeTuiles[17][72]=3; codeTuiles[17][73]=3; codeTuiles[17][74]=3; codeTuiles[17][75]=3; codeTuiles[17][76]=3; codeTuiles[17][77]=3; codeTuiles[17][78]=3; codeTuiles[17][79]=3;
        codeTuiles[18][63]=2; codeTuiles[18][64]=2; codeTuiles[18][65]=2; codeTuiles[18][66]=2; codeTuiles[18][67]=2; codeTuiles[18][68]=2; codeTuiles[18][69]=2; codeTuiles[18][70]=2; codeTuiles[18][71]=2; codeTuiles[18][72]=2; codeTuiles[18][73]=2; codeTuiles[18][74]=2; codeTuiles[18][75]=2; codeTuiles[18][76]=2; codeTuiles[18][77]=2; codeTuiles[18][78]=2; codeTuiles[18][79]=2;
    }

    public int hauteur() { return this.codeTuiles.length; }
    public int largeur() { return this.codeTuiles[0].length; }

    public int codeTuile(int ligne, int col) {
        int val = codeTuiles[ligne][col];
        if (val == 3) return 2;
        return val;
    }

    public boolean estPraticable(int x, int y) {
        if (x < 0 || x >= largeur() || y < 0 || y >= hauteur()) return false;
        return codeTuiles[y][x] == 3;
    }
}