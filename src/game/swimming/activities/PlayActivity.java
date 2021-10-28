package game.swimming.activities;


import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayActivity extends JPanel implements KeyListener {
    private MainActivity main;
    private SelectModeActivity selectModeActivity;

    public PlayActivity(MainActivity main) {
        this.main = main;

        setOpaque(false);
        setLayout(null);
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            System.out.println("위로");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            System.out.println("아래로");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            System.out.println("왼쪽");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            System.out.println("오른쪽");
    }
}
