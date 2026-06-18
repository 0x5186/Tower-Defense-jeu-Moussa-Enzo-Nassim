package universite_paris8.iut.nchaieb.sae_jeux.modele.Artefacts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList <Artefact> lesArtefacts;

    public Inventaire(){
        this.lesArtefacts = FXCollections.observableArrayList();
    }

    public ObservableList <Artefact> getLesArtefacts() {
        return lesArtefacts;
    }

    public void ajouterArtefact(Artefact artefact){
        this.lesArtefacts.add(artefact);
        System.out.println(artefact.getNom() + "a été ajoquté");
    }

    public void retirerArtefact(Artefact artefact){
        this.lesArtefacts.remove(artefact);
    }
}
