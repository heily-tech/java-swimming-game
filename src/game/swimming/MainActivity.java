package game.swimming;

import game.swimming.activities.*;
import game.swimming.strokes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    initActivity initActivity;
    SelectStrokeActivity selectStrokeActivity;
    SelectModeActivity selectModeActivity;
    PlayActivity playActivity;
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

        main.initActivity = new initActivity(main);
        main.selectStrokeActivity = new SelectStrokeActivity(main);
        main.selectModeActivity = new SelectModeActivity(main);
        main.playActivity = new PlayActivity(main);
        main.freestyle = new freestyle(main);
        main.backStroke = new backStroke(main);
        main.butterfly = new butterfly(main);
        main.breastStroke = new breastStroke(main);

        main.add(main.initActivity);

        main.setVisible(true);
    }

    public void change(String panelName) {
        if (panelName.equals("SelectStrokeActivity")) {
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
