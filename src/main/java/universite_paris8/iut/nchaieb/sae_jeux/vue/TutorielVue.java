package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class TutorielVue {

    private Rectangle rectangle;
    private StackPane stackPane;
    private BooleanProperty afficher;
    private HBox hbox;
    private int page;
    private Label label;
    private Label paragraphe1;

    public TutorielVue(StackPane stackPane){
        this.stackPane =stackPane;
        this.hbox = new HBox();
        this.label = new Label();
        this.paragraphe1 = new Label();
        this.afficher = new SimpleBooleanProperty(false);
        this.rectangle = new Rectangle(720, 750);
        //definit taille + couleur label
        this.label.setScaleY(2);
        this.label.setScaleX(2);
        this.label.setTextFill(Color.WHITE);
        //ajout du label dans le hbox + position hbox
        this.hbox.getChildren().add(this.label);
        this.hbox.setTranslateX(1290);
        this.hbox.setTranslateY(10);
        this.hbox.getChildren().add(this.paragraphe1);
        //couleur rectangle en fodn + effet + position + deja caché
        this.rectangle.setFill(Color.BLACK);
        this.rectangle.setOpacity(0.5);
        this.rectangle.setTranslateX(1200);
        this.rectangle.setTranslateY(1);
        this.rectangle.setVisible(false);
        //ajout rectangle dans hbox + ajout hbox dans stackpane
        this.stackPane.getChildren().add(this.rectangle);
        this.stackPane.getChildren().add(hbox);

    }

    public void afficherTutot(){
        this.page = 0;
        this.afficher.set(!this.afficher.get());
        if (this.afficher.get()){
            this.label.setText("bienvenue sur notre tuto");
        } else {
            this.label.setText("");
        }
    }

    public BooleanProperty tutoProperty(){
        return this.afficher;
    }

    public Rectangle getRectangle(){

        return this.rectangle;
    }

    public void changerPage(){
        page++;
        switch (this.page){
            case 1:
                this.label.setText("page 2");
                break;
            case 2:
                this.label.setText("page 3");
                break;
        }
    }

}
