package game.swimming.activities;


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
    }
}
