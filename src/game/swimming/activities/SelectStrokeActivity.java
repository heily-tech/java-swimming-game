package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.swimming.activities.SelectModeActivity.backupNum;

public class SelectStrokeActivity extends JPanel implements ActionListener {
    public static int strokeChooseNum = 0;
    private MainActivity main;
    private SelectModeActivity selectModeActivity;
    JButton freeBtn, backBtn, btflyBtn, brstBtn, nextBtn, dist100Btn, dist200Btn, reBtn;
    JLabel titleLabel, styleLabel, dLabel;
    private JOptionPane nullSelection;
    Image background = new ImageIcon(MainActivity.class.getResource("res/modeBG.jpg")).getImage();

    public SelectStrokeActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        btnInit();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == freeBtn) {
            strokeChooseNum += 1000;
            freeBtn.setBackground(new Color(193, 213, 207));
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == backBtn){
            strokeChooseNum += 2000;
            backBtn.setBackground(new Color(193, 213, 207));
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == btflyBtn){
            strokeChooseNum += 3000;
            btflyBtn.setBackground(new Color(193, 213, 207));
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == brstBtn){
            strokeChooseNum += 4000;
            brstBtn.setBackground(new Color(193, 213, 207));
            freeBtn.setEnabled(false);
            backBtn.setEnabled(false);
            btflyBtn.setEnabled(false);
            brstBtn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        if (e.getSource() == dist100Btn) {
            strokeChooseNum += 100;
            dist100Btn.setBackground(new Color(193, 213, 207));
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }
        else if (e.getSource() == dist200Btn){
            strokeChooseNum += 200;
            dist200Btn.setBackground(new Color(193, 213, 207));
            dist100Btn.setEnabled(false);
            dist200Btn.setEnabled(false);
            System.out.println(strokeChooseNum);
        }

        if (e.getSource() == nextBtn){
            if (strokeChooseNum < 1100 && strokeChooseNum < 2100 | strokeChooseNum < 3100 | strokeChooseNum < 4100) {
                nullSelection.showMessageDialog(null, "영법과 거리를 선택해주세요.");
            } else {
                main.change("SelectModeActivity");
                System.out.println(strokeChooseNum);
                backupNum = strokeChooseNum;
            }
        } else if (e.getSource() == reBtn) {
            strokeChooseNum = 0;
            freeBtn.setEnabled(true);		freeBtn.setBackground(new Color(243, 236, 232));
            backBtn.setEnabled(true);		backBtn.setBackground(new Color(243, 236, 232));
            btflyBtn.setEnabled(true);		btflyBtn.setBackground(new Color(243, 236, 232));
            brstBtn.setEnabled(true);		brstBtn.setBackground(new Color(243, 236, 232));
            dist100Btn.setEnabled(true);	dist100Btn.setBackground(new Color(243, 236, 232));
            dist200Btn.setEnabled(true);	dist200Btn.setBackground(new Color(243, 236, 232));
            System.out.println(strokeChooseNum);
        }

    }

    void btnInit() {
        titleLabel = new JLabel("Select Mode");
        titleLabel.setFont(new Font("HY강B", Font.BOLD, 30));
        titleLabel.setBounds(415, 140, 190, 70);
        add(titleLabel);

        styleLabel = new JLabel("영법");
        styleLabel.setFont(new Font("HY강B", Font.PLAIN, 11));
        styleLabel.setBounds(485, 200, 190, 70);
        add(styleLabel);

        freeBtn = new JButton("<HTML><body style='text-align:center;'>자유형<br>★★</body></HTML>");
        freeBtn.setForeground(Color.DARK_GRAY);
        freeBtn.setBackground(new Color(243, 236, 232));
        freeBtn.setBorderPainted(false);
        freeBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        freeBtn.addActionListener(this);
        freeBtn.setBounds(230, 250, 110, 55);
        add(freeBtn);

        backBtn = new JButton("<HTML><body style='text-align:center;'>배영<br>★</body></HTML>");
        backBtn.setForeground(Color.DARK_GRAY);
        backBtn.setBackground(new Color(243, 236, 232));
        backBtn.setBorderPainted(false);
        backBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        backBtn.addActionListener(this);
        backBtn.setBounds(370, 250, 110, 55);
        add(backBtn);

        btflyBtn = new JButton("<HTML><body style='text-align:center;'>접영<br>★★★★</body></HTML>");
        btflyBtn.setForeground(Color.DARK_GRAY);
        btflyBtn.setBackground(new Color(243, 236, 232));
        btflyBtn.setBorderPainted(false);
        btflyBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        btflyBtn.addActionListener(this);
        btflyBtn.setBounds(510, 250, 110, 55);
        add(btflyBtn);

        brstBtn = new JButton("<HTML><body style='text-align:center;'>평영<br>★★★★★</body></HTML>");
        brstBtn.setForeground(Color.DARK_GRAY);
        brstBtn.setBackground(new Color(243, 236, 232));
        brstBtn.setBorderPainted(false);
        brstBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        brstBtn.addActionListener(this);
        brstBtn.setBounds(650, 250, 110, 55);
        add(brstBtn);

        reBtn = new JButton("다시 선택");
        reBtn.setForeground(Color.DARK_GRAY);
        reBtn.setBackground(new Color(243, 236, 232));
        reBtn.setBorderPainted(false);
        reBtn.setFont(new Font("HY강B", Font.PLAIN, 17));
        reBtn.addActionListener(this);
        reBtn.setBounds(440, 510, 110, 55);
        add(reBtn);

        nextBtn = new JButton("->");
        nextBtn.setForeground(Color.DARK_GRAY);
        nextBtn.setBackground(new Color(243, 236, 232));
        nextBtn.setBorderPainted(false);
        nextBtn.setFont(new Font("HY강B", Font.BOLD, 30));
        nextBtn.addActionListener(this);
        nextBtn.setBounds(580, 510, 110, 55);
        add(nextBtn);

        dLabel = new JLabel("거리");
        dLabel.setFont(new Font("HY강B", Font.PLAIN, 11));
        dLabel.setBounds(485, 330, 190, 70);
        add(dLabel);

        dist100Btn = new JButton("100m");
        dist100Btn.setForeground(Color.DARK_GRAY);
        dist100Btn.setBackground(new Color(243, 236, 232));
        dist100Btn.setBorderPainted(false);
        dist100Btn.setFont(new Font("HY강B", Font.PLAIN, 17));
        dist100Btn.addActionListener(this);
        dist100Btn.setBounds(360, 380, 110, 55);
        add(dist100Btn);

        dist200Btn = new JButton("200m");
        dist200Btn.setForeground(Color.DARK_GRAY);
        dist200Btn.setBackground(new Color(243, 236, 232));
        dist200Btn.setBorderPainted(false);
        dist200Btn.setFont(new Font("HY강B", Font.PLAIN, 17));
        dist200Btn.addActionListener(this);
        dist200Btn.setBounds(520, 380, 110, 55);
        add(dist200Btn);
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
