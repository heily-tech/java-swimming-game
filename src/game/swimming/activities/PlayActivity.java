package game.swimming.activities;


import game.swimming.strokes.backStroke;
import game.swimming.strokes.breastStroke;
import game.swimming.strokes.butterfly;
import game.swimming.strokes.freestyle;

import javax.swing.*;

public class PlayActivity extends JPanel {
    SelectActivity i = new SelectActivity();
    int mode;

    public PlayActivity() {
        System.out.println(mode);
        setMode();
        System.out.println(mode);
    }

    public void setMode() {
        mode = i.strokeChooseNum;
        //thread 구현해야됨
        if (mode == 1) {
            freestyle free = new freestyle();
        } else if (mode == 2) {
            backStroke back = new backStroke();
        } else if (mode == 3) {
            butterfly btfly = new butterfly();
        } else if (mode == 4) {
            breastStroke brst = new breastStroke();
        }
    }
}
