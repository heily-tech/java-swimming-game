package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.swimming.activities.SelectStrokeActivity.strokeChooseNum;

public class SelectModeActivity extends JPanel implements ActionListener {
    public static int backupNum = 0;
    JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, backBtn, reBtn, chosenBtn;
    JLabel titleLabel, entryLabel, modeLabel;
    private MainActivity main;
    private SelectStrokeActivity selectStrokeActivity;
    private swimmerThread swimmerThread;
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
            strokeChooseNum += 10;
            singleBtn.setBackground(new Color(193, 213, 207));
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == indivBtn){
            strokeChooseNum += 20;
            indivBtn.setBackground(new Color(193, 213, 207));
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == grpBtn){
            strokeChooseNum += 30;
            grpBtn.setBackground(new Color(193, 213, 207));
            singleBtn.setEnabled(false);
            indivBtn.setEnabled(false);
            grpBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == speedBtn) {
            strokeChooseNum += 1;
            speedBtn.setBackground(new Color(193, 213, 207));
            speedBtn.setEnabled(false);
            itemBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == itemBtn){
            strokeChooseNum += 2;
            itemBtn.setBackground(new Color(193, 213, 207));
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
            singleBtn.setEnabled(true);		singleBtn.setBackground(new Color(243, 236, 232));
            indivBtn.setEnabled(true);		indivBtn.setBackground(new Color(243, 236, 232));
            grpBtn.setEnabled(true);		grpBtn.setBackground(new Color(243, 236, 232));
            speedBtn.setEnabled(true);		speedBtn.setBackground(new Color(243, 236, 232));
            itemBtn.setEnabled(true);		itemBtn.setBackground(new Color(243, 236, 232));
        } else if (e.getSource() == backBtn) {
            main.change("SelectStrokeActivity");
            singleBtn.setEnabled(true);		singleBtn.setBackground(new Color(243, 236, 232));
            indivBtn.setEnabled(true);		indivBtn.setBackground(new Color(243, 236, 232));
            grpBtn.setEnabled(true);		grpBtn.setBackground(new Color(243, 236, 232));
            speedBtn.setEnabled(true);		speedBtn.setBackground(new Color(243, 236, 232));
            itemBtn.setEnabled(true);		itemBtn.setBackground(new Color(243, 236, 232));
        }
    }

    void btnInit() {
        entryLabel = new JLabel("플레이 인원");
        entryLabel.setFont(new Font("HY강B", Font.PLAIN, 11));
        entryLabel.setBounds(465, 200, 190, 70);
        add(entryLabel);

        singleBtn = new JButton("싱글모드");
        singleBtn.setForeground(Color.DARK_GRAY);
        singleBtn.setBackground(new Color(243, 236, 232));
        singleBtn.setBorderPainted(false);
        singleBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        singleBtn.addActionListener(this);
        singleBtn.setBounds(280, 250, 110, 55);
        add(singleBtn);

        indivBtn = new JButton("개인전");
        indivBtn.setForeground(Color.DARK_GRAY);
        indivBtn.setBackground(new Color(243, 236, 232));
        indivBtn.setBorderPainted(false);
        indivBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        indivBtn.addActionListener(this);
        indivBtn.setBounds(440, 250, 110, 55);
        add(indivBtn);

        grpBtn = new JButton("단체전");
        grpBtn.setForeground(Color.DARK_GRAY);
        grpBtn.setBackground(new Color(243, 236, 232));
        grpBtn.setBorderPainted(false);
        grpBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        grpBtn.addActionListener(this);
        grpBtn.setBounds(600, 250, 110, 55);
        add(grpBtn);

        modeLabel = new JLabel("플레이 모드");
        modeLabel.setFont(new Font("HY강B", Font.PLAIN, 11));
        modeLabel.setBounds(465, 330, 190, 70);
        add(modeLabel);

        speedBtn = new JButton("스피드전");
        speedBtn.setForeground(Color.DARK_GRAY);
        speedBtn.setBackground(new Color(243, 236, 232));
        speedBtn.setBorderPainted(false);
        speedBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        speedBtn.addActionListener(this);
        speedBtn.setBounds(360, 380, 110, 55);
        add(speedBtn);

        itemBtn = new JButton("아이템전");
        itemBtn.setForeground(Color.DARK_GRAY);
        itemBtn.setBackground(new Color(243, 236, 232));
        itemBtn.setBorderPainted(false);
        itemBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        itemBtn.addActionListener(this);
        itemBtn.setBounds(520, 380, 110, 55);
        add(itemBtn);

        backBtn = new JButton("<-");
        backBtn.setForeground(Color.DARK_GRAY);
        backBtn.setBackground(new Color(243, 236, 232));
        backBtn.setBorderPainted(false);
        backBtn.setFont(new Font("HY강B", Font.BOLD, 30));
        backBtn.addActionListener(this);
        backBtn.setBounds(300, 510, 110, 55);
        add(backBtn);

        reBtn = new JButton("다시 선택");
        reBtn.setForeground(Color.DARK_GRAY);
        reBtn.setBackground(new Color(243, 236, 232));
        reBtn.setBorderPainted(false);
        reBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        reBtn.addActionListener(this);
        reBtn.setBounds(440, 510, 110, 55);
        add(reBtn);

        chosenBtn = new JButton("게임 시작");
        chosenBtn.setForeground(Color.DARK_GRAY);
        chosenBtn.setBackground(new Color(243, 236, 232));
        chosenBtn.setBorderPainted(false);
        chosenBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(580, 510, 110, 55);
        add(chosenBtn);
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
