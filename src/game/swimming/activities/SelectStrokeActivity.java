package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectStrokeActivity extends JPanel implements ActionListener {
    public static String strokeChooseNum = "";
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    JButton freeBtn, backBtn, btflyBtn, brstBtn, nextBtn;

    public SelectStrokeActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setVisible(true);
        btnInit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == freeBtn)
            strokeChooseNum += "1";
        else if (e.getSource() == backBtn)
            strokeChooseNum += "2";
        else if (e.getSource() == btflyBtn)
            strokeChooseNum += "3";
        else if (e.getSource() == brstBtn)
            strokeChooseNum += "4";

        if (e.getSource() == nextBtn){
            main.change("SelectModeActivity");
        }
    }

    void btnInit() {
        freeBtn = new JButton("자유형");
//        ImageIcon freeImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        freeBtn.setIcon(freeImg);
        freeBtn.addActionListener(this);
        freeBtn.setBounds(180, 180, 100, 45);
        add(freeBtn);

        backBtn = new JButton("배영");
//        ImageIcon backImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        backBtn.setIcon(backImg);
        backBtn.addActionListener(this);
        backBtn.setBounds(300, 180, 100, 45);
        add(backBtn);

        btflyBtn = new JButton("접영");
//        ImageIcon btflyImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        btflyBtn.setIcon(btflyImg);
        btflyBtn.addActionListener(this);
        btflyBtn.setBounds(420, 180, 100, 45);
        add(btflyBtn);

        brstBtn = new JButton("평영");
//        ImageIcon brstImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        brstBtn.setIcon(brstImg);
        brstBtn.addActionListener(this);
        brstBtn.setBounds(540, 180, 100, 45);
        add(brstBtn);

        nextBtn = new JButton("=>");
//        ImageIcon nextImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        nextBtn.setIcon(nextImg);
        nextBtn.addActionListener(this);
        nextBtn.setBounds(180, 400, 100, 45);
        add(nextBtn);

    }

    public String getStrokeChooseNum() {
        return strokeChooseNum;
    }
}
