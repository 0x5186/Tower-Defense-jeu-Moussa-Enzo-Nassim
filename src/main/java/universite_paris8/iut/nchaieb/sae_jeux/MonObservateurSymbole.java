package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import universite_paris8.iut.nchaieb.sae_jeux.vue.InterfaceVue;

public class MonObservateurSymbole implements ListChangeListener<String> {

    private InterfaceVue interfaceVue;

    public MonObservateurSymbole(InterfaceVue interfaceVue){
        this.interfaceVue = interfaceVue;
    }

    @Override
    public void onChanged(Change<? extends String> change){
        while(change.next()){
            if (change.wasAdded()){
                for (String symbole : change.getAddedSubList()){
                    this.interfaceVue.afficherUnSeulSymbole(symbole);



                }
            }
        }
    }

}
