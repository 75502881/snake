package com.ouhau.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int lenth;
    int score;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    String fx;

    boolean isStart = false;
    Timer timer = new Timer(100, this);

    int foodX;
    int foodY;
    Random random = new Random();
    Random num = new Random();
    ImageIcon imageIcon = Data.food;

    public GamePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    boolean isFail = false;

    public void init() {
        lenth = 3;
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

        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        imageIcon.paintIcon(this, g, foodX, foodY);

        g.setColor(Color.WHITE);
        g.setFont(new Font("状態", Font.BOLD, 18));
        g.drawString("長さ" + lenth, 750, 35);
        g.drawString("スコア" + score, 750, 50);

        if (isStart == false) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("sanke Game", Font.BOLD, 40));
            g.drawString("スペースでスタート", 300, 300);
        }

        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("sanke Game", Font.BOLD, 40));
            g.drawString("死んでしまった", 300, 300);
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
        if (isStart && isFail == false) {
            for (int i = lenth - 1; i > 0; i--) {
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
                    lenth += 1;
                } else if (Data.watermelon.equals(imageIcon)) {
                    score += 30;
                    lenth += 3;
                } else if (Data.bomb.equals(imageIcon)) {
                    score -= 10;
                    lenth -= 1;
                } else if (Data.apple.equals(imageIcon)) {
                    score += 50;
                    lenth += 2;
                } else if (Data.death.equals(imageIcon)) {
                    score = 0;
                    lenth = 0;
                    isFail = true;
                } else if (Data.strawberry.equals(imageIcon)) {
                    score += 20;
                    lenth += 2;
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

                //


                //test
                //testds
                //testssfsd
                //testdssdfdffevv
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }

            for (int i = 1; i < lenth; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }
}

