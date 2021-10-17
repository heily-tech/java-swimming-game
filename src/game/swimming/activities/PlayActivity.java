package game.swimming.activities;


import game.swimming.MainActivity;

import javax.swing.*;

public class PlayActivity extends JPanel {
    private MainActivity main;
    private SelectModeActivity selectModeActivity;

    public PlayActivity(MainActivity main) {
        this.main = main;

        setOpaque(false);
        setLayout(null);
        setVisible(true);

        /*
         템스피드 | 거리 | 네트워킹 | 영법
         */
    }
}
