package game.swimming.activities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.swimming.MainActivity;
import game.swimming.strokes.backStroke;
import game.swimming.strokes.breastStroke;
import game.swimming.strokes.butterfly;
import game.swimming.strokes.freestyle;

import static game.swimming.MainActivity.*;
import static game.swimming.activities.RunningTimer.*;

public class PlayActivity extends JPanel {
    public static String strokeName;
    private static int imgX, rank = 1;
    private static boolean leftPrsd = false, rightPrsd = false, spacePrsd = false, upPrsd = false, downPrsd = false,
            counterStat = false, isCounterFinished = false, userDist = false;
    private MainActivity main;
    private static int[] pcXs = new int[7];
    private int[] pcYs = {5, 105, 198, 385, 480, 573, 668};
    private pcThread[] pcThreads = new pcThread[7];
    private static boolean[] pcDists = {false, false, false, false, false, false, false};
    public static Image stroke = new ImageIcon(MainActivity.class.getResource("res/null.png")).getImage();
    static boolean gameStatus = true;
    boolean isPaused = true;
    int pauseOption;
    Image countImg = new ImageIcon(MainActivity.class.getResource("res/null.png")).getImage();
    Image pause = new ImageIcon(MainActivity.class.getResource("res/btns/pause1.png")).getImage();
    Image exitBtn = new ImageIcon(MainActivity.class.getResource("res/btns/뒤로가기1.png")).getImage();
    Image resumeBtn = new ImageIcon(MainActivity.class.getResource("res/btns/게임하기1.png")).getImage();
    Image NUll = new ImageIcon(MainActivity.class.getResource("res/null.png")).getImage();
    JPanel pausePanel, exitPanel, resumePanel;

    public PlayActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setFocusable(true);
        setVisible(true);

        new checkThread().start();

