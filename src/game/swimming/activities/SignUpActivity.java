package game.swimming.activities;

import java.awt.event.*;
import javax.swing.*;

//뒤로가기 버튼

public class SignUpActivity extends JFrame{
    JButton doBtn;
    JLabel idLabel, pwLabel;
    JTextField idField;
    JPasswordField pwField;

    public SignUpActivity() {
        //JPanel btnPanel = new JPanel();
        setLayout(null);

        idLabel = new JLabel("아이디 : ");
        idLabel.setBounds(100, 200, 100, 35);
        //idLabel.getHorizontalTextPosition();
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 200, 150, 35);
        add(idField);

        pwLabel = new JLabel("패스워드 : ");
        pwLabel.setBounds(100, 240, 100, 35);
        add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBounds(200, 240, 150, 35);
        add(pwField);

        doBtn = new JButton("가입");
        doBtn.setBounds(150, 300, 100, 35);
        doBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(doBtn);

        setTitle("My Swimming Game : Sign UP");
        setSize(400, 500);
        setResizable(false);
        setLocationRelativeTo(null); //화면 중앙에 창 위치
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

