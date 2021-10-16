package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectActivity extends JPanel implements ActionListener {
    int strokeChooseNum;
    JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, distBtn;
    JButton chosenBtn;

    public SelectActivity() {
        setOpaque(false);
        setLayout(null);
        btnInit();

        //모드
        //영법 거리 선택 후 싱글, 개인, 단체
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if ~Btn Pressed
        strokeChooseNum = 1;
    }

    void btnInit() {
        singleBtn = new JButton("싱글모드");
        //ImageIcon singleImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
        //singleBtn.setIcon(singleImg);
        singleBtn.addActionListener(this);
        singleBtn.setBounds(180, 180, 100, 45);
        add(singleBtn);

        chosenBtn = new JButton("게임시작");
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(180, 650, 100, 45);
        add(chosenBtn);

    }
}
