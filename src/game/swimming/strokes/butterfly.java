package game.swimming.strokes;

import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class butterfly extends JPanel {
    private MainActivity main;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    Image stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_org_1.png")).getImage();
    private int imgX, distance = 0;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false;

    public butterfly(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);

        btflyThread btfly = new btflyThread();
        btfly.start();

        setBackground(Color.YELLOW);
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
                if (leftPrsd == false) {
                    System.out.println("left");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_org_1.png")).getImage();
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
                    System.out.println("space");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_org_2.png")).getImage();
                    imgX += 10;
                    leftPrsd = true;
                    rightPrsd = false;
                    spacePrsd = true;
                }
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rightPrsd == false) {
                    System.out.println("right");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_org_3.png")).getImage();
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