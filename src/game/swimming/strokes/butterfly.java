package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import static game.swimming.MainActivity.SPEED;
import static game.swimming.MainActivity.KEYS;

public class butterfly extends JPanel {
    private MainActivity main;
    private static int imgX;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterfly_org_1.png")).getImage();

    public butterfly(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        btflyThread btfly = new btflyThread();
        btfly.start();

        setVisible(true);

    }

    class btflyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (imgX >= 840) {
                        leftPrsd = true;
                        rightPrsd = true;
                        upPrsd = true;
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
        //좌우 위 스페이스 아래
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterfly_"
                            + hats[3] + "_1.png")).getImage();
                    if (leftPrsd == true && rightPrsd == false) {
                        imgX += 9 * SPEED;
                        setSpeed("side");
                    }
                    rightPrsd = true;
                    upPrsd = false;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upPrsd == false) {
                    setSpeed("up");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterfly_"
                            + hats[3] + "_2.png")).getImage();
                    imgX += 9 * SPEED;
                    leftPrsd = true;
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterfly_"
                            + hats[3] + "_3.png")).getImage();
                    imgX += 9 * SPEED;
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterfly_"
                            + hats[3] + "_3.png")).getImage();
                    imgX += 9 * SPEED;
                    rightPrsd = false;
                    leftPrsd = false;
                    upPrsd = true;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
    }
    public static void setSpeed(String mapKey) {
        KEYS.add(mapKey);
//        System.out.print(mapKey + " | 키 사이즈 " + KEYS.size() + " | 스피드 " + SPEED + "\n");
        if (KEYS.size() == 1) {
            if (KEYS.get(0).equals("side"))
                SPEED += 1.5;
            else {
                SPEED = 1;
                KEYS.remove(0);
            }
        } else if (KEYS.size() == 2) {
            if (KEYS.get(0).equals("side") && KEYS.get(1).equals("up"))
                SPEED += 1.5;
            else {
                SPEED = 1;
                KEYS.remove(1);
            }
        } else if (KEYS.size() == 3) {
            if (KEYS.get(0).equals("side") && KEYS.get(1).equals("up") && KEYS.get(2).equals("space")) {
                SPEED += 1.5;
            } else {
                SPEED = 1;
                KEYS.remove(2);
            }
        } else if (KEYS.size() == 4) {
            if (KEYS.get(0).equals("side") && KEYS.get(1).equals("up") && KEYS.get(2).equals("space") && KEYS.get(3).equals("down")) {
                SPEED += 1.5;
                KEYS.clear();
            } else {
                SPEED = 1;
                KEYS.remove(3);
            }
        }
    }

    public static void imgReset() {
        imgX = 0;
        leftPrsd = false;
        rightPrsd = false;
        upPrsd = false;
        spacePrsd = false;
        downPrsd = false;
    }

    public void paint(Graphics g) {
        g.drawImage(main.pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[3], 145, 80, this);
        repaint();
    }
}