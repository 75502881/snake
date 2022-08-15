package com.ouhau.snake;

import javax.swing.*;
import java.net.URISyntaxException;

public class CommonFunction {
    public static void isVisible(JButton button1,JButton button2,JButton button3,boolean isFlg){
        //button set Visible
        button1.setVisible(isFlg);
        button2.setVisible(isFlg);
        button3.setVisible(isFlg);
    }
    public static void popSound(){
        Sound sound = new Sound();
        try {
            sound.playback2(Data.popSoundURL.getFile(), 10);
            System.out.println("log:pop");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