        pausePanel = new JPanel();
        pausePanel.setBounds(10, 10, 30, 30);
        pausePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.sfx("res/sfxs/select.wav");
                isPaused = true;
                leftPrsd = true;
                rightPrsd = true;
                spacePrsd = true;
                upPrsd = true;
                downPrsd = true;
                pauseOption = 1;
                optionInit();
                backgroundMusic.stop();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pause = new ImageIcon(MainActivity.class.getResource("res/btns/pause2.png")).getImage();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pause = new ImageIcon(MainActivity.class.getResource("res/btns/pause1.png")).getImage();
            }
        });
        add(pausePanel);

        exitPanel = new JPanel();
        exitPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.sfx("res/sfxs/reselect.wav");
                pauseOption = 2;
                for (int i = 0; i < pcThreads.length; i++)
                    pcXs[i] = 0;
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    return;
                }
                main.change("UserActivity");
                for (int i = 0; i < pcThreads.length; i++)
                    pcThreads[i] = null;
                SelectStrokeActivity.reset();
                SelectModeActivity.reset();
                RankActivity.reset();
                backgroundMusic.stop();
                backgroundMusic.change("res/sfxs/lobby.wav");
                backgroundMusic.play();
                main.playActivity = null;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn = new ImageIcon(MainActivity.class.getResource("res/btns/뒤로가기2.png")).getImage();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn = new ImageIcon(MainActivity.class.getResource("res/btns/뒤로가기1.png")).getImage();
            }
        });

        resumePanel = new JPanel();
        resumePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.sfx("res/sfxs/select_with_reverb.wav");
                pauseOption = 3;
                isPaused = false;
                leftPrsd = false;
                rightPrsd = false;
                spacePrsd = false;
                upPrsd = false;
                downPrsd = false;
                backgroundMusic.stop();
                backgroundMusic.change("res/sfxs/play1.wav");
                backgroundMusic.play();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resumeBtn = new ImageIcon(MainActivity.class.getResource("res/btns/게임하기2.png")).getImage();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeBtn = new ImageIcon(MainActivity.class.getResource("res/btns/게임하기1.png")).getImage();
            }
        });
    }

    private void optionInit() {
        exitPanel.setBounds(382, 310, 200, 50);
        add(exitPanel);
        exitPanel.setVisible(true);

        resumePanel.setBounds(382, 390, 200, 50);
        add(resumePanel);
        resumePanel.setVisible(true);
    }

    class checkThread extends Thread {
        @Override
        public void run() {
            while (!main.singleGameStatus) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (main.singleGameStatus) {
                try {
                    backgroundMusic.change("res/sfxs/play1.wav");
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
                for (int i = 0; i < 7; i++)
                    pcStrokes[i] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png")).getImage();

                counterStat = true;
                isPaused = true;
                isCounterFinished=false;
                Thread.sleep(100);
                countImg = new ImageIcon(MainActivity.class.getResource("res/3.png")).getImage();
                main.sfx("res/sfxs/beep.wav");
                Thread.sleep(1000);
                countImg = new ImageIcon(MainActivity.class.getResource("res/2.png")).getImage();
                main.sfx("res/sfxs/beep.wav");
                Thread.sleep(1000);
                countImg = new ImageIcon(MainActivity.class.getResource("res/1.png")).getImage();
                main.sfx("res/sfxs/beep.wav");
                Thread.sleep(1000);
                isCounterFinished=true;
                countImg = new ImageIcon(MainActivity.class.getResource("res/start.png")).getImage();
                Thread.sleep(1000);
                backgroundMusic.play();
                counterStat = false;
                isPaused = false;

                userThread user = new userThread();
                user.start();

                for (int i = 0; i < 7; i++) {
                    pcThread pc = new pcThread(i, pcYs[i]);
                    pcThreads[i] = pc;
                }

                for (int i = 0; i < 7; i++)
                    pcThreads[i].start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class pcThread extends Thread {
        String pc_name = "";
        int pc_x, pc_y, pc_num;

        pcThread(int num, int pc_y) {
            this.pc_num = num + 1;
            this.pc_name = "PC" + this.pc_num;
            this.pc_y = pc_y;
        }

        @Override
        public void run() {
            try {
                startTimer(pc_num - 1);
                Thread.sleep(100);
                for (int i = 0; i < 600; i++) {
                    while (true) {
                        if (isPaused)
                            Thread.sleep(100);
                        else
                            break;
                    }
                    if (!dist) {
                        pcRunningTimes(pc_num - 1);
                        Thread.sleep(300);
                        pc_x += 25 * Math.random();
                        pcXs[pc_num - 1] = pc_x;
                        changeImage(strokeName, i, pc_num);
                        if (pc_x >= 840) {
                            GAME_RESULT += (rank + "등 | " + pc_name + " | " + pcRunningTimes(pc_num - 1) + "\n");
                            pc_x = 840;
                            rank++;
                            break;
                        } else if (!gameStatus) {
                            GAME_RESULT += ("   | " + pc_name + " | unfin.\n\n");
                            break;
                        }
                    } else if (dist && !pcDists[pc_num - 1]) {
                        pcRunningTimes(pc_num - 1);
                        Thread.sleep(300);
                        pc_x += 25 * Math.random();
                        pcXs[pc_num - 1] = pc_x;
                        changeImage(strokeName, i, pc_num);
                        if (pc_x >= 840)
                            pcDists[pc_num - 1] = true;
                        else if (!gameStatus) {
                            GAME_RESULT += ("   | " + pc_name + " | unfin.\n\n");
                            break;
                        }
                    } else if (pcDists[pc_num - 1]) {
                        pcRunningTimes(pc_num - 1);
                        Thread.sleep(300);
                        pc_x -= 25 * Math.random();
                        pcXs[pc_num - 1] = pc_x;
                        changeImage(strokeName, i, pc_num);
                        if (pc_x <= 0) {
                            GAME_RESULT += (rank + "등 | " + pc_name + " | " + pcRunningTimes(pc_num - 1) + "\n");
                            pc_x = 0;
                            rank++;
                            break;
                        } else if (!gameStatus) {
                            GAME_RESULT += ("   | " + pc_name + " | unfin.\n\n");
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void changeImage(String strokeName, int i, int p) {
            int pc = p - 1;
            if (strokeName.equals("freestyle") || strokeName.equals("backStroke")) {
                if (!pcDists[pc_num - 1]) {
                    if (i % 2 == 0)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png")).getImage();
                    else
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_2.png")).getImage();
                } else {
                    if (i % 2 == 0)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1_rev.png")).getImage();
                    else
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_2_rev.png")).getImage();
                }
            }  else if (strokeName.equals("butterfly") || strokeName.equals("breastStroke")) {
                if (!pcDists[pc_num - 1]) {
                    if (i % 3 == 0)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png")).getImage();
                    else if (i % 3 == 1)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_2.png")).getImage();
                    else
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_3.png")).getImage();
                } else {
                    if (i % 3 == 0)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1_rev.png")).getImage();
                    else if (i % 3 == 1)
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_2_rev.png")).getImage();
                    else
                        pcStrokes[pc] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_3_rev.png")).getImage();
                }
            }
        }
    }

    class userThread extends Thread {
        @Override
        public void run() {
            startTimer();
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

                    if (imgX >= 840 && !dist) {
                        GAME_RESULT += (rank++ + "등 | YOU | " + runningTime() + "\n");
                        gameStatus = false;
                        Thread.sleep(1000);
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        upPrsd = true;
                        downPrsd = true;
                        backgroundMusic.stop();
                        main.sfx("res/sfxs/end1.wav");
                        System.out.println(GAME_RESULT);
                        new RankActivity(main);
                        break;
                    } else if (imgX >= 840 && dist) {
                        userDist = true;
                    } else if (imgX <= 0 && userDist) {
                        GAME_RESULT += (rank++ + "등 | YOU | " + runningTime() + "\n");
                        gameStatus = false;
                        Thread.sleep(1000);
                        leftPrsd = true;
                        rightPrsd = true;
                        spacePrsd = true;
                        upPrsd = true;
                        downPrsd = true;
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

    private void freestyleKeySet() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freestyle.setSpeed("right");
                if (rightPrsd == false) {
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                        imgX += 4 * SPEED;
                    } else if (userDist){
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1_rev.png")).getImage();
                        imgX -= 4 * SPEED;
                    }
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
                    if (!userDist)
                        imgX += 4 * SPEED;
                    else if (userDist)
                        imgX -= 4 * SPEED;
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2.png")).getImage();
                        imgX += 4 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2_rev.png")).getImage();
                        imgX -= 4 * SPEED;
                    }
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
                if (!userDist) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                    imgX += 4 * SPEED;
                } else if (userDist) {
                    stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1_rev.png")).getImage();
                    imgX -= 4 * SPEED;
                }
                leftPrsd = true;
                rightPrsd = false;
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backStroke.setSpeed("right");
                if (rightPrsd == false) {
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2.png")).getImage();
                        imgX += 4 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2_rev.png")).getImage();
                        imgX -= 4 * SPEED;
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX += 9 * SPEED;
                            butterfly.setSpeed("side");
                        }
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1_rev.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX -= 9 * SPEED;
                            butterfly.setSpeed("side");
                        }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX += 9 * SPEED;
                            butterfly.setSpeed("up");
                        }
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2_rev.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX -= 9 * SPEED;
                            butterfly.setSpeed("up");
                        }
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3.png")).getImage();
                        imgX += 9 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3_rev.png")).getImage();
                        imgX -= 9 * SPEED;
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3.png")).getImage();
                        imgX += 9 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3_rev.png")).getImage();
                        imgX -= 9 * SPEED;
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                        imgX += 9 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1_rev.png")).getImage();
                        imgX -= 9 * SPEED;
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX += 9 * SPEED;
                            breastStroke.setSpeed("side");
                        }
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_2_rev.png")).getImage();
                        if (leftPrsd == true && rightPrsd == false) {
                            imgX -= 9 * SPEED;
                            breastStroke.setSpeed("side");
                        }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3.png")).getImage();
                        imgX += 9 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_3_rev.png")).getImage();
                        imgX -= 9 * SPEED;
                    }
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
                    if (!userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
                        imgX += 9 * SPEED;
                    } else if (userDist) {
                        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1_rev.png")).getImage();
                        imgX -= 9 * SPEED;
                    }
                    rightPrsd = true;
                    leftPrsd = true;
                    upPrsd = false;
                    spacePrsd = true;
                    downPrsd = true;
                }
            }
        });
    }

    public static void imgReset() {
        stroke = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_user_1.png")).getImage();
        imgX = 0;
        leftPrsd = false;
        rightPrsd = false;
        spacePrsd = false;
        upPrsd = false;
        downPrsd = false;

        userDist = false;
        for (int i = 0; i < 7; i++) {
            pcXs[i] = 0;
            pcDists[i] = false;
            pcStrokes[i] = new ImageIcon(MainActivity.class.getResource("res/strokes/" + strokeName + "/" + strokeName + "_pc_1.png")).getImage();
        }
        gameStatus = true;
        singleGameStatus = false;
    }

    public void paint(Graphics g) {
        g.drawImage(pool, 0, 0, 990, 760, this);
        if (main.singleGameStatus) {
            g.drawImage(stroke, imgX, 290, 145, 80, this);
            for (int i = 0; i < 7; i++)
                g.drawImage(pcStrokes[i], pcXs[i], pcYs[i], 145, 80, this);
        }
        if (counterStat)
            if(isCounterFinished)
                g.drawImage(countImg, 290, 310, 430, 70, this);
            else
                g.drawImage(countImg, 440, 250, 80, 150, this);

        if(!isPaused)
            g.drawImage(pause, 10, 10, 30, 30, this);
        else
            g.drawImage(NUll, 10, 10, 30, 30, this);

        if (pauseOption == 1) {
            g.setColor(new Color(174, 227, 255, 205));
            g.fillRect(330, 270, 300, 200);
            g.drawImage(exitBtn, 382, 295, 200, 75, this);
            g.drawImage(resumeBtn, 382, 375, 200, 75, this);
            if (pauseOption == 2 || pauseOption == 3)
                setVisible(false);
        }
        repaint();
    }
}