package game.swimming.activities;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.swimming.MainActivity;

public class PlayActivity extends JPanel {
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    private int setImage, imgX;
    JPanel bgPanel, charPanel;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_1.png")).getImage();

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        bgPanel = new JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(pool, 0, 0, 990, 760, this);
                repaint();
            }
        };
        bgPanel.setSize(1000, 800);
        bgPanel.setVisible(true);
        add(bgPanel);

        charPanel = new JPanel() {
            public void paint(Graphics g) {
                if (setImage == 1)
                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_1.png")).getImage();
                else if (setImage == 3)
                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_3.png")).getImage();
                g.drawImage(free, imgX, 385, 145, 80, this);
                //5, 105, 200, 290, 385
                repaint();
            }
        };
        charPanel.setSize(1000, 800);
        charPanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        charPanel.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left");
                setImage = 1;
                imgX += 10;
            }
        });
        charPanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        charPanel.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("right");
                setImage = 1;
                imgX += 10;
            }
        });
        charPanel.setVisible(true);
        add(charPanel);

        setVisible(true);
    }
}