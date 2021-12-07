package ClientEx.activities;

import ClientEx.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rankActivity extends JFrame {

    public rankActivity(MainActivity main) {
        setSize(400, 500);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.RED);

        JButton backBtn = new JButton();
        backBtn.setBounds(20, 20, 55, 48);
        ImageIcon backImg = new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png"));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setIcon(backImg);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                main.change("SelectStrokeActivity");
            }
        });
        add(backBtn);

        this.setFocusable(true);
        this.setVisible(true);


    }
}
