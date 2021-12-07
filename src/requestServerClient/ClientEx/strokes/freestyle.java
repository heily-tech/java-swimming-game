package ClientEx.strokes;

import ClientEx.MainActivity;
import ClientEx.Player;
import ClientEx.activities.SelectModeActivity;
import ClientEx.activities.rankActivity;
import ClientEx.network.tcpClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class freestyle extends JPanel {
    private MainActivity main;
    Image pool = new ImageIcon(MainActivity.class.getResource("res/poolBG.gif")).getImage();
    private int imgX, distance = 0, speed = 1;
    private int[] imgY = {5, 105, 198, 290, 385, 480, 573, 668};
    boolean leftPrsd = false, rightPrsd = false, spacePrsd = false;
    tcpClient client;
    
    public freestyle(MainActivity main) {
        this.main = main;
        main.setGameState(1);
        //main.getUdpClient().reaminStop();
        setOpaque(false);
        setLayout(null);
        freestyleThread free = new freestyleThread();
        enterUserThread enter = new enterUserThread(this);
        enter.start();
        free.start();
        setVisible(true);
        main.getMe().setStroke(new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage());
    }

    
    class enterUserThread extends Thread{
    	freestyle fs;
    	
    	public enterUserThread(freestyle fs) {
    		this.fs = fs;
    	}
    	
    	public void run()
    	{
    		while(true) {
    			
				fs.repaint();
    			try {
    				if(null != main.getOther())
    				{
    					//System.out.println("상대방 들어옴");
    					main.getOther().setStroke(new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage());
    					//break;
    				} else {
    					System.out.println("상대방 없음");
    					//break;
    				}
    			}catch(Exception e)
    			{
    				
    			}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
    
    class freestyleThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    keySet();
                    Thread.sleep(1000);
                    if (main.getMe().getX() >= 840) {
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
                    main.getMe().setStroke(new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage());
                    main.getMe().setX(main.getMe().getX() + 10 * speed);
                    String data = "@MOVE" + main.getMe().getName() + ":" + main.getMe().getX() + ":RIGHT";
                    System.out.println("LEFT: " + data + ":LEFT");
                    main.getClient().send(data);
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
                	main.getMe().setStroke(new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_2.png")).getImage());
                    main.getMe().setX(main.getMe().getX() + 10 * speed);
                    String data = "@MOVE" + main.getMe().getName() + ":" + main.getMe().getX() + ":RIGHT";
                    System.out.println("SPACE: " + data + ":SPACE");
                    main.getClient().send(data);
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
                	main.getMe().setStroke(new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_3.png")).getImage());
                    main.getMe().setX(main.getMe().getX() + 10 * speed);
                    String data = "@MOVE" + main.getMe().getName() + ":" + main.getMe().getX() + ":RIGHT";
                    System.out.println("RIGHT: " + data + ":RIGHT");
                    main.getClient().send(data);
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
        g.drawImage(main.getMe().getStroke(), main.getMe().getX(), main.getMe().getY(), 145, 80, this);
        if(null != main.getOther())
        {
            g.drawImage(main.getOther().getStroke(), main.getOther().getX(), main.getOther().getY(), 145, 80, this);
        }
        repaint();
    }
}
