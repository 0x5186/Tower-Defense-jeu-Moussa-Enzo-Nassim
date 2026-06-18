package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Symboles;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;

public class MonObservateurSymbole implements ListChangeListener<String> {

    private InterfaceVue interfaceVue;
    private int compteur;

    public MonObservateurSymbole(InterfaceVue interfaceVue) {
        this.interfaceVue = interfaceVue;
        this.compteur = 0;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    @Override
    public void onChanged(Change<? extends String> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (String symbole : change.getAddedSubList()) {
                    this.interfaceVue.afficherUnSeulSymbole(symbole, this.compteur);
                    compteur++;
                }
            }

            if (change.getList().isEmpty()) {
                this.compteur = 0;
            }
        }


    }
}
