package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;

public class PlayActivity extends JFrame {

    public PlayActivity() {
        setTitle("My Swimming Game : Play");
        setSize(MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Image swimmingPool = new ImageIcon(
                MainActivity.class.getResource("../res/freestyle.gif")
            ).getImage();

        }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
}
