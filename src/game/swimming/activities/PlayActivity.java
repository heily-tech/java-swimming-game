package game.swimming.activities;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.swimming.MainActivity;
import game.swimming.player;

public class PlayActivity extends JPanel {
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    private int imgX;
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_1.png")).getImage();

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        Runnable task = () -> {
            while (true) {
                try {
                    charPanel();
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
        new Thread(task).start();

        setVisible(true);
    }

    private void charPanel() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftPrsd == false) {
//                    System.out.println("left");
                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_1.png")).getImage();
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
//                    System.out.println("space");
                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_2.png")).getImage();
                    //이미지 바꾸쉴?
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
//                    System.out.println("right");
                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_3.png")).getImage();
                    imgX += 10;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
//        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
//        getActionMap().put("up", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (spacePrsd == false) {
//                    System.out.println("up");
//                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_3.png")).getImage();
//                    imgX += 10;
//                }
//            }
//        });
//        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
//        getActionMap().put("down", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (spacePrsd == false) {
//                    System.out.println("down");
//                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/free_3.png")).getImage();
//                    imgX += 10;
//                }
//            }
//        });
    }

    public void paint(Graphics g) {
        g.drawImage(pool, 0, 0, 990, 760, this);
        g.drawImage(free, imgX, 385, 145, 80, this);
        //5, 105, 200, 290, 385
        repaint();
    }
}