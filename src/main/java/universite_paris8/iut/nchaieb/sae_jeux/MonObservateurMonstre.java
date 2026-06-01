package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;

public class MonObservateurMonstre implements ListChangeListener<Monstre> {

    private Pane pane;
    private MonstreVue monstreVue;

    public MonObservateurMonstre(Pane pane) {
        super();
        this.pane = pane;
        this.monstreVue = new MonstreVue(this.pane);
    }



    private void creerSprite(Monstre monstreDeBase) {
        this.monstreVue.ajouterSprite(monstreDeBase);
    }



    private void enleverSprite(Entite entite) {

        this.monstreVue.animationMort(entite);

    }

    @Override
    public void onChanged(Change<? extends Monstre> change) {

        while (change.next()) {
            if (change.wasAdded()) {

                for (Monstre nouveau : change.getAddedSubList()) {

                    creerSprite(nouveau);
                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {

                        if (newValue.equals("fixe")) {

                            this.monstreVue.stopAnimation(nouveau);
                        }
                        if (newValue.equals("marche")) {


                            this.monstreVue.animationMarche(nouveau);

                        }
                        if (newValue.equals("attaque")) {
                            this.monstreVue.animationAttaque(nouveau);
                        }
                    });




                }
            }
            if(change.wasRemoved()) {
                System.out.println("je suis morttttttttttttt");
                for (Entite mort : change.getRemoved()) {
                    enleverSprite(mort);
                }
            }

        }
    }
}