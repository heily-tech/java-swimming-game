package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static game.swimming.activities.SelectModeActivity.backupNum;

public class SelectStrokeActivity extends JPanel implements ActionListener {
    public static ArrayList<String> strokeChooseNum = new ArrayList<>();
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    static JButton freeBtn, backBtn, btflyBtn, brstBtn, nextBtn, dist100Btn, dist200Btn, reBtn, goBackBtn;
    private JOptionPane nullSelection;
    Image background = new ImageIcon(MainActivity.class.getResource("res/modeBackground.png")).getImage();

    public SelectStrokeActivity(MainActivity main) {
        for (int i = 0; i < 4; i++)
            strokeChooseNum.add("0");
        this.main = main;
        setOpaque(false);
        setLayout(null);
        btnInit();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == freeBtn) {
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(0, "1");
            btnSelected();
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == backBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(0, "2");
            btnSelected();
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == btflyBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(0, "3");
            btnSelected();
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == brstBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(0, "4");
            btnSelected();
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == dist100Btn) {
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(1, "1");
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == dist200Btn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(1, "2");
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == nextBtn){
            if (freeBtn.isEnabled() == true || backBtn.isEnabled() == true || btflyBtn.isEnabled() == true || brstBtn.isEnabled() == true ||
            dist100Btn.isEnabled() == true || dist200Btn.isEnabled() == true) {
                main.sfx("res/sfxs/error.wav");
                nullSelection.showMessageDialog(null, "영법과 거리를 선택해주세요.");
            } else {
                main.sfx("res/sfxs/select_with_reverb.wav");
                main.change("SelectModeActivity");
                System.out.println(strokeChooseNum);
                backupNum = (ArrayList<String>) strokeChooseNum.clone();
            }
        } else if (e.getSource() == reBtn) {
            main.sfx("res/sfxs/reselect.wav");
            reset();
            System.out.println(strokeChooseNum);
        }
        if (e.getSource() == goBackBtn) {
            main.sfx("res/sfxs/back.wav");
            main.change("UserActivity");
        }
    }

    void btnInit() {
        freeBtn = new JButton();
        freeBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/자유형1.png")));
        freeBtn.setBorderPainted(false);
        freeBtn.setContentAreaFilled(false);
        freeBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/자유형2.png")));
        freeBtn.addActionListener(this);
        freeBtn.setBounds(44, 175, 210, 220);
        add(freeBtn);

        backBtn = new JButton();
        backBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/배영1.png")));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/배영2.png")));
        backBtn.addActionListener(this);
        backBtn.setBounds(277, 175, 210, 220);
        add(backBtn);

        btflyBtn = new JButton();
        btflyBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/접영1.png")));
        btflyBtn.setBorderPainted(false);
        btflyBtn.setContentAreaFilled(false);
        btflyBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/접영2.png")));
        btflyBtn.addActionListener(this);
        btflyBtn.setBounds(510, 175, 210, 220);
        add(btflyBtn);

        brstBtn = new JButton();
        brstBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/평영1.png")));
        brstBtn.setBorderPainted(false);
        brstBtn.setContentAreaFilled(false);
        brstBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/평영2.png")));
        brstBtn.addActionListener(this);
        brstBtn.setBounds(746, 175, 210, 220);
        add(brstBtn);

        reBtn = new JButton();
        reBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/재선택1.png")));
        reBtn.setBorderPainted(false);
        reBtn.setContentAreaFilled(false);
        reBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/재선택2.png")));
        reBtn.addActionListener(this);
        reBtn.setBounds(44, 660, 200, 75);
        add(reBtn);

        nextBtn = new JButton();
        nextBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/다음1.png")));
        nextBtn.setBorderPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/다음2.png")));
        nextBtn.addActionListener(this);
        nextBtn.setBounds(756, 660, 200, 75);
        add(nextBtn);

        dist100Btn = new JButton();
        dist100Btn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/100m1.png")));
        dist100Btn.setBorderPainted(false);
        dist100Btn.setContentAreaFilled(false);
        dist100Btn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/100m2.png")));
        dist100Btn.addActionListener(this);
        dist100Btn.setBounds(50, 427, 900, 95);
        add(dist100Btn);

        dist200Btn = new JButton();
        dist200Btn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/200m1.png")));
        dist200Btn.setBorderPainted(false);
        dist200Btn.setContentAreaFilled(false);
        dist200Btn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectStroke/200m2.png")));
        dist200Btn.addActionListener(this);
        dist200Btn.setBounds(50, 537, 900, 95);
        add(dist200Btn);

        goBackBtn = new JButton();
        goBackBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png")));
        goBackBtn.setBorderPainted(false);
        goBackBtn.setContentAreaFilled(false);
        goBackBtn.addActionListener(this);
        goBackBtn.setBounds(20, 20, 55, 48);
        add(goBackBtn);

        reset();
    }

    void btnSelected() {
        freeBtn.setEnabled(false);
        backBtn.setEnabled(false);
        btflyBtn.setEnabled(false);
        brstBtn.setEnabled(false);
    }

    static void reset() {
        for (int i = 0; i < 4; i++)
            strokeChooseNum.set(i, "0");
        System.out.println(strokeChooseNum);

        freeBtn.setEnabled(true);
        backBtn.setEnabled(true);
        btflyBtn.setEnabled(true);
        brstBtn.setEnabled(true);
        nextBtn.setEnabled(true);
        dist100Btn.setEnabled(true);
        dist200Btn.setEnabled(true);
        goBackBtn.setEnabled(true);
    }
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
