package game.swimming;

import game.swimming.activities.PlayActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class test extends JFrame {
    PlayActivity playActivity;
    test() {
        setTitle("My Swimming Game");
        setSize(1000, 800);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MyPanel());
        setVisible(true);
    }

    class MyPanel extends JPanel {
        MyPanel() {
            setBackground(Color.WHITE);

            String left = "LEFT";
            getInputMap().put(KeyStroke.getKeyStroke("LEFT"), left);
            getActionMap().put(left, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(left);
                }
            });
            String right = "RIGHT";
            getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), right);
            getActionMap().put(right, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(right);
                }
            });
            setVisible(true);
        }
    }

//        bgPanel = new JPanel() {
//            public void paint(Graphics g) {
//                super.paint(g);
//                g.drawImage(pool, 0, 0, 990, 760, this);
//                repaint();
//            }
//        };
//        bgPanel.setSize(1000, 800);
//        bgPanel.setVisible(true);
//        add(bgPanel);

    //        charPanel = new JPanel() {
//            public void paint(Graphics g) {
//                if (setImage == 1)
//                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_1.png")).getImage();
//                else if (setImage == 3)
//                    free = new ImageIcon(MainActivity.class.getResource("res/strokes/freestyle_3.png")).getImage();
//                g.drawImage(free, imgX, 385, 145, 80, this);
//                //5, 105, 200, 290, 385
//                repaint();
//            }
//        };
//        charPanel.setSize(1000, 800);
//        charPanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
//        charPanel.getActionMap().put("left", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("left");
//                setImage = 1;
//                imgX += 10;
//            }
//        });
//        charPanel.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
//        charPanel.getActionMap().put("right", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("right");
//                setImage = 1;
//                imgX += 10;
//            }
//        });
//        charPanel.setVisible(true);
//        add(charPanel);

    public static void main(String[] args) {
        test t = new test();
    }
}
