package game.swimming;

import game.swimming.activities.LoginActivity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Btns extends JPanel {
	Image background = new ImageIcon(Btns.class.getResource("res/초기화면.jpg")).getImage();
	JButton login, join;
	JLabel idlabel, pwlabel;
	JTextField ID;
	JPasswordField PW;
	String idex = "qwer", pwex = "1234";
	JOptionPane notfound;
	
	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1000, 800, null);
	}
	
	public Btns() {
		setOpaque(false);
		setLayout(null);
		notfound = new JOptionPane();
		login = new JButton();
        login.setBounds(600, 500, 100, 45);
        ImageIcon loginImg = new ImageIcon(MainActivity.class.getResource("res/login.jpg"));
        login.setIcon(loginImg);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(ID.getText().equals(idex) && PW.getText().equals(pwex)) {
            		setVisible(false);
            		new PlayActivity();
            	}
            	else {
            		notfound.showMessageDialog(null, "ID 혹은 Password가 일치하지 않습니다.");
            		ID.setText(null);
            		PW.setText(null);
            	}
            }
        });
        join = new JButton();
        join.setBounds(600, 550, 100, 45);
        ImageIcon joinImg = new ImageIcon(MainActivity.class.getResource("res/join.jpg"));
        join.setIcon(joinImg);
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpActivity();
            }
        });
        idlabel = new JLabel("ID");
        pwlabel = new JLabel("PW");
        ID = new JTextField();
        PW = new JPasswordField();
        add(login);		add(join);		add(ID);	add(PW);	add(idlabel);	add(pwlabel);
        idlabel.setBounds(180, 650, 100, 45); //ID
        pwlabel.setBounds(360, 650, 100, 45); //PW
        ID.setBounds(200, 660, 120, 30); //Field
        PW.setBounds(385, 660, 120, 30); //Field
        login.setBounds(550, 655, 100, 35); //Button
        join.setBounds(670, 655, 100, 35); //Button
	}
}

public class MainActivity extends JFrame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    MainActivity() {
        setTitle("My Swimming Game : Main");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        add(new Btns());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainActivity();
    }
}
