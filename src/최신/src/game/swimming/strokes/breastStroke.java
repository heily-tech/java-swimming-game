package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static game.swimming.MainActivity.SPEED;
import static game.swimming.MainActivity.KEYS;

public class breastStroke extends JPanel {
    private MainActivity main;
    private static int imgX;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breastStroke_org_1.png")).getImage();

    public breastStroke(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        breastThread breast = new breastThread();
        breast.start();

        setVisible(true);
    }

    class breastThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (imgX >= 840) {
                        upPrsd = true;
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        downPrsd = true;
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
        //위 좌 스페이스 아래
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upPrsd == false) {
                    setSpeed("up");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_1.png")).getImage();
                    imgX += 10 * SPEED;
                    leftPrsd = false;
                    rightPrsd = false;
                    upPrsd = true;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "side1");
        getActionMap().put("side1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftPrsd == false) {
                    leftPrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "side");
        getActionMap().put("side", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_2.png")).getImage();
                    if (leftPrsd == true && rightPrsd == false) {
                        imgX += 10 * SPEED;
                        setSpeed("side");
                    }
                    rightPrsd = true;
                    upPrsd = true;
                    spacePrsd = false;
                    downPrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        getActionMap().put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("space");
                if (spacePrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_3.png")).getImage();
                    imgX += 10 * SPEED;
                    leftPrsd = true;
                    rightPrsd = true;
                    upPrsd = true;
                    spacePrsd = true;
                    downPrsd = false;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
        getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpeed("down");
                if (downPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_1.png")).getImage();
                    imgX += 10 * SPEED;
                    rightPrsd = true;
                    leftPrsd = true;
                    upPrsd = false;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
    }

    public static void setSpeed(String mapKey) {
        KEYS.add(mapKey);
        System.out.print(mapKey + " | 키 사이즈 " + KEYS.size() + " | 스피드 " + SPEED + "\n");
        if (KEYS.size() == 1) {
            if (KEYS.get(0).equals("up"))
                SPEED += 0.04;
            else {
                SPEED = 1;
                KEYS.remove(0);
            }
        } else if (KEYS.size() == 2) {
            if (KEYS.get(0).equals("up") && KEYS.get(1).equals("side"))
                SPEED += 0.04;
            else {
                SPEED = 1;
                KEYS.remove(1);
            }
        } else if (KEYS.size() == 3) {
            if (KEYS.get(0).equals("up") && KEYS.get(1).equals("side") && KEYS.get(2).equals("space")) {
                SPEED += 0.04;
            } else {
                SPEED = 1;
                KEYS.remove(2);
            }
        } else if (KEYS.size() == 4) {
            if (KEYS.get(0).equals("up") && KEYS.get(1).equals("side") && KEYS.get(2).equals("space") && KEYS.get(3).equals("down")) {
                SPEED += 0.04;
                KEYS.clear();
            } else {
                SPEED = 1;
                KEYS.remove(3);
                }
            }
        }

    public static void imgReset() {
        imgX = 0;
        upPrsd = false;
        leftPrsd = false;
        rightPrsd = false;
        spacePrsd = false;
        downPrsd = false;
    }

    public void paint(Graphics g) {
        g.drawImage(pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[4], 145, 80, this);
        repaint();
    }
}
