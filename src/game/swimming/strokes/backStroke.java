package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static game.swimming.MainActivity.SPEED;
import static game.swimming.MainActivity.KEYS;

public class backStroke extends JPanel {
    private MainActivity main;
    private static int imgX;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backStroke_org_1.png")).getImage();

    public backStroke(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        imgReset();

        backThread back = new backThread();
        back.start();

        setVisible(true);
    }

    class backThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (imgX >= 840) {
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        new RankActivity(main);
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void keySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("left");
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_org_1.png")).getImage();
                    imgX += 10 * SPEED;
                    leftPrsd = true;
                    rightPrsd = false;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("right");
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_org_2.png")).getImage();
                    imgX += 10 * SPEED;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }

    public static void setSpeed(String mapKey) {
        KEYS.add(mapKey);
//        System.out.print(mapKey + " | 키 사이즈 " + KEYS.size() + " | 스피드 " + SPEED + "\n");
        if (KEYS.size() == 1) {
            if (KEYS.get(0).equals("right"))
                SPEED += 0.1;
            else {
                SPEED = 1;
                KEYS.remove(0);
            }
        } else {
            if (KEYS.size() == 2) {
                if (KEYS.get(0).equals("right") && KEYS.get(1).equals("left")) {
                    SPEED += 0.1;
                    KEYS.clear();
               }
                else {
                    SPEED = 1;
                    KEYS.remove(1);
                }
            }
        }
    }

    public static void imgReset() {
        imgX = 0;
        leftPrsd = false;
        rightPrsd = false;
        spacePrsd = false;
    }

    public void paint(Graphics g) {
        g.drawImage(main.pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[4], 145, 80, this);
        repaint();
    }
}
