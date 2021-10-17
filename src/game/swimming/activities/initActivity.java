package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class initActivity extends JPanel {
    private MainActivity main;
    Image background = new ImageIcon(MainActivity.class.getResource("res/초기화면.jpg")).getImage();
    JButton loginBtn, joinBtn;
    JLabel idLabel, pwLabel;
    JTextField idField;
    JPasswordField pwField;
    String idSample = "1111", pwSample = "1111"; //변경 필요
    JOptionPane notFound;

    public initActivity(MainActivity main) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        notFound = new JOptionPane();

        loginBtn = new JButton();
        ImageIcon loginImg = new ImageIcon(MainActivity.class.getResource("res/btns/login.jpg"));
        loginBtn.setIcon(loginImg);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals(idSample) && pwField.getText().equals(pwSample)) {
                    main.change("SelectStrokeActivity");
                    //setVisible(false);
                }
                else {
                    notFound.showMessageDialog(null, "ID/Password가 일치하지 않습니다.");
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
