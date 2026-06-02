package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;

import java.util.ResourceBundle;

public class ControleurMenu implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }


    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");
    }

}
