package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.nchaieb.sae_jeux.modele.Environnement;


import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    public static int map = 1;
    private BooleanProperty modePlacementTour;

    @Override
    public void start(Stage premierstage) throws IOException {

        stage = premierstage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fenetreMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 900);
        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
        stage.setTitle("witch hat atelier defense");
        stage.setScene(scene);
        stage.show();


//        Controller.displayImage

    }

    public static void setMap(int map) { Main.map = map; }

    public static void changerScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fenetreJeu.fxml"));
        Scene scene = new Scene(loader.load(), 1920, 1080);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}