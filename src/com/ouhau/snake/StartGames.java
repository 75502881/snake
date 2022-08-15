package com.ouhau.snake;

import javax.swing.*;
import java.net.URISyntaxException;

public class StartGames {
    public static void main(String[] args) throws URISyntaxException {
        System.out.println();
        JFrame frame = new JFrame("欧がゲームで遊ぶ");
        frame.setBounds(10, 10, 900, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new GamePanel();

        frame.add(jPanel);
        frame.setVisible(true);
        Sound sound = new Sound();
        sound.playback(Data.chillSoundURL.getFile(), 1000, true);
    }
}
