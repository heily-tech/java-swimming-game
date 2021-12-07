package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;
import game.swimming.activities.backgroundMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static game.swimming.MainActivity.*;
import static game.swimming.MainActivity.GAME_RESULT;
import static game.swimming.activities.RunningTimer.runningTime;
import static game.swimming.activities.RunningTimer.startTimer;

public class freestyle extends JPanel {
    private MainActivity main;
    public static boolean dist;
    private static int imgX, rank;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle/freestyle_org_1.png")).getImage();
    static boolean userDist = false, leftPrsd = false, rightPrsd = false, spacePrsd = false;

    public freestyle(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        imgReset();

        freestyleThread free = new freestyleThread();
        free.start();

        setVisible(true);

    }

    class freestyleThread extends Thread {
        @Override
        public void run() {
            startTimer();
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (imgX >= 800 && !dist) {
                        GAME_RESULT += (rank++ + "등 | YOU | " + runningTime() + "\n");
                        Thread.sleep(1000);
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        backgroundMusic.stop();
                        main.sfx("res/sfxs/end1.wav");
                        new RankActivity(main);
                        break;
                    } else if (imgX >= 800 && dist) {
                        userDist = true;
                    } else if (imgX <= 0 && userDist) {
                        GAME_RESULT += (rank++ + "등 | YOU | " + runningTime() + "\n");
                        Thread.sleep(1000);
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        new RankActivity(main);
                        backgroundMusic.stop();
                        main.sfx("res/sfxs/end1.wav");

                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void keySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("right");
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle/freestyle_"
                            + hats[3] + "_2.png")).getImage();
                    imgX += 4 * SPEED;
                    rightPrsd = true;
                    leftPrsd = true;
                    spacePrsd = false;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        getActionMap().put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("space");
                if (spacePrsd == false) {
                    imgX += 4 * SPEED;
                    leftPrsd = false;
                    rightPrsd = true;
                    spacePrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("left");
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle/freestyle_"
                            + hats[3] + "_1.png")).getImage();
                    imgX += 4 * SPEED;
                    leftPrsd = true;
                    rightPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }

    public static void setSpeed(String mapKey) {
        KEYS.add(mapKey);
       System.out.print(mapKey + " | 키 사이즈 " + KEYS.size() + " | 스피드 " + SPEED + "\n");
        if (KEYS.size() == 1) {
            if (KEYS.get(0).equals("right"))
                SPEED += 0.01;
            else {
                SPEED = 1;
                KEYS.remove(0);
            }
        } else if (KEYS.size() == 2) {
            if (KEYS.get(0).equals("right") && KEYS.get(1).equals("space"))
                SPEED += 0.01;
            else {
                SPEED = 1;
                KEYS.remove(1);
            }
        } else if (KEYS.size() == 3) {
            if (KEYS.get(0).equals("right") && KEYS.get(1).equals("space") && KEYS.get(2).equals("left")) {
                SPEED += 0.01;
                KEYS.clear();
            } else {
                SPEED = 1;
                KEYS.remove(2);
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
        g.drawImage(stroke, imgX, imgY[3], 145, 80, this);
        repaint();
    }
}
