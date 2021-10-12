package game.swimming.activities;

import game.swimming.MainActivity;

import java.awt.event.*;
import javax.swing.*;

//뒤로가기 버튼

public class SignUpActivity extends JFrame{
    JButton done;
    JLabel IDW, PWW;
    JTextField NID;
    JPasswordField NPW;
    public SignUpActivity() {
        JPanel Btns = new JPanel();
        setLayout(null);
        setBackground(0, 0, 255);
        IDW = new JLabel("NEW ID : ");
        NID = new JTextField();
        PWW = new JLabel("NEW PW : ");
        NPW = new JPasswordField();
        done = new JButton("DONE");
        add(IDW); //label
        IDW.setBounds(350, 300, 100, 45);
        add(NID); //label
        NID.setBounds(500, 305, 150, 30);
        add(PWW); //field
        PWW.setBounds(350, 400, 100, 45);
        add(NPW); //field
        NPW.setBounds(500, 405, 150, 30);
        add(done);
        done.setBounds(450, 500, 100, 35);
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setTitle("My Swimming Game : Sign UP");
        setSize(MainActivity.GAME_WIDTH, MainActivity.GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void setBackground(int i, int j, int k) {
        // TODO Auto-generated method stub

    }
}

