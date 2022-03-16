package com.ouhau.snake;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Sound {
    public static void playback(String filePath, long millis,boolean isLoop) {
        while (isLoop) {
            Clip clip = Sound.soundPlayback(new File(filePath));
            clip.start();
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static Clip soundPlayback(File path) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(path)) {

            AudioFormat af = ais.getFormat();

            DataLine.Info dataLine = new DataLine.Info(Clip.class, af);

            Clip c = (Clip) AudioSystem.getLine(dataLine);

            c.open(ais);

            return c;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }
}

