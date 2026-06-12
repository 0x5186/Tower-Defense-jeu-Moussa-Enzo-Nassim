package universite_paris8.iut.nchaieb.sae_jeux.modele.Observateur;

import javafx.collections.ListChangeListener;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;

public class MonObservateurSymbole implements ListChangeListener<String> {

    private InterfaceVue interfaceVue;
//    private Symboles symboles;
    private int compteur;

    public MonObservateurSymbole(InterfaceVue interfaceVue){
        this.interfaceVue = interfaceVue;
//        this.symboles = symboles;
        this.compteur=0;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    @Override
    public void onChanged(Change<? extends String> change){
        while(change.next()){
            if (change.wasAdded()){
                for (String symbole : change.getAddedSubList()){
                    this.interfaceVue.afficherUnSeulSymbole(symbole, this.compteur);
                    compteur++;
                }

//                for (String symbole : change.getAddedSubList()){
//                    this.symboles.ajouterSymbole(symbole);
//                   if (this.symboles.avertissement()){
//
//                   }
//                }
            }
        }
    }

}
