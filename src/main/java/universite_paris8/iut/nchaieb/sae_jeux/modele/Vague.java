package universite_paris8.iut.nchaieb.sae_jeux.modele;

public class Vague {

    private ListeApparition listeApparition;

    public Vague() {
        listeApparition = new ListeApparition();
    }

    public ListeApparition getListeApparition() {
        return listeApparition;
    }

    public boolean estTerminee() {
        return !listeApparition.resteProchain();
    }
}