package universite_paris8.iut.nchaieb.sae_jeux.modele.Observateur;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;
import universite_paris8.iut.nchaieb.sae_jeux.vue.BaseVue;
import universite_paris8.iut.nchaieb.sae_jeux.vue.MonstreVue;

public class MonObservateurMonstre implements ListChangeListener<Monstre> {

    private Pane pane;
    private MonstreVue monstreVue;
    private BaseVue baseVue;

    public MonObservateurMonstre(Pane pane, BaseVue baseVue) {
        super();
        this.pane = pane;
        this.monstreVue = new MonstreVue(this.pane);
        this.baseVue= baseVue;
        System.out.println(baseVue);
    }

    private void creerSprite(Monstre monstreDeBase) {
        this.monstreVue.ajouterSprite(monstreDeBase);
    }

    private void enleverSprite(Monstre monstre) {
        this.monstreVue.animationMort(monstre);
    }

    @Override
    public void onChanged(Change<? extends Monstre> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Monstre nouveau : change.getAddedSubList()) {
                    creerSprite(nouveau);
                    nouveau.getActionActuelle().addListener((observable, oldValue, newValue) -> {
                        if (newValue.equals("marche")) {
                            this.monstreVue.animationMarche(nouveau);
                        }
                        else if (newValue.equals("soin")) {
                            this.monstreVue.stopAnimation(nouveau);
                        }
                    });
                }
            }
            if(change.wasRemoved()) {
                for (Monstre mort : change.getRemoved()) {
                    enleverSprite(mort);
                }
            }
        }
    }
}