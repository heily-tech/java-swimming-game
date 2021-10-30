package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.swimming.activities.SelectStrokeActivity.strokeChooseNum;

public class SelectModeActivity extends JPanel implements ActionListener {
    public static int backupNum = 0;
    JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, reBtn;
    JButton chosenBtn;
    private MainActivity main;
    private SelectStrokeActivity selectStrokeActivity;
    private swimmerThread swimmerThread;
    private JOptionPane nullSelection;


    public SelectModeActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        btnInit();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singleBtn) {
            strokeChooseNum += 10;
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == indivBtn){
            strokeChooseNum += 20;
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == grpBtn){
            strokeChooseNum += 30;
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == speedBtn) {
            strokeChooseNum += 1;
            speedBtn.setEnabled(false);
            itemBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == itemBtn){
            strokeChooseNum += 2;
            speedBtn.setEnabled(false);
            itemBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == chosenBtn) {
            if (strokeChooseNum == backupNum) {
                nullSelection.showMessageDialog(null, "모드를 선택해주세요.");
            } else {
                new swimmerThread();
                main.dispose();
                System.out.println(strokeChooseNum);
                //num에 따라서 게임 모드 달라지는 메소드 만들기
            }
        } else if (e.getSource() == reBtn) {
            strokeChooseNum = backupNum;
            singleBtn.setEnabled(true);
            indivBtn.setEnabled(true);
            grpBtn.setEnabled(true);
            speedBtn.setEnabled(true);
            itemBtn.setEnabled(true);
            System.out.println(strokeChooseNum);
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

        reBtn = new JButton("다시 선택");
//        ImageIcon reImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        reBtn.setIcon(reImg);
        reBtn.addActionListener(this);
        reBtn.setBounds(700, 180, 100, 45);
        add(reBtn);

        chosenBtn = new JButton("게임 시작");
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(180, 650, 100, 45);
        add(chosenBtn);
    }
}
