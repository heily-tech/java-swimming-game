package game.swimming.activities;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import game.swimming.MainActivity;
import game.swimming.player;
import game.swimming.strokes.freestyle;

public class PlayActivity extends JPanel {
    public static ArrayList<String> strokeChooseNum = new ArrayList<>();
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    private int imgX;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    String strokeName;
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image pool, stroke;

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
        strokeName = "freestyle";
        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "_1.png")).getImage();
        Runnable freestyle = () -> {
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (imgX >= 840) {
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(freestyle).start();
        setVisible(true);
    }

    public void keySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "_1.png")).getImage();
                    imgX += 10;
                    leftPrsd = true;
                    rightPrsd = true;
                    spacePrsd = false;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        getActionMap().put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (spacePrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "_2.png")).getImage();
                    imgX += 10;
                    leftPrsd = true;
                    rightPrsd = false;
                    spacePrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "_3.png")).getImage();
                    imgX += 10;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }
    public void paint(Graphics g) {
        g.drawImage(pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[4], 145, 80, this);
        repaint();
    }
}