package universite_paris8.iut.nchaieb.sae_jeux;// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.sound.sampled.*;

public class JouerSon {

    Long currentFrame;
    Clip clip;

    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    public JouerSon(String path, int count) // Changed parameter name to 'path' to avoid shadowing
            throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        filePath = path;

        File audioFile = new File(filePath);

        audioInputStream = AudioSystem.getAudioInputStream(audioFile.getAbsoluteFile());

        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        if(count<1000){
            clip.loop(count);
        }
        else {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }


    }


    public void setVolume(float volume) {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        float min = gainControl.getMinimum();
        float max = gainControl.getMaximum();

        float gain = min + (max - min) * volume;
        gainControl.setValue(gain);
    }

    public void play()
    {
        clip.start();

        status = "play";
    }

    public void pause() {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}