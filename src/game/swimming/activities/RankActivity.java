package game.swimming.activities;

import game.swimming.MainActivity;
import game.swimming.strokes.backStroke;
import game.swimming.strokes.breastStroke;
import game.swimming.strokes.butterfly;
import game.swimming.strokes.freestyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.swimming.MainActivity.GAME_RESULT;

public class RankActivity extends JFrame {
    JTextArea area;
    JButton backBtn;

    public RankActivity(MainActivity main) {
        Color c = new Color(120, 209, 255);
        JPanel panel = new JPanel();
        panel.setBackground(c);
        panel.setLayout(null);

        backBtn = new JButton();
        backBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png")));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                backgroundMusic.stop();
                backgroundMusic.change("res/sfxs/start2.wav");
                backgroundMusic.play();
                reset();
                main.change("UserActivity");
            }
        });
        backBtn.setBounds(12, 18, 58, 48);
        backBtn.setFocusable(false);
        panel.add(backBtn);

        area = new JTextArea(GAME_RESULT);
        area.setEditable(false);
        area.setOpaque(false);
        area.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
        area.setBounds(52, 139, 300, 350);
        panel.add(area);


        this.add(panel);
        this.setSize(400, 500);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //화면 중앙에 창 위치
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    static void reset() {
        SelectStrokeActivity.reset();
        SelectModeActivity.reset();
        freestyle.imgReset();
        backStroke.imgReset();
        butterfly.imgReset();
        breastStroke.imgReset();
        PlayActivity.imgReset();
    }
}
