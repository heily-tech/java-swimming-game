package game.swimming;

import game.swimming.activities.*;
import game.swimming.strokes.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    static tcpClient client;
    initActivity initActivity;
    SelectStrokeActivity selectStrokeActivity;
    SelectModeActivity selectModeActivity;
    PlayActivity playActivity;
    UserActivity userActivity;
    freestyle freestyle;
    backStroke backStroke;
    butterfly butterfly;
    breastStroke breastStroke;

    public static void main(String[] args) {
        MainActivity main = new MainActivity();

        main.setTitle("My Swimming Game");
        main.setSize(GAME_WIDTH, GAME_HEIGHT);
        main.setResizable(false);
        main.setLocationRelativeTo(null); //화면 중앙에 창 위치
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main.client = new tcpClient();
        main.initActivity = new initActivity(main, client);
        main.selectStrokeActivity = new SelectStrokeActivity(main);
        main.selectModeActivity = new SelectModeActivity(main);
        main.playActivity = new PlayActivity(main);
        main.userActivity = new UserActivity(main);
        main.freestyle = new freestyle(main);
        main.backStroke = new backStroke(main);
        main.butterfly = new butterfly(main);
        main.breastStroke = new breastStroke(main);

        main.add(main.initActivity);

        main.setVisible(true);
    }

    public void sfx(String fileName) {
        try {
            String path = MainActivity.class.getResource("").getPath();
            File file = new File(path + fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            if (clip.isRunning()) {
                clip.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void change(String panelName) {
        if (panelName.equals("initActivity")) {
            getContentPane().removeAll();
            getContentPane().add(initActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("SelectStrokeActivity")) {
            getContentPane().removeAll();
            getContentPane().add(selectStrokeActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("SelectModeActivity")) {
            getContentPane().removeAll();
            getContentPane().add(selectModeActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("PlayActivity")) {
            getContentPane().removeAll();
            getContentPane().add(playActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("UserActivity")) {
            getContentPane().removeAll();
            getContentPane().add(userActivity);
            revalidate();
            repaint();
        } else if (panelName.equals("freestyle")) {
            getContentPane().removeAll();
            getContentPane().add(freestyle);
            revalidate();
            repaint();
        } else if (panelName.equals("backStroke")) {
            getContentPane().removeAll();
            getContentPane().add(backStroke);
            revalidate();
            repaint();
        } else if (panelName.equals("butterfly")) {
            getContentPane().removeAll();
            getContentPane().add(butterfly);
            revalidate();
            repaint();
        } else if (panelName.equals("breastStroke")) {
            getContentPane().removeAll();
            getContentPane().add(breastStroke);
            revalidate();
            repaint();
        }
    }
}