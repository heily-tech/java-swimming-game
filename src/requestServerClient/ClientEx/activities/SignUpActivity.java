package ClientEx.activities;

import ClientEx.MainActivity;
import ClientEx.network.tcpClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//뒤로가기 버튼

public class SignUpActivity extends JFrame{
    JButton doBtn, backBtn;
    JLabel idLabel, pwLabel;
    JTextField idField;
    JPasswordField pwField;
    String password = "";
    JOptionPane notFound;
    
    public SignUpActivity(MainActivity main, tcpClient client) {
        Color c = new Color(120, 209, 255);
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(c);
        btnPanel.setLayout(null);
        notFound = new JOptionPane();

        idLabel = new JLabel("아이디 : ");
        idLabel.setBounds(70, 180, 100, 45);
        //idLabel.getHorizontalTextPosition();
        btnPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(185, 185, 150, 30);
        btnPanel.add(idField);

        pwLabel = new JLabel("패스워드 : ");
        pwLabel.setBounds(70, 220, 100, 45);
        btnPanel.add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBounds(185, 225, 150, 30);
        btnPanel.add(pwField);

        doBtn = new JButton("가입");
        doBtn.setBounds(145, 320, 100, 35);
        doBtn.setBackground(Color.WHITE);
        doBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	char[] pw = pwField.getPassword();
            	for(char cha : pw)
            	{
            		Character.toString(cha);
            		password += (password.equals("")) ? ""+cha+"" : ""+cha+"";
            	}
            	
            	
            	client.send("@signup" + idField.getText() + "," + password);
            	
            	try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if(client.getSignupResult()) {
                	notFound.showMessageDialog(null, "회원가입에 성공했습니다.");
                    dispose();
                    //setVisible(false);
                }
                else {
                    notFound.showMessageDialog(null, "ID/Password가 이미 존재합니다.");
                    idField.setText(null);
                    pwField.setText(null);
                }
                //dispose();
            }
        });
        btnPanel.add(doBtn);

        backBtn = new JButton();
        backBtn.setBounds(20, 20, 50, 50);
        backBtn.setContentAreaFilled(false);
        ImageIcon backImg = new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png"));
        backBtn.setIcon(backImg);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnPanel.add(backBtn);

        this.add(btnPanel);
        this.setTitle("My Swimming Game : Sign UP");
        this.setSize(400, 500);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); //화면 중앙에 창 위치
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}

