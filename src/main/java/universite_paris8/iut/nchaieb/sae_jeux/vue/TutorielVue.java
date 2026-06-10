package universite_paris8.iut.nchaieb.sae_jeux.vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.nchaieb.sae_jeux.Main;


public class TutorielVue {

    //rempalce le rectangle
    Image parcheminTuto = new Image(Main.class.getResourceAsStream("images/ParcheminTuto.png"));

    private Rectangle rectangle;
    private StackPane stackPane;
    private BooleanProperty afficher;
    private HBox hbox;
    private int page;
    private Label label;
    private Label paragraphe;
    private ImageView image;

    public TutorielVue(StackPane stackPane){
        this.image = new ImageView(parcheminTuto);
        this.stackPane =stackPane;
        this.hbox = new HBox();
        this.label = new Label();
        this.paragraphe = new Label();
        this.afficher = new SimpleBooleanProperty(false);
        //definit taille + couleur label
        this.label.setScaleY(2);
        this.label.setScaleX(2);
        this.label.setTextFill(Color.BLACK);
        //paragraphe
        this.paragraphe.setTextFill(Color.BLACK);
        this.paragraphe.setScaleY(2);
        this.paragraphe.setScaleX(2);
        this.paragraphe.setTranslateY(40);

        //ajout du label dans le hbox + position hbox
        this.hbox.getChildren().add(this.paragraphe);
        this.hbox.getChildren().add(this.label);
        this.hbox.setTranslateX(1420);
        this.hbox.setTranslateY(50);
        //couleur rectangle en fodn + effet + position + deja caché
        this.image.setScaleX(1.7);
        this.image.setScaleY(1.3);
        this.image.setTranslateX(1360);
        this.image.setTranslateY(90);
        this.image.setVisible(false);
        //ajout rectangle dans hbox + ajout hbox dans stackpane
        this.stackPane.getChildren().add(image);
        this.stackPane.getChildren().add(hbox);
    }

    public void afficherTutot(){
        //à chaque fois qu'on ouvre le tuto on met la page à 0
        this.page = 0;
        //le paragraphes est toujours vidé sinon il restera afficher même après avoir fermer le tuto
        this.paragraphe.setText("");
        //vu que le simpleProperty est sur false, on veut s'assurer de le mettre en true avant
        this.afficher.set(!this.afficher.get());
        //si maintenant il est true, il affiche
        if (this.afficher.get()){
            this.label.setText("Witch Hat Atelier ~ Tutoriel");
        } else {
            this.label.setText("");
        }
    }

    public BooleanProperty tutoProperty(){
        return this.afficher;
    }


    public ImageView getImage(){
        return this.image;
    }

    public void changerPage(){
        page++;
        switch (this.page){
            case 1:
                this.label.setText("page 2");
                paragraphes();
                break;
            case 2:
                this.label.setText("page 3");
                paragraphes();
                break;
        }
    }
    
    public void paragraphes() {
        if (this.page == 1){
            this.paragraphe.setText("lala, \n ~Nassim");
        } else if (this.page == 2){
            this.paragraphe.setText("lolo, \n ~Nassim");
        }
    }

}
