package universite_paris8.iut.nchaieb.sae_jeux.modele;

import java.util.ArrayList;

public class ListeApparition {

    private int curseur;
    private ArrayList<Integer> listeMonstres;
    private ArrayList<Integer> listeDelais;

    public ListeApparition() {
        curseur = 0;
        listeMonstres = new ArrayList<>();
        listeDelais = new ArrayList<>();
    }

    public void ajouter(int codeMonstre, int delaiTick) {
        listeMonstres.add(codeMonstre);
        listeDelais.add(delaiTick);
    }

    public boolean resteProchain() {
        return curseur < Math.min(listeMonstres.size(), listeDelais.size());
    }

    public int prochainMonstre() {
        return listeMonstres.get(curseur);
    }

    public int prochainDelai() {
        return listeDelais.get(curseur);
    }

    public void avancer() {
        curseur++;
    }
}