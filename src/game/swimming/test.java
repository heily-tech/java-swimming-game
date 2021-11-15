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

    public static void main(String[] args) {
        test t = new test();
    }
}
