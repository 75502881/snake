package com.ouhau.snake.bt;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        JFrame frame = new JFrame("欧がゲームで遊ぶ");
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
