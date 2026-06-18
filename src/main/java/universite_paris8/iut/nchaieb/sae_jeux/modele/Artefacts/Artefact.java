package universite_paris8.iut.nchaieb.sae_jeux.modele.Artefacts;

public class Artefact {

    private String nom;
    private String cheminImage;

    public Artefact(String nom, String cheminImage){
        this.nom = nom;
        this.cheminImage = cheminImage;
    }

    public String getNom(){
        return this.nom;
    }

    public String getCheminImage(){
        return this.cheminImage;
    }
}
