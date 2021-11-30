package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class breastStroke extends JPanel {
    private MainActivity main;
    private static int imgX, distance;
    private double speed = 1.0;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    private String[] hats = {"black", "blue", "green", "org", "pur", "red", "white", "yel"};
    static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breastStroke_org_1.png")).getImage();
    ArrayList<String> keys = new ArrayList<>();

    public breastStroke(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        breastThread breast = new breastThread();
        breast.start();

        setBackground(Color.GREEN);
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

    public void keySet() {
        //위 좌 스페이스 아래
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upPrsd == false) {
                    setSpeed("up");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_1.png")).getImage();
                    imgX += 10;
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
                        imgX += 10;
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
                    System.out.println("space");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_3.png")).getImage();
                    imgX += 10;
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
                    System.out.println("down");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_org_1.png")).getImage();
                    imgX += 10;
                    rightPrsd = true;
                    leftPrsd = true;
                    upPrsd = false;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
    }
    public void setSpeed(String mapKey) {
        keys.add(mapKey);
        System.out.print(mapKey + " | 키 사이즈 " + keys.size() + " | 스피드 " + speed + "\n");
        System.out.println(keys);
        if (keys.size() == 1) {
            if (keys.get(0).equals("up"))
                speed += 0.1;
            else {
                speed = 1;
                keys.remove(0);
            }
        } else if (keys.size() == 2) {
            if (keys.get(0).equals("up") && keys.get(1).equals("side"))
                speed += 0.1;
            else {
                speed = 1;
                keys.remove(1);
            }
        } else if (keys.size() == 3) {
            if (keys.get(0).equals("up") && keys.get(1).equals("side") && keys.get(2).equals("space")) {
                speed += 0.1;
            } else {
                speed = 1;
                keys.remove(2);
            }
        } else if (keys.size() == 4) {
            if (keys.get(0).equals("up") && keys.get(1).equals("side") && keys.get(2).equals("space") && keys.get(3).equals("down")) {
                speed += 0.1;
                keys.clear();
            } else {
                speed = 1;
                keys.remove(3);
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
