package game.swimming.activities;

import game.swimming.MainActivity;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class swimmerThread extends JFrame implements KeyListener {
    private int rank = 1;

    public swimmerThread() {
        setTitle("My Swimming Game");
        setSize(1000, 800);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 1; i <= 4; i++) {
            Thread pcThread = new MySwimmerThread("pc"+i,10, i * 75);
            pcThread.start();
        }
        for (int i = 5; i <= 7; i++) {
            Thread pcThread = new MySwimmerThread("pc"+i,10, i * 75 + 75);
            pcThread.start();
        }
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            System.out.println("아래");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class MySwimmerThread extends Thread {
        String pc_name = "";
        private JLabel label, countLabel;
        private int x, y;

        public MySwimmerThread(String pc_name, int x, int y) {
            this.pc_name = pc_name;
            this.x = x;
            this.y = y;
            label = new JLabel();
            label.setIcon(new ImageIcon("car1.png"));
            label.setBounds(x, y, 100, 100);
            add(label);
//            countLabel = new JLabel();
//            countLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/3.png")));
//            countLabel.setBounds(350, 226, 300, 350);
//            add(countLabel);
        }

        @Override
        public void run() {
            try {
                countLabel = new JLabel();
                countLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/3.png")));
                countLabel.setBounds(425, 210, 300, 350);
                add(countLabel);
                Thread.sleep(1000);
                countLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/2.png")));
                Thread.sleep(1000);
                countLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/1.png")));
                Thread.sleep(1000);
                countLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/start.png")));
                countLabel.setBounds(290, 210, 450, 350);
                Thread.sleep(1000);
                countLabel.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 600; i++) {
                try {
                    x += 15 * Math.random();
                    label.setBounds(x, y, 100, 100);
                    int num = i % 4;
                    switch (num) {
                        case 1:  label.setIcon(new ImageIcon("car1.png")); break;
                        case 2:  label.setIcon(new ImageIcon("car2.png")); break;
                        case 3:  label.setIcon(new ImageIcon("car3.png")); break;
                        case 4:  label.setIcon(new ImageIcon("car2.png")); break;
                    }
                    if (x >= 900) {
                        System.out.println(rank + "등은 " + pc_name);
                        this.x = 900;
                        rank++;
                        break;
                    }
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        swimmerThread thread = new swimmerThread();
    }

}
