package game.swimming;

<<<<<<< Updated upstream
import game.swimming.activities.PlayActivity;
import game.swimming.activities.SelectModeActivity;
import game.swimming.activities.SelectStrokeActivity;
import game.swimming.activities.initActivity;
=======
import game.swimming.activities.SelectModeActivity;
import game.swimming.activities.SelectStrokeActivity;
import game.swimming.activities.initActivity;
import game.swimming.activities.swimmerThread;

>>>>>>> Stashed changes
import javax.swing.*;

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    initActivity initActivity;
    SelectStrokeActivity selectStrokeActivity;
    SelectModeActivity selectModeActivity;
    swimmerThread swimmerThread;



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
<<<<<<< Updated upstream
        }else if (panelName.equals("PlayActivity")) {
            getContentPane().removeAll();
            getContentPane().add(playActivity);
            revalidate();
            repaint();
=======
>>>>>>> Stashed changes
        }
    }
}
