package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActivity extends JPanel {
    private MainActivity main;
    JButton go2play;
    Image background = new ImageIcon(MainActivity.class.getResource("res/userBG.png")).getImage();

    public UserActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setVisible(true);

        go2play = new JButton();
        go2play.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/다음1.png")));
        go2play.setBorderPainted(false);
        go2play.setContentAreaFilled(false);
        go2play.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/다음2.png")));
        go2play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.change("SelectStrokeActivity");
            }
        });
        go2play.setBounds(756, 660, 200, 75);
        add(go2play);
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
