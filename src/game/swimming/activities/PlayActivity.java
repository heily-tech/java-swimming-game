package game.swimming.activities;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.javafx.binding.StringFormatter;
import game.swimming.MainActivity;
import game.swimming.activities.RankActivity;
import game.swimming.strokes.backStroke;
import game.swimming.strokes.breastStroke;
import game.swimming.strokes.butterfly;
import game.swimming.strokes.freestyle;

import static game.swimming.MainActivity.SPEED;
import static game.swimming.strokes.freestyle.setSpeed;

public class PlayActivity extends JPanel {
    public static ArrayList<String> strokeChooseNum = new ArrayList<>();
    public static String strokeName;
    private MainActivity main;
    private int imgX, pcX, rank = 1;
    private int[] pcY = {5, 105, 198, 290, 385, 480, 573, 668};
    private boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false, counterStat = false, gameStat = false;
    Image stroke, countImg;
    JLabel pcStroke = new JLabel();
    ArrayList<String> keys = new ArrayList<>();

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setFocusable(true);
        setVisible(true);

        new checkThread().start();
    }

    class checkThread extends Thread {
        @Override
        public void run() {
            while (!main.gameStatus) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (main.gameStatus) {
                try {
                    backgroundMusic.change("res/sfxs/play1.wav");
                    backgroundMusic.play();
                    Thread.sleep(100);
                    new playThread().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class playThread extends Thread {
        @Override
        public void run() {
            try {
                stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png"));
                counterStat = true;

                Thread.sleep(100);
                countImg = new ImageIcon(MainActivity.class.getResource("res/3.png")).getImage();
                Thread.sleep(1000);
                countImg = new ImageIcon(MainActivity.class.getResource("res/2.png")).getImage();
                Thread.sleep(1000);
                countImg = new ImageIcon(MainActivity.class.getResource("res/1.png")).getImage();
                Thread.sleep(1000);
                gameStat = true;
                counterStat = false;

                userThread user = new userThread();
                user.start();

                for (int i = 1; i <= 8; i++) {
                    if (i == 3)
                        continue;
                    pcThread pc = new pcThread("pc" + i, pcY[i-1]);
                    pc.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class pcThread extends Thread {
        String pc_name = "";
        int pc_y;

        pcThread(String pc_name, int pc_y) {
            this.pc_name = pc_name;
            this.pc_y = pc_y;
            pcStroke = new JLabel();
            pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png"));
            pcStroke.setBounds(pcX, pc_y, 145, 80);
            add(pcStroke);
        }

        @Override
        public void run() {
            for (int i = 0; i < 600; i++) {
                try {
                    pcX += 10 * Math.random();
                    changeImage(strokeName, i);
                    Thread.sleep(300);
                    if (pcX >= 840) {
                        pcX = 840;
                        new RankActivity(main);
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void changeImage(String strokeName, int i) {
            if (strokeName.equals("freestyle") || strokeName.equals("backStroke")) {
                if (i % 2 == 0)
                    pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png"));
                else
                    pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_2.png"));
            }  else if (strokeName.equals("butterfly") || strokeName.equals("breastStroke")) {
                if (i % 3 == 0)
                    pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png"));
                else if (i % 3 == 1)
                    pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_2.png"));
                else
                    pcStroke.setIcon(new ImageIcon("res/strokes/" + strokeName + "/" + strokeName + "_pc_3.png"));
            }
        }
    }

    class userThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    if (strokeName.equals("freestyle"))
                        freestyleKeySet();
                    else if (strokeName.equals("backStroke"))
                        backStrokeKeySet();
                    else if (strokeName.equals("butterfly"))
                        butterflyKeySet();
                    else if (strokeName.equals("breastStroke"))
                        breastStrokeKeySet();
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

    private void freestyleKeySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freestyle.setSpeed("right");
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                    imgX += 10 * SPEED;
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
                freestyle.setSpeed("space");
                if (spacePrsd == false) {
                    imgX += 10 * SPEED;
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
                freestyle.setSpeed("left");
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2.png")).getImage();
                    imgX += 10 * SPEED;
                    leftPrsd = true;
                    rightPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }
    private void backStrokeKeySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backStroke.setSpeed("left");
                if (leftPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_user_1.png")).getImage();
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
                backStroke.setSpeed("right");
                if (rightPrsd == false) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/backStroke/backstroke_user_2.png")).getImage();
                    imgX += 10;
                    rightPrsd = true;
                    leftPrsd = false;
                    spacePrsd = true;
                }
            }
        });
    }
    private void butterflyKeySet() {
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_user_1.png")).getImage();
                    if (leftPrsd == true && rightPrsd == false) {
                        imgX += 10;
                        butterfly.setSpeed("side");
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
                    butterfly.setSpeed("up");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_user_2.png")).getImage();
                    imgX += 10;
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
                butterfly.setSpeed("space");
                if (spacePrsd == false) {
                    System.out.println("space");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_user_3.png")).getImage();
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
                butterfly.setSpeed("down");
                if (downPrsd == false) {
                    System.out.println("down");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/butterfly/butterflystroke_user_3.png")).getImage();
                    imgX += 10;
                    rightPrsd = false;
                    leftPrsd = false;
                    upPrsd = true;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
    }
    private void breastStrokeKeySet() {
        //위 좌 스페이스 아래
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upPrsd == false) {
                    breastStroke.setSpeed("up");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_user_1.png")).getImage();
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
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_user_2.png")).getImage();
                    if (leftPrsd == true && rightPrsd == false) {
                        imgX += 10;
                        breastStroke.setSpeed("side");
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
                breastStroke.setSpeed("space");
                if (spacePrsd == false) {
                    System.out.println("space");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_user_3.png")).getImage();
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
                breastStroke.setSpeed("down");
                if (downPrsd == false) {
                    System.out.println("down");
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/breastStroke/breaststroke_user_1.png")).getImage();
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


    public void paint(Graphics g) {
        g.drawImage(main.pool, 0, 0, 990, 760, this);
        if (gameStat)
            g.drawImage(stroke, imgX, 290, 145, 80, this);
        if (counterStat)
            g.drawImage(countImg, 420, 250, 80, 150, this);

        repaint();
    }
}