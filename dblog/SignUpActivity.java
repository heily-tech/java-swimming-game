package game.swimming.activities;

import game.swimming.MainActivity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//뒤로가기 버튼

public class SignUpActivity extends JFrame {
    JButton doBtn, backBtn;
    JLabel idLabel, pwLabel;
    JTextField idField;
    JPasswordField pwField;
    PreparedStatement preparedStatement;

    public SignUpActivity() {
        Color c = new Color(120, 209, 255);
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(c);
        btnPanel.setLayout(null);

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
                String id = idField.getText();

                if (id == null || id.equals("")) {
                    JOptionPane.showMessageDialog(doBtn, "아이디를 입력해주세요");
                    return;
                }

                if (!check(id)) {
                    JOptionPane.showMessageDialog(doBtn, "중복된 아이디 입니다");
                    return;
                }

                String password = new String(pwField.getPassword());

                if (password.equals("")) {
                    JOptionPane.showMessageDialog(doBtn, "비밀번호를 입력해주세요");
                    return;
                }

                insert(id, password);
                JOptionPane.showMessageDialog(doBtn, "회원가입완료");
                dispose();
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

    private boolean check(String id) {
        String query = "select id from user where id= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            // id 로 찾은게 있다면 중복
            if (resultSet.next())
                return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void insert(String id, String pw) {
        String query = "insert into user(id, password) values(?,?)";
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            preparedStatement.execute();
        } catch (SQLException ignored) {
        }
    }
}