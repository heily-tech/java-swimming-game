package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectModeActivity extends JPanel implements ActionListener {
    JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, reBtn;
    JButton chosenBtn;
    private MainActivity main;
    private SelectStrokeActivity selectStrokeActivity;


    public SelectModeActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setVisible(true);
        btnInit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singleBtn)
            SelectStrokeActivity.strokeChooseNum += 5;
        else if (e.getSource() == indivBtn)
            SelectStrokeActivity.strokeChooseNum += 6;
        else if (e.getSource() == grpBtn)
            SelectStrokeActivity.strokeChooseNum += 7;

        if (e.getSource() == chosenBtn) {
            main.change("PlayActivity");
            System.out.println(SelectStrokeActivity.strokeChooseNum);
        }
    }

    void btnInit() {
        singleBtn = new JButton("싱글모드");
//        ImageIcon singleImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        singleBtn.setIcon(singleImg);
        singleBtn.addActionListener(this);
        singleBtn.setBounds(180, 180, 100, 45);
        add(singleBtn);

        indivBtn = new JButton("개인전");
//        ImageIcon indivImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        indivBtn.setIcon(indivImg);
        indivBtn.addActionListener(this);
        indivBtn.setBounds(300, 180, 100, 45);
        add(indivBtn);

        grpBtn = new JButton("단체전");
//        ImageIcon grpImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        grpBtn.setIcon(grpImg);
        grpBtn.addActionListener(this);
        grpBtn.setBounds(420, 180, 100, 45);
        add(grpBtn);

        speedBtn = new JButton("스피드전");
//        ImageIcon speedImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        speedBtn.setIcon(speedImg);
        speedBtn.addActionListener(this);
        speedBtn.setBounds(180, 230, 100, 45);
        add(speedBtn);

        itemBtn = new JButton("아이템전");
//        ImageIcon itemImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        itemBtn.setIcon(itemImg);
        itemBtn.addActionListener(this);
        itemBtn.setBounds(300, 230, 100, 45);
        add(itemBtn);

        chosenBtn = new JButton("게임시작");
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(180, 650, 100, 45);
        add(chosenBtn);
    }
}
