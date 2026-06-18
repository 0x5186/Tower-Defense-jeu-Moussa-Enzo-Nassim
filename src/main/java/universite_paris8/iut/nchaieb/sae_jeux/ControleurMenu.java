package universite_paris8.iut.nchaieb.sae_jeux;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ControleurMenu implements Initializable {
    JouerSon musiqueFond = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        try {
            musiqueFond = new JouerSon("src/main/resources/universite_paris8/iut/nchaieb/sae_jeux/Sons/musiqueMenu.wav",0);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        musiqueFond.setVolume(0.75f);
        musiqueFond.play();


    }


    @FXML
    public void onBoutonJouerClique() throws Exception {
        Main.map=2;
        musiqueFond.stop();
        Main.changerScene("universite_paris8/iut/nchaieb/sae_jeux/fenetreJeu.fxml");
    }

}
