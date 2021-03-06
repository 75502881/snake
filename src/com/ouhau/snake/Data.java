package com.ouhau.snake;

import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static URL upUrl = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static URL bodyURL = Data.class.getResource("/statics/body.png");
    public static URL foodURL = Data.class.getResource("/statics/food/food.png");
    public static URL watermelonURL = Data.class.getResource("/statics/food/Watermelon.png");
    public static URL bombURL = Data.class.getResource("/statics/food/bomb.png");
    public static URL appleURL = Data.class.getResource("/statics/food/apple.png");
    public static URL deathURL = Data.class.getResource("/statics/food/death.png");
    public static URL ForestURL = Data.class.getResource("/statics/dearSir/Forest.png");
    public static URL GardenRockURL = Data.class.getResource("/statics/dearSir/GardenRock.png");
    public static URL XyGridURL = Data.class.getResource("/statics/dearSir/Xygrid.png");
    public static URL strawberryURL = Data.class.getResource("/statics/food/strawberry.jpg");
    public static URL chillSoundURL = Data.class.getResource("/statics/sound/Chill.wav");
    public static URL popSoundURL = Data.class.getResource("/statics/sound/Pop.wav");
    public static ImageIcon header = new ImageIcon(headerURL);
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static ImageIcon food = new ImageIcon(foodURL);
    public static ImageIcon watermelon = new ImageIcon(watermelonURL);
    public static ImageIcon bomb = new ImageIcon(bombURL);
    public static ImageIcon apple = new ImageIcon(appleURL);
    public static ImageIcon death = new ImageIcon(deathURL);
    public static ImageIcon strawberry = new ImageIcon(strawberryURL);
    public static ImageIcon Forest = new ImageIcon(ForestURL);
    public static ImageIcon GardenRock = new ImageIcon(GardenRockURL);
    public static ImageIcon XyGrid = new ImageIcon(XyGridURL);
}
