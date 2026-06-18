package universite_paris8.iut.nchaieb.sae_jeux.modele.AEtoile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

// Note : Assurez-vous d'importer votre classe Terrain si elle est dans un autre package
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

class AlgorithmeAEtoileTest {
    class TerrainFaux extends Terrain {
        private boolean[][] obstacles = new boolean[50][50];
        public void poserMur(int x, int y) {
            if (x >= 0 && x < 50 && y >= 0 && y < 50) {
                obstacles[x][y] = true;
            }
        }

        @Override
        public boolean estPraticable(int x, int y) {
            if (x < 0 || x >= 50 || y < 0 || y >= 50) {
                return false;
            }
            return !obstacles[x][y];
        }
    }

    @Test
    void testTrouverChemin_DepartIdentiqueCible() {
        TerrainFaux terrain = new TerrainFaux();
        int departX = 5, departY = 5;
        ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(terrain, departX, departY, departX, departY);
        assertNotNull(chemin, "Le chemin ne doit pas être null");
        assertEquals(1, chemin.size(), "Le chemin doit contenir 1 seul nœud");
        assertEquals(departX, chemin.get(0).x, "Le X doit être celui de départ");
        assertEquals(departY, chemin.get(0).y, "Le Y doit être celui de départ");
    }

    @Test
    void testTrouverChemin_LigneDroiteSansObstacle() {
        TerrainFaux terrain = new TerrainFaux();
        ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(terrain, 0, 0, 0, 3);
        assertNotNull(chemin, "Un chemin doit être trouvé obligatoirement");
        assertTrue(chemin.size() > 1, "Le chemin doit avancer de plusieurs cases");
        Noeud dernierNoeud = chemin.get(chemin.size() - 1);
        assertEquals(0, dernierNoeud.x, "Le X doit être atteint");
        assertEquals(3, dernierNoeud.y, "Le Y doit être atteint");
    }

    @Test
    void testTrouverChemin_AvecObstacleSimple() {
        TerrainFaux terrain = new TerrainFaux();
        terrain.poserMur(1, 0);
        ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(terrain, 0, 0, 2, 0);
        assertNotNull(chemin, "Un chemin de contournement doit être trouvé");
        for (Noeud noeud : chemin) {
            boolean estSurLeMur = (noeud.x == 1 && noeud.y == 0);
            assertFalse(estSurLeMur, "Le chemin ne doit pas passer par l'obstacle (1,0)");
        }
        Noeud dernierNoeud = chemin.get(chemin.size() - 1);
        assertEquals(2, dernierNoeud.x, "La cible X doit être atteinte");
        assertEquals(0, dernierNoeud.y, "La cible Y doit être atteinte");
    }

    @Test
    void testTrouverChemin_CibleCompletementBloquee() {
        TerrainFaux terrain = new TerrainFaux();
        terrain.poserMur(2, 1);
        terrain.poserMur(2, 3);
        terrain.poserMur(1, 2);
        terrain.poserMur(3, 2);
        ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(terrain, 0, 0, 2, 2);
        assertTrue(chemin == null || chemin.isEmpty(), "Aucun chemin est trouvé car la cible est inaccessible");
    }

    @Test
    void testTrouverChemin_HorsLimites() {
        TerrainFaux terrain = new TerrainFaux();
        ArrayList<Noeud> chemin = AlgorithmeAEtoile.trouverChemin(terrain, 0, 0, 100, 100);

        assertTrue(chemin == null || chemin.isEmpty(), "L'algorithme doit échouer au vue du fait que l'on est hors limite");
    }
}