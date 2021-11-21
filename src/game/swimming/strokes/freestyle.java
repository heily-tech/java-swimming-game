package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.SelectModeActivity;
import game.swimming.activities.rankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class freestyle extends JPanel {
    private MainActivity main;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage();
    private int imgX, distance = 0, speed = 1;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false;

    public freestyle(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        freestyleThread free = new freestyleThread();
        free.start();
        setVisible(true);
    }

    class freestyleThread extends Thread {
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage();
                    imgX += 10 * speed;
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_2.png")).getImage();
                    imgX += 10 * speed;
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_3.png")).getImage();
                    imgX += 10 * speed;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }

    public void setSpeed(String mapKey) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("left");
        keys.add("space");
        keys.add("right");

//        if (mapKey ==
//        리스트에 좌 스페이스 우
//        틀린값이면 스피드 1, 맞는 값이면 ++
        //어레이리스트

    }

    public void paint(Graphics g) {
        g.drawImage(pool, 0, 0, 990, 760, this);
        g.drawImage(stroke, imgX, imgY[4], 145, 80, this);
        repaint();
    }
}
