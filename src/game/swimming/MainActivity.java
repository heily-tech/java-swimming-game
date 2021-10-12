package game.swimming;

import game.swimming.activities.PlayActivity;
import game.swimming.activities.SignUpActivity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class init extends JPanel {
	Image background = new ImageIcon(init.class.getResource("res/초기화면.jpg")).getImage();
	JButton loginBtn, joinBtn;
	JLabel idLabel, pwLabel;
	JTextField idField;
	JPasswordField pwField;
	String idSample = "1111", pwSample = "1111"; //변경 필요
	JOptionPane notfound;
	
	public init() {
		setOpaque(false);
		setLayout(null);
		notfound = new JOptionPane();

		loginBtn = new JButton();
        ImageIcon loginImg = new ImageIcon(MainActivity.class.getResource("res/btns/login.jpg"));
        loginBtn.setIcon(loginImg);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(idField.getText().equals(idSample) && pwField.getText().equals(pwSample)) {
            		setVisible(false);
            		new PlayActivity();
            	}
            	else {
            		notfound.showMessageDialog(null, "ID 혹은 Password가 일치하지 않습니다.");
            		idField.setText(null);
            		pwField.setText(null);
            	}
            }
        });
        loginBtn.setBounds(550, 655, 100, 35);
        add(loginBtn);

        joinBtn = new JButton();
        ImageIcon joinImg = new ImageIcon(MainActivity.class.getResource("res/btns/join.jpg"));
        joinBtn.setIcon(joinImg);
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpActivity();
            }
        });
        joinBtn.setBounds(670, 655, 100, 35);
        add(joinBtn);

        idLabel = new JLabel("ID");
        idLabel.setBounds(180, 650, 100, 45);
        add(idLabel);
        pwLabel = new JLabel("PW");
        pwLabel.setBounds(360, 650, 100, 45);
        add(pwLabel);
        idField = new JTextField();
        idField.setBounds(200, 660, 120, 30);
        add(idField);
        pwField = new JPasswordField();
        pwField.setBounds(385, 660, 120, 30);
        add(pwField);
	}
    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
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
        add(new init());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MainActivity();
    }
}
