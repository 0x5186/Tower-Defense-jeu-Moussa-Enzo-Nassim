package universite_paris8.iut.nchaieb.sae_jeux.modele.Controleur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.TutorielVue;

public class MonObservateurTutoriel implements ChangeListener<Boolean> {

    private TutorielVue tutorielVue;

    public MonObservateurTutoriel(TutorielVue tutorielVue){
        this.tutorielVue = tutorielVue;
    }


    @Override
    public void changed(ObservableValue<? extends Boolean> obs, Boolean ancien, Boolean nouveau) {
        if (nouveau == true) {
            System.out.println("tuto activé");
            this.tutorielVue.getImage().setVisible(true);
        } else {
            System.out.println("tuto desactivé ");
            this.tutorielVue.retirerImage();
            this.tutorielVue.getImage().setVisible(false);
        }
    }

    public boolean estVisible(){
        if (this.tutorielVue.getImage().isVisible() == true){
            return true;
        }
        return false;
    }
}
