package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.swimming.activities.SelectModeActivity.backupNum;

public class SelectStrokeActivity extends JPanel implements ActionListener {
    public static int strokeChooseNum = 0;
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    JButton freeBtn, backBtn, btflyBtn, brstBtn, nextBtn, dist100Btn, dist200Btn, reBtn;
    JLabel styleLabel, dLabel;
    private JOptionPane nullSelection;

    public SelectStrokeActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        setVisible(true);
        btnInit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == freeBtn) {
            strokeChooseNum += 1000;
<<<<<<< Updated upstream
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
=======
            btnSelected();
>>>>>>> Stashed changes
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == backBtn){
            strokeChooseNum += 2000;
<<<<<<< Updated upstream
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
=======
            btnSelected();
>>>>>>> Stashed changes
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == btflyBtn){
            strokeChooseNum += 3000;
<<<<<<< Updated upstream
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
=======
            btnSelected();
>>>>>>> Stashed changes
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == brstBtn){
            strokeChooseNum += 4000;
<<<<<<< Updated upstream
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
=======
            btnSelected();
>>>>>>> Stashed changes
            System.out.println(strokeChooseNum);
        }
        if (e.getSource() == dist100Btn) {
            strokeChooseNum += 100;
<<<<<<< Updated upstream
=======
            dist100Btn.setBackground(new Color(193, 213, 207));
>>>>>>> Stashed changes
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == dist200Btn){
            strokeChooseNum += 200;
<<<<<<< Updated upstream
=======
            dist200Btn.setBackground(new Color(193, 213, 207));
>>>>>>> Stashed changes
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == nextBtn){
            if (strokeChooseNum == 0) {
                nullSelection.showMessageDialog(null, "영법과 거리를 선택해주세요.");
            } else {
                main.change("SelectModeActivity");
                System.out.println(strokeChooseNum);
                backupNum = strokeChooseNum;
            }
        } else if (e.getSource() == reBtn) {
            strokeChooseNum = 0;
<<<<<<< Updated upstream
            freeBtn.setEnabled(true);
            backBtn.setEnabled(true);
            btflyBtn.setEnabled(true);
            brstBtn.setEnabled(true);
            dist100Btn.setEnabled(true);
            dist200Btn.setEnabled(true);
=======
            freeBtn.setEnabled(true);		freeBtn.setBackground(new Color(243, 236, 232));
            backBtn.setEnabled(true);		backBtn.setBackground(new Color(243, 236, 232));
            btflyBtn.setEnabled(true);		btflyBtn.setBackground(new Color(243, 236, 232));
            brstBtn.setEnabled(true);		brstBtn.setBackground(new Color(243, 236, 232));
            dist100Btn.setEnabled(true);	dist100Btn.setBackground(new Color(243, 236, 232));
            dist200Btn.setEnabled(true);	dist200Btn.setBackground(new Color(243, 236, 232));
>>>>>>> Stashed changes
            System.out.println(strokeChooseNum);
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

        reBtn = new JButton("다시 선택");
//        ImageIcon reImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        reBtn.setIcon(reImg);
        reBtn.addActionListener(this);
        reBtn.setBounds(700, 180, 100, 45);
        add(reBtn);

        nextBtn = new JButton("=>");
//        ImageIcon nextImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        nextBtn.setIcon(nextImg);
        nextBtn.addActionListener(this);
        nextBtn.setBounds(180, 400, 100, 45);
        add(nextBtn);

        dist100Btn = new JButton("100m");
        dist100Btn.addActionListener(this);
        dist100Btn.setBounds(180, 300, 100, 45);
        add(dist100Btn);
        dist200Btn = new JButton("200m");
        dist200Btn.addActionListener(this);
        dist200Btn.setBounds(300, 300, 100, 45);
        add(dist200Btn);
    }
}
