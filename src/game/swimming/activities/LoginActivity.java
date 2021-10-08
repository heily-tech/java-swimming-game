package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;

public class LoginActivity extends JFrame {

    public LoginActivity() {
        setTitle("My Swimming Game : Login");
        setSize(MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
