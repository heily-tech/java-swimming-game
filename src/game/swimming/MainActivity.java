package game.swimming;

import game.swimming.activities.LoginActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;

    MainActivity() {
        setTitle("My Swimming Game : Main");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setStartBtn();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainActivity();
    }

    void setStartBtn() {
        JButton test = new JButton();
        test.setBounds(350, 350, 300, 90);
        ImageIcon testImg = new ImageIcon(MainActivity.class.getResource("res/btnTest.png"));
        test.setIcon(testImg);
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginActivity();
            }
        });
        add(test);
    }

}
