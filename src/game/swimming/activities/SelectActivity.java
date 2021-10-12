package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;

public class SelectActivity extends JFrame {

    public SelectActivity() {
        setTitle("My Swimming Game : SELECT");
        setSize(MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
