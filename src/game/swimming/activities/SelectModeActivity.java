package game.swimming.activities;

import static game.swimming.activities.SelectModeActivity.backupNum;
import static game.swimming.activities.SelectStrokeActivity.strokeChooseNum;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import game.swimming.MainActivity;

public class SelectModeActivity extends JPanel implements ActionListener {
    public static int backupNum = 0;
    Image background = new ImageIcon(MainActivity.class.getResource("res/modeBG.jpg")).getImage();
    JButton singleBtn, indivBtn, grpBtn, speedBtn, itemBtn, reBtn;
    JButton chosenBtn, reset;
    JLabel selectMode, people, mode;
    Font f1, f2, f3;
    Color Bg = new Color(195, 228, 234);
    private MainActivity main;
    private SelectStrokeActivity selectStrokeActivity;
    private JOptionPane nullSelection;

    public SelectModeActivity(MainActivity main) {
    	f1 = new Font("HY강B", Font.PLAIN, 17);
    	f2 = new Font("HY강B", Font.BOLD, 30);
    	f3= new Font("HY강B", Font.PLAIN, 11);
        this.main = main;
        btnInit();
        setOpaque(false);
        setLayout(null);
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
                main.change("PlayActivity");
                System.out.println(strokeChooseNum);
            }
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == reBtn) {
            strokeChooseNum = backupNum;
            singleBtn.setEnabled(true);		singleBtn.setBackground(new Color(243, 236, 232));
            indivBtn.setEnabled(true);		indivBtn.setBackground(new Color(243, 236, 232));
            grpBtn.setEnabled(true);		grpBtn.setBackground(new Color(243, 236, 232));
            speedBtn.setEnabled(true);		speedBtn.setBackground(new Color(243, 236, 232));
            itemBtn.setEnabled(true);		itemBtn.setBackground(new Color(243, 236, 232));
            System.out.println(strokeChooseNum);
        } else if (e.getSource() == reset){
                main.change("SelectStrokeActivity");
                strokeChooseNum = backupNum;
                singleBtn.setEnabled(true);		singleBtn.setBackground(new Color(243, 236, 232));
                indivBtn.setEnabled(true);		indivBtn.setBackground(new Color(243, 236, 232));
                grpBtn.setEnabled(true);		grpBtn.setBackground(new Color(243, 236, 232));
                speedBtn.setEnabled(true);		speedBtn.setBackground(new Color(243, 236, 232));
                itemBtn.setEnabled(true);		itemBtn.setBackground(new Color(243, 236, 232));
                System.out.println(strokeChooseNum);
        }
    }

    void btnInit() {
    	selectMode = new JLabel("Select Mode");
    	selectMode.setFont(f2);
    	selectMode.setBounds(415, 140, 190, 70);
    	add(selectMode);
    	
    	people = new JLabel("플레이 인원");
    	people.setFont(f3);
    	people.setBounds(465, 200, 190, 70);
    	add(people);
    	
        singleBtn = new JButton("싱글모드");
        singleBtn.setForeground(Color.DARK_GRAY);
        singleBtn.setBackground(new Color(243, 236, 232));
        singleBtn.setBorderPainted(false);
        singleBtn.setFont(f1);
//        ImageIcon singleImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        singleBtn.setIcon(singleImg);
        singleBtn.addActionListener(this);
        singleBtn.setBounds(280, 250, 110, 55);
        add(singleBtn);

        indivBtn = new JButton("개인전");
        indivBtn.setForeground(Color.DARK_GRAY);
        indivBtn.setBackground(new Color(243, 236, 232));
        indivBtn.setBorderPainted(false);
        indivBtn.setFont(f1);
//        ImageIcon indivImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        indivBtn.setIcon(indivImg);
        indivBtn.addActionListener(this);
        indivBtn.setBounds(440, 250, 110, 55);
        add(indivBtn);

        grpBtn = new JButton("단체전");
        grpBtn.setForeground(Color.DARK_GRAY);
        grpBtn.setBackground(new Color(243, 236, 232));
        grpBtn.setBorderPainted(false);
        grpBtn.setFont(f1);
//        ImageIcon grpImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        grpBtn.setIcon(grpImg);
        grpBtn.addActionListener(this);
        grpBtn.setBounds(600, 250, 110, 55);
        add(grpBtn);
        
        mode = new JLabel("플레이 모드");
    	mode.setFont(f3);
    	mode.setBounds(465, 330, 190, 70);
    	add(mode);

        speedBtn = new JButton("스피드전");
        speedBtn.setForeground(Color.DARK_GRAY);
        speedBtn.setBackground(new Color(243, 236, 232));
        speedBtn.setBorderPainted(false);
        speedBtn.setFont(f1);
//        ImageIcon speedImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        speedBtn.setIcon(speedImg);
        speedBtn.addActionListener(this);
        speedBtn.setBounds(360, 380, 110, 55);
        add(speedBtn);

        itemBtn = new JButton("아이템전");
        itemBtn.setForeground(Color.DARK_GRAY);
        itemBtn.setBackground(new Color(243, 236, 232));
        itemBtn.setBorderPainted(false);
        itemBtn.setFont(f1);
//        ImageIcon itemImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
//        itemBtn.setIcon(itemImg);
        itemBtn.addActionListener(this);
        itemBtn.setBounds(520, 380, 110, 55);
        add(itemBtn);

        reBtn = new JButton("다시 선택");
        reBtn.setForeground(Color.DARK_GRAY);
        reBtn.setBackground(new Color(243, 236, 232));
        reBtn.setBorderPainted(false);
        reBtn.setFont(f1);
//        ImageIcon reImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        reBtn.setIcon(reImg);
        reBtn.addActionListener(this);
        reBtn.setBounds(440, 510, 110, 55);
        add(reBtn);
        
        reset = new JButton("←");
        reset.setForeground(Color.DARK_GRAY);
        reset.setBackground(new Color(243, 236, 232));
        reset.setBorderPainted(false);
        reset.setFont(f2);
//        ImageIcon reImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg")); //바꾸기
//        reBtn.setIcon(reImg);
        reset.addActionListener(this);
        reset.setBounds(300, 510, 110, 55);
        add(reset);

        chosenBtn = new JButton("게임시작");
        chosenBtn.setForeground(Color.DARK_GRAY);
        chosenBtn.setBackground(new Color(243, 236, 232));
        chosenBtn.setBorderPainted(false);
        chosenBtn.setFont(f1);
        chosenBtn.addActionListener(this);
        chosenBtn.setBounds(580, 510, 110, 55);
        add(chosenBtn);
    }
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}
