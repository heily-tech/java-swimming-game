package game.swimming;

import game.swimming.activities.initActivity;
import javax.swing.*;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    public MainActivity() {
        setTitle("My Swimming Game");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        add(new initActivity());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainActivity();
    }
}
