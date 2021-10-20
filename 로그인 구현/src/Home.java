import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Home extends JFrame {
    private JPanel contentPane;
    PreparedStatement preparedStatement;

    private static Home home;

    private Home() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 320, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 10, 10);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("      수영선수권대회");
        lblUsername.setBounds(60, 10, 163, 34);
        lblUsername.setFont(new Font("Gulim", Font.BOLD, 15));
        panel.add(lblUsername);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(40, 60, 163, 34);
        idLabel.setFont(new Font("Gulim", Font.BOLD, 15));
        panel.add(idLabel);

        JTextField id = new JTextField();
        id.setBounds(70, 65, 150, 20);
        id.setColumns(10);
        panel.add(id);

        JLabel pwLabel = new JLabel("PW");
        pwLabel.setBounds(40, 100, 163, 34);
        pwLabel.setFont(new Font("Gulim", Font.BOLD, 15));
        panel.add(pwLabel);

        JTextField pw = new JTextField();
        pw.setBounds(70, 105, 150, 20);
        pw.setColumns(10);
        panel.add(pw);

        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(70, 145, 150, 20);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(check(id.getText(), pw.getText())) {
                    JOptionPane.showMessageDialog(home, "로그인 성공!");
                }
                else
                    JOptionPane.showMessageDialog(home, "아이디 비밀번호를 확인해주세요");
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("회원가입");
        signUpButton.setBounds(70, 175, 150, 20);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼 클릭 시 회원가입 창으로 이동
                SignUp.open(home);
            }
        });
        panel.add(signUpButton);

        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }

    // 아이디 비밀번호가 맞으면 로그인
    private boolean check(String id, String passwrod) {
        String query = "select u_id from user where nickname= ? and password= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, passwrod);
            resultSet = preparedStatement.executeQuery();

            // 아이디 비밀번호 체크한게 있다면 성공을 반환
            if(resultSet.next())
                return true;

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void open(JFrame jFrame) {
        if (home == null) {
            home = new Home();
        }

        if (jFrame != null)
            jFrame.setVisible(false);
        home.setVisible(true);
    }
}