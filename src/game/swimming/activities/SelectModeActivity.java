package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static game.swimming.activities.SelectStrokeActivity.strokeChooseNum;
import static game.swimming.activities.PlayActivity.strokeName;

public class SelectModeActivity extends JPanel implements ActionListener {
    public static ArrayList<String> backupNum = new ArrayList<>();
    static JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, goBackBtn, reBtn, chosenBtn;
    private MainActivity main;
    private JOptionPane nullSelection;
    Image background = new ImageIcon(MainActivity.class.getResource("res/modeBackground.png")).getImage();

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
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(2, "1");
            singleBtn.setBackground(new Color(193, 213, 207));
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == indivBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(2, "2");
            indivBtn.setBackground(new Color(193, 213, 207));
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == grpBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(2, "3");
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == speedBtn) {
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(3, "1");
            speedBtn.setEnabled(false);
            itemBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == itemBtn){
            main.sfx("res/sfxs/select.wav");
            strokeChooseNum.set(3, "2");
            speedBtn.setEnabled(false);
            itemBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == chosenBtn) {
            if (singleBtn.isEnabled() == true || indivBtn.isEnabled() == true || grpBtn.isEnabled() == true
                    || speedBtn.isEnabled() == true || itemBtn.isEnabled() == true) {
                main.sfx("res/sfxs/error.wav");
                nullSelection.showMessageDialog(null, "????????? ??????????????????.");
            } else {
                backgroundMusic.stop();
                main.playActivity = new PlayActivity(main);
                if (strokeChooseNum.get(2).equals("1")) {
                    main.singleGameStatus = true;
                    main.sfx("res/sfxs/select_with_reverb.wav");
                    if (strokeChooseNum.get(1).equals("2"))
                        main.dist = true;
                    main.change("PlayActivity");
                    if (strokeChooseNum.get(0).equals("1"))
                        strokeName = "freestyle";
                    else if (strokeChooseNum.get(0).equals("2"))
                        strokeName = "backStroke";
                    else if (strokeChooseNum.get(0).equals("3"))
                        strokeName = "butterfly";
                    else if (strokeChooseNum.get(0).equals("4"))
                        strokeName = "breastStroke";
                } else {
                    main.sfx("res/sfxs/select_with_reverb.wav");
                    if (strokeChooseNum.get(0).equals("1"))
                        main.change("freestyle");
                    else if (strokeChooseNum.get(0).equals("2"))
                        main.change("backStroke");
                    else if (strokeChooseNum.get(0).equals("3"))
                        main.change("butterfly");
                    else if (strokeChooseNum.get(0).equals("4"))
                        main.change("breastStroke");
                }
            }
        } else if (e.getSource() == reBtn) {
            main.sfx("res/sfxs/reselect.wav");
            reset();
        }
        if (e.getSource() == goBackBtn) {
            main.sfx("res/sfxs/back.wav");
            main.change("SelectStrokeActivity");
        }
    }

    void btnInit() {
        singleBtn = new JButton();
        singleBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/????????????1.png")));
        singleBtn.setBorderPainted(false);
        singleBtn.setContentAreaFilled(false);
        singleBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/????????????2.png")));
        singleBtn.addActionListener(this);
        singleBtn.setBounds(160, 175, 210, 220);
        add(singleBtn);

        indivBtn = new JButton();
        indivBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????1.png")));
        indivBtn.setBorderPainted(false);
        indivBtn.setContentAreaFilled(false);
        indivBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????2.png")));
        indivBtn.addActionListener(this);
        indivBtn.setBounds(398, 175, 210, 220);
        add(indivBtn);

        grpBtn = new JButton();
        grpBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????1.png")));
        grpBtn.setBorderPainted(false);
        grpBtn.setContentAreaFilled(false);
        grpBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????2.png")));
        grpBtn.addActionListener(this);
        grpBtn.setBounds(631, 175, 210, 220);
        add(grpBtn);

        speedBtn = new JButton();
        speedBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????1.png")));
        speedBtn.setBorderPainted(false);
        speedBtn.setContentAreaFilled(false);
        speedBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/?????????2.png")));
        speedBtn.addActionListener(this);
        speedBtn.setBounds(50, 427, 900, 95);
        add(speedBtn);

        itemBtn = new JButton();
        itemBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/????????????1.png")));
        itemBtn.setBorderPainted(false);
        itemBtn.setContentAreaFilled(false);
        itemBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/selectMode/????????????2.png")));
        itemBtn.addActionListener(this);
        itemBtn.setBounds(50, 537, 900, 95);
        add(itemBtn);

        goBackBtn = new JButton();
        goBackBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png")));
        goBackBtn.setBorderPainted(false);
        goBackBtn.setContentAreaFilled(false);
        goBackBtn.addActionListener(this);
        goBackBtn.setBounds(20, 20, 55, 48);
        add(goBackBtn);

        reBtn = new JButton();
        reBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/?????????1.png")));
        reBtn.setBorderPainted(false);
        reBtn.setContentAreaFilled(false);
        reBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/?????????2.png")));
        reBtn.addActionListener(this);
        reBtn.setBounds(44, 660, 200, 75);
        add(reBtn);

        chosenBtn = new JButton();
        chosenBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/??????1.png")));
        chosenBtn.setBorderPainted(false);
        chosenBtn.setContentAreaFilled(false);
        chosenBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/??????2.png")));
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(756, 660, 200, 75);
        add(chosenBtn);

        reset();
    }

    static void reset() {
        for (int i = 2; i < 4; i++) {
            strokeChooseNum.set(i, "0");
        }
        singleBtn.setEnabled(true);
        indivBtn.setEnabled(true);
        grpBtn.setEnabled(true);
        speedBtn.setEnabled(true);
        itemBtn.setEnabled(true);
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
