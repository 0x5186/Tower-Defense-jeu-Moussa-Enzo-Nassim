package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public class AlgorithmeAEtoile {

    public static ArrayList<Noeud> trouverChemin(Terrain terrain, int departX, int departY, int cibleX, int cibleY) {
        ArrayList<Noeud> listeOuverte = new ArrayList<>();
        ArrayList<Noeud> listeFermee = new ArrayList<>();

        Noeud noeudDepart = new Noeud(departX, departY);
        listeOuverte.add(noeudDepart);

        while (listeOuverte.size() > 0) {

            Noeud noeudActuel = listeOuverte.get(0);
            int indexActuel = 0;

            for (int i = 1; i < listeOuverte.size(); i++) {
                if (listeOuverte.get(i).coutF() < noeudActuel.coutF()) {
                    noeudActuel = listeOuverte.get(i);
                    indexActuel = i;
                }
            }

            listeOuverte.remove(indexActuel);
            listeFermee.add(noeudActuel);

            // Si on a atteint la destination
            if (noeudActuel.estMemePosition(cibleX, cibleY)) {
                return reconstruireChemin(noeudActuel);
            }

            int[][] directions = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

            for (int i = 0; i < directions.length; i++) {
                int voisinX = noeudActuel.x + directions[i][0];
                int voisinY = noeudActuel.y + directions[i][1];

                if (!terrain.estPraticable(voisinX, voisinY)) {
                    continue;
                }

                if (estDansListe(listeFermee, voisinX, voisinY)) {
                    continue;
                }

                int nouveauCoutG = noeudActuel.coutD + 10;
                Noeud voisin = trouverDansListe(listeOuverte, voisinX, voisinY);

                if (voisin == null) {
                    voisin = new Noeud(voisinX, voisinY);
                    voisin.parent = noeudActuel;
                    voisin.coutD = nouveauCoutG;
                    voisin.coutA = calculerHeuristique(voisinX, voisinY, cibleX, cibleY);
                    listeOuverte.add(voisin);
                }
                else if (nouveauCoutG < voisin.coutD) {
                    voisin.parent = noeudActuel;
                    voisin.coutD = nouveauCoutG;
                }
            }
        }

        return new ArrayList<>();
    }

    private static boolean estDansListe(ArrayList<Noeud> liste, int x, int y) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).estMemePosition(x, y)) {
                return true;
            }
        }
        return false;
    }

    private static Noeud trouverDansListe(ArrayList<Noeud> liste, int x, int y) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).estMemePosition(x, y)) {
                return liste.get(i);
            }
        }
        return null;
    }

    private static int calculerHeuristique(int x1, int y1, int x2, int y2) {
        int distX = x1 - x2;
        int distY = y1 - y2;
        if (distX < 0) distX = -distX;
        if (distY < 0) distY = -distY;
        return (distX + distY) * 10;
    }

    private static ArrayList<Noeud> reconstruireChemin(Noeud noeudCible) {
        ArrayList<Noeud> cheminInverse = new ArrayList<>();
        Noeud actuel = noeudCible;

        while (actuel != null) {
            cheminInverse.add(actuel);
            actuel = actuel.parent;
        }

        ArrayList<Noeud> chemin = new ArrayList<>();
        for (int i = cheminInverse.size() - 1; i >= 0; i--) {
            chemin.add(cheminInverse.get(i));
        }
        return chemin;
    }
}