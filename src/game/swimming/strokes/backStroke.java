package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class backStroke extends JPanel {
    private MainActivity main;
    private static int imgX, distance;
    private double speed = 1.0;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_org_1.png")).getImage();
    ArrayList<String> keys = new ArrayList<>();

    public backStroke(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        imgReset();

        backThread back = new backThread();
        back.start();

        setBackground(Color.ORANGE);
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

    public void keySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("left");
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_org_1.png")).getImage();
                    imgX += 10;
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
                    imgX += 10;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }

    public void setSpeed(String mapKey) {
        keys.add(mapKey);
        System.out.print(mapKey + " | 키 사이즈 " + keys.size() + " | 스피드 " + speed + "\n");
        System.out.println(keys);
        if (keys.size() == 1) {
            if (keys.get(0).equals("right"))
                speed += 0.1;
            else {
                speed = 1;
                keys.remove(0);
            }
        } else {
            if (keys.size() == 2) {
                if (keys.get(0).equals("right") && keys.get(1).equals("left")) {
                    speed += 0.1;
                    keys.clear();
               }
                else {
                    speed = 1;
                    keys.remove(1);
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
        g.drawImage(pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[4], 145, 80, this);
        repaint();
    }
}
