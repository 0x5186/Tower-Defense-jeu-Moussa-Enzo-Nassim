package universite_paris8.iut.nchaieb.sae_jeux.modele.Tours;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Entite;
import universite_paris8.iut.nchaieb.sae_jeux.modele.monstres.Monstre;

public class Tour extends Entite {
    protected int portee;
    protected double x, y;
    protected int cout;
    private int atq;
    private final BooleanProperty modePlacementTour;
    private int cooldown;
    private int cooldownPourAttaque; //temps de chargement d'une attaque

    public Tour(int portee, int atq, int x, int y, int cout) {
        this.portee = portee;
        this.atq = atq;
        this.cout = cout;
        this.x = x;
        this.y = y;
        this.modePlacementTour = new SimpleBooleanProperty(false);
        this.cooldown = 0;
        this.cooldownPourAttaque = 3;
    }

    public int getCout() {
        return this.cout;
    }

    public boolean isModePlacementTour() {
        return modePlacementTour.get();
    }

    public BooleanProperty modePlacementTourProperty() {
        return modePlacementTour;
    }

    public void setModePlacementTour(boolean modePlacementTour) {
        this.modePlacementTour.set(modePlacementTour);
    }

    public void agir(ObservableList<Monstre> listeMonstre) {
        Monstre monstrePlusProche;
        gererCooldown();

        if (this.cooldown == cooldownPourAttaque) {
            if (!listeMonstre.isEmpty()) {

                monstrePlusProche = this.plusProche(listeMonstre);
                if (monstrePlusProche != null) {
                    this.setActionActuelle("fixe");

                    this.infligerDegat(monstrePlusProche);
                    this.setActionActuelle("attaque");
                    this.cooldown = 0;
                    System.out.println("j'attaque");
                }

            }
        }
    }

    private void gererCooldown() {
        if (this.cooldown < this.cooldownPourAttaque) {
            this.cooldown++;
        } else {
            this.cooldown = this.cooldownPourAttaque;
        }
    }

    public boolean estDansLeRayon(Monstre monstre) {
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());
        int distance = distanceX + distanceY;
        if (distance <= (this.portee * 16)) {
            return true;
        }
        return false;
    }

    public int getPortee() {
        return portee;
    }

    public Monstre plusProche(ObservableList<Monstre> listeMonstre) {
        Monstre monstrePlusProche = null;
        for (int i = 0; i < listeMonstre.size(); i++) {
            if (estDansLeRayon(listeMonstre.get(i))) {
                if (monstrePlusProche == null || calculDistance(listeMonstre.get(i)) < calculDistance(monstrePlusProche)) {
                    monstrePlusProche = listeMonstre.get(i);
                }
            }
        }
        return monstrePlusProche;
    }

    private int calculDistance(Monstre monstre) {
        int distanceX = Math.abs(monstre.getPosX() - this.getPosX());
        int distanceY = Math.abs(monstre.getPosY() - this.getPosY());
        return distanceX + distanceY;
    }

    public void infligerDegat(Monstre monstre) {
        System.out.println("tour attaque");
        if (monstre.getPV() != 0) {
            monstre.retirerPV(this.atq);
        }
    }

}