package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

import static org.junit.jupiter.api.Assertions.*;

class MonstreTest {

    // Création d'une classe concrète pour pouvoir tester la classe abstraite Monstre
    class MonstreFictif extends Monstre {
        public MonstreFictif(Terrain terrain) {
            super(100, 10, 50, terrain); // pvMax = 100, atq = 10, recompense = 50
        }
    }

    @Test
    void setSpawnEnnemi() {
        // Initialisation propre au test
        Terrain terrain = new Terrain();
        Monstre monstreTest = new MonstreFictif(terrain);

        // Exécution de la méthode à tester
        monstreTest.setSpawnEnnemi(terrain);

        int x = monstreTest.getPosX();
        int y = monstreTest.getPosY();

        // On vérifie que les coordonnées correspondent à l'un des 3 spawns possibles
        boolean estAuSpawn1 = (x == 0 && y == 8 * 32);
        boolean estAuSpawn2 = (x == 24 * 32 && y == 0);
        boolean estAuSpawn3 = (x == 0 && y == 22 * 32);

        assertTrue(estAuSpawn1 || estAuSpawn2 || estAuSpawn3,
                "Le monstre devrait apparaître sur l'un des 3 points de spawn prévus.");
    }

    @Test
    void recalculerItineraire() {
        // Initialisation propre au test
        Terrain terrain = new Terrain();
        Base base = new Base();
        Monstre monstreTest = new MonstreFictif(terrain);

        // On s'assure que le chemin n'est pas encore calculé
        monstreTest.setPosX(0);
        monstreTest.setPosY(8 * 32); // On le place au Spawn 1

        // Exécution
        monstreTest.recalculerItineraire(terrain, base);

        // Vérification
        // Si l'A* a bien fonctionné et trouvé un chemin jusqu'à la base, la méthode aAtteintSaCible()
        // renverra false (car il vient de spawn) et on peut supposer que cheminCalcule est passé à true en interne.
        assertFalse(monstreTest.aAtteintSaCible(),
                "Le monstre vient de recalculer son chemin, il ne devrait pas avoir déjà atteint sa cible.");
    }

    @Test
    void gestionPointsDeVie() {
        Terrain terrain = new Terrain();
        Monstre monstreTest = new MonstreFictif(terrain);

        // Le monstre commence avec 100 PV (définis dans MonstreFictif)
        assertEquals(100, monstreTest.getPV(), "Le monstre doit commencer avec ses PV max (100).");

        // Test 1 : Retrait de PV normal
        monstreTest.retirerPV(30);
        assertEquals(70, monstreTest.getPV(), "Le monstre devrait avoir 70 PV après avoir pris 30 dégâts.");

        // Test 2 : Soin normal
        monstreTest.ajouterPV(10);
        assertEquals(80, monstreTest.getPV(), "Le monstre devrait remonter à 80 PV après un soin de 10.");

        // Test 3 : Soin au-dessus du max (il ne doit pas dépasser pvMax)
        monstreTest.ajouterPV(50);
        assertEquals(100, monstreTest.getPV(), "Le monstre ne peut pas dépasser son maximum de 100 PV.");

        // Test 4 : Dégâts mortels (les PV ne doivent pas descendre sous 0)
        monstreTest.retirerPV(200);
        assertEquals(0, monstreTest.getPV(), "Les PV du monstre ne peuvent pas être négatifs.");
        assertFalse(monstreTest.estVivant(), "Le monstre devrait être considéré mort s'il a 0 PV.");
    }
}