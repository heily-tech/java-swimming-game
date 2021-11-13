package game.swimming.activities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import game.swimming.MainActivity;

public class PlayActivity extends JPanel implements Runnable {
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    JPanel bgPanel;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        bgPanel = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(pool, 0, 0, 990, 760, this);
            }
        };
        bgPanel.setSize(1000, 800);
        bgPanel.setVisible(true);
        add(bgPanel);

        setVisible(true);
    }






    @Override
    public void run() {
    }
}