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
        자유형 1 배영 2 접영 3 평영 4
        싱글 1 개인 2 그룹 3
        스피드 1 아이템 2
         */
    }
}
