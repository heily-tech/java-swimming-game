package game.swimming;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class player {
    private int x = 0;
    private int y = 0;
    private int speed;
    private int setImage, imgX;
    Image free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_1.png")).getImage();

    public player() {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("left");
            x += 10;
        }
    }

}
