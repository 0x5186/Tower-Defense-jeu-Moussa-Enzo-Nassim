package universite_paris8.iut.nchaieb.sae_jeux.modele.monstres;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Base.Base;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Terrain;

import static org.junit.jupiter.api.Assertions.*;

class MonstreTest {

    class MonstreFictif extends Monstre {
        public MonstreFictif(Terrain terrain) {
            super(100, 10, 50, terrain);
        }
    }

    @Test
    void setSpawnEnnemi() {
        Terrain terrain = new Terrain();
        Monstre monstreTest = new MonstreFictif(terrain);

        monstreTest.setSpawnEnnemi(terrain);

        int x = monstreTest.getPosX();
        int y = monstreTest.getPosY();

        boolean estAuSpawn1 = (x == 0 && y == 8 * 32);
        boolean estAuSpawn2 = (x == 24 * 32 && y == 0);
        boolean estAuSpawn3 = (x == 0 && y == 22 * 32);

        assertTrue(estAuSpawn1 || estAuSpawn2 || estAuSpawn3,
                "Le monstre devrait apparaître sur l'un des 3 points de spawn prévus.");
    }

    @Test
    void recalculerItineraire() {
        Terrain terrain = new Terrain();
        Base base = new Base();
        Monstre monstreTest = new MonstreFictif(terrain);

        monstreTest.setPosX(0);
        monstreTest.setPosY(8 * 32);

        monstreTest.recalculerItineraire(terrain, base);
        assertFalse(monstreTest.aAtteintSaCible(),
                "Le monstre vient de recalculer son chemin, il ne devrait pas avoir déjà atteint sa cible.");
    }

    @Test
    void gestionPointsDeVie() {
        Terrain terrain = new Terrain();
        Monstre monstreTest = new MonstreFictif(terrain);
        assertEquals(100, monstreTest.getPV(), "Le monstre doit commencer avec ses PV max.");
        monstreTest.retirerPV(30);
        assertEquals(70, monstreTest.getPV(), "Le monstre devrait avoir 70 PV après avoir pris 30 dégâts.");
        monstreTest.ajouterPV(10);
        assertEquals(80, monstreTest.getPV(), "Le monstre devrait remonter à 80 PV après un soin de 10 PV.");
        monstreTest.ajouterPV(50);
        assertEquals(100, monstreTest.getPV(), "Le monstre ne peut pas dépasser son maximum de 100 PV.");
        monstreTest.retirerPV(200);
        assertEquals(0, monstreTest.getPV(), "Les PV du monstre ne peuvent pas être négatifs.");
        assertFalse(monstreTest.estVivant(), "Le monstre doit être considéré mort s'il a 0 PV.");
    }
}