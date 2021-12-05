package game.swimming.activities;


import game.swimming.MainActivity;
import server.tcpClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//뒤로가기 버튼

public class SignUpActivity extends JFrame {
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

        idLabel = new JLabel();
        idLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/idBase.png")));
        idLabel.setBounds(70, 185, 250, 50);
        add(idLabel);

        idField = new JTextField();
        idField.setBorder(BorderFactory.createEmptyBorder());
        idField.setOpaque(false);
        //idField.setFont(new Font("SansSerif", Font.BOLD, 25));
        idField.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        idField.setBounds(150, 185, 100, 30);
        add(idField);

        pwLabel = new JLabel();
        pwLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/pwBase.png")));
        pwLabel.setBounds(70, 250, 250, 50);
        add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBorder(BorderFactory.createEmptyBorder());
        pwField.setOpaque(false);
        pwField.setEchoChar('*');
        //pwField.setFont(new Font("SansSerif", Font.BOLD, 25));
        pwField.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        pwField.setBounds(150, 250, 200, 30);
        add(pwField);

        doBtn = new JButton();
        doBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/가입1.png")));
        doBtn.setBorderPainted(false);
        doBtn.setContentAreaFilled(false);
        doBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/가입2.png")));
        doBtn.setBounds(100, 350, 200, 75);
        doBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                char[] pw = pwField.getPassword();
                for (char cha : pw) {
                    Character.toString(cha);
                    password += (password.equals("")) ? ""+cha+"" : ""+cha+"";
                }
                client.send("@signup" + idField.getText() + "," + password);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
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
        backBtn.setBounds(20, 20, 55, 48);
        ImageIcon backImg = new ImageIcon(MainActivity.class.getResource("res/btns/backBtn.png"));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setIcon(backImg);
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.sfx("res/sfxs/back.wav");
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
