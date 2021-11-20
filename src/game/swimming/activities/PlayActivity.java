package game.swimming.activities;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import game.swimming.MainActivity;

public class PlayActivity extends JPanel {
    public static ArrayList<String> strokeChooseNum = new ArrayList<>();
    public static String strokeName;
    private MainActivity main;
    private int imgX, distance = 0, pcX, pcY;
    private int[] pc_Y = {5, 105, 198, 290, 385, 480, 573, 668};
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
//    Image stroke, pcStroke;
    Image stroke = new ImageIcon(MainActivity.class.getResource("res//strokes/freestyle_1.png")).getImage();
    Image pcStroke = new ImageIcon(MainActivity.class.getResource("res//strokes/freestyle_1.png")).getImage();

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        for (int i = 1; i <= 8; i++) {
            if (i == 3)
                continue;
            pcY = pc_Y[i-1];
            pcThread pc = new pcThread("pc" + i, pcY);
            pc.start();
        }

        userThread user = new userThread();
        user.start();

        setBackground(Color.BLUE);
        setVisible(true);
    }

    class pcThread extends Thread {
        String pc_name = "";
        int pc_y;

        pcThread(String pc_name, int pc_y) {
            this.pc_name = pc_name;
            this.pc_y = pc_y;
        }

        @Override
        public void run() {
            for (int i = 0; i < 600; i++) {
                try {
                    pcX += 15 * Math.random();
                    Thread.sleep(300);
                    if (pcX >= 890) {
                        pcX = 890;
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class userThread extends Thread {
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
                        new rankActivity(main);
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
        g.drawImage(stroke, imgX, 290, 145, 80, this);
        g.drawImage(pcStroke, pcX, pcY, 145, 80, this);
        repaint();
    }
}