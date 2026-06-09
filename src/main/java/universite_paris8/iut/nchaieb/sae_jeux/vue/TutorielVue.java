package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TutorielVue {

    private Rectangle rectangle;
    private StackPane stackPane;
    private BooleanProperty afficher;

    public TutorielVue(StackPane stackPane){
        this.stackPane =stackPane;
        this.afficher = new SimpleBooleanProperty(false);
        this.rectangle = new Rectangle(720, 750);
        this.rectangle.setFill(Color.BLACK);
        this.rectangle.setOpacity(0.5);
        this.rectangle.setTranslateX(1200);
        this.rectangle.setTranslateY(1);
        this.rectangle.setVisible(false);
        this.stackPane.getChildren().add(this.rectangle);
    }

    public void afficherTutot(){
        this.afficher.set(!this.afficher.get());
    }

    public BooleanProperty tutoProperty(){
        return this.afficher;
    }

    public Rectangle getRectangle(){

        return this.rectangle;
    }

    public void changerPage(){

    }
}
