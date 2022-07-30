package com.ouhau.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //Judgment of length
    int length;

    //judgment of score
    int score;

    //decision
    int judgment = 1;
    int isBegin = 10;

    //snake speed
    int delay;

    //snake x and y
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];

    //Head direction
    String fx;

    //The speed button1
    JButton button1;

    //The speed button2
    JButton button2;

    //The speed button3
    JButton button3;

    //Judgment whether it is a start
    boolean isStart = false;

    //Timer judgment
    Timer timer = null;

    //food x and y
    int foodX;
    int foodY;

    //Random number of food
    Random random = new Random();
    Random num = new Random();

    //Food data
    ImageIcon imageIcon = Data.food;

    //snake die code
    public void Dead(){
        isFail = true;
    }

    @Deprecated
    public void Fail(){
        Dead();
    }
    //game variable setup constructor
    public GamePanel() {
        init();
        this.setLayout(null);

        button1 = new JButton("遅い");
        button1.setBounds(100, 100, 200, 50);

        button2 = new JButton("普通");
        button2.setBounds(350, 100, 200, 50);

        button3 = new JButton("速い");
        button3.setBounds(600, 100, 200, 50);

        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setActionCommand("slow");

        button2.addActionListener(this);
        button2.setFocusable(false);
        button2.setActionCommand("usually");

        button3.addActionListener(this);
        button3.setFocusable(false);
        button3.setActionCommand("quick");

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.setFocusable(true);
        this.addKeyListener(this);
        timer = new Timer(delay, this);

    }

    //Judgment of Death
    boolean isFail = false;

    //variable setup function
    public void init() {
        judgment = 1;
        isBegin = 10;
        delay = 0;
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        fx = "R";

        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);

        score = 0;
    }

    //paint food and Font and button
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        Data.header.paintIcon(this, g, 25, 11);

        g.fillRect(25, 75, 850, 600);

        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        imageIcon.paintIcon(this, g, foodX, foodY);

        g.setColor(Color.WHITE);
        g.setFont(new Font("状態", Font.BOLD, 18));
        g.drawString("長さ" + length, 750, 35);
        g.drawString("スコア" + score, 750, 50);

        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("snake Game", Font.BOLD, 40));
            g.drawString("スペースでスタート", 300, 300);
        }

        if (isFail) {
            judgment = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("snake Game", Font.BOLD, 40));
            g.drawString("死んでしまった", 300, 300);
            init();
        }

        if (judgment != 1){
            CommonFunction.isVisible(button1, button2, button3, false);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int kyeCode = e.getKeyCode();

        if (kyeCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                CommonFunction.isVisible(button1, button2, button3, false);
            } else if (isBegin == 10) {
                isBegin = 0;
                CommonFunction.isVisible(button1, button2, button3, true);
                if (kyeCode == KeyEvent.VK_SPACE) {
                    CommonFunction.isVisible(button1, button2, button3, false);
                }
            } else if (isFail != true || isBegin != 10) {
                CommonFunction.isVisible(button1, button2, button3, false);
            }

            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint();
        }

        if (kyeCode == KeyEvent.VK_DOWN) {
            fx = "D";
        } else if (kyeCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (kyeCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (kyeCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd != null) {
            if (cmd.equals("slow")) {
                delay = 300;
            } else if (cmd.equals("usually")) {
                delay = 200;
            } else if (cmd.equals("quick")) {
                delay = 160;
            }
            timer = new Timer(delay, this);
            timer.start();
        }
        startGame();

    }


    public void startGame() {
        if (isStart && isFail == false) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }

            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                if (Data.food.equals(imageIcon)) {
                    score += 10;
                    length += 1;
                } else if (Data.watermelon.equals(imageIcon)) {
                    score += 30;
                    length += 3;
                } else if (Data.bomb.equals(imageIcon)) {
                    score -= 10;
                    length -= 1;
                } else if (Data.apple.equals(imageIcon)) {
                    score += 50;
                    length += 2;
                } else if (Data.death.equals(imageIcon)) {
                    score = 0;
                    Dead();
                } else if (Data.strawberry.equals(imageIcon)) {
                    score += 20;
                    length += 2;
                }
                try {
                    Sound.playback(Data.chillSoundURL, 100, false);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                int nums = num.nextInt(6);
                if (nums == 1) {
                    imageIcon = Data.food;
                } else if (nums == 2) {
                    imageIcon = Data.watermelon;
                } else if (nums == 3) {
                    imageIcon = Data.bomb;
                } else if (nums == 4) {
                    imageIcon = Data.apple;
                } else if (nums == 5) {
                    imageIcon = Data.death;
                } else if (nums == 6) {
                    imageIcon = Data.strawberry;
                }

                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    Dead();
                }
                isDeath();
            }

            repaint();
        }
        timer.start();
    }

    public void isDeath() {
        if (snakeX[0] >= 850 || snakeY[0] >= 650) {
           Dead();
        }
    }
}

