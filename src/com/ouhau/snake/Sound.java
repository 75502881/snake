package com.ouhau.snake;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Sound {
    String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

    public  void playback(String filePath, long millis, boolean isLoop) throws URISyntaxException {
        while (isLoop) {

            String url = filePath.toString();
            path = path.replaceAll("snakeGame\\.jar","Chill.wav");
            System.out.println("this jar path:="+path);
            File file = new File(path);
            if (file == null || !file.exists() || file.isDirectory()){
                file = new File(filePath);
            }
            //System.out.println(uri);
            //sound Play
            Clip clip = Sound.soundPlayback(file);
            clip.start();
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //sound Play method
    public static Clip soundPlayback(File path) {

        System.out.println("path:"+path);
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

