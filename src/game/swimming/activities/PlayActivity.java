package game.swimming.activities;

import javax.swing.*;

import static game.swimming.MainActivity.GAME_HEIGHT;
import static game.swimming.MainActivity.GAME_WIDTH;

public class PlayActivity extends JFrame {
    public PlayActivity() {
        setTitle("My Swimming Game : PLAY");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
