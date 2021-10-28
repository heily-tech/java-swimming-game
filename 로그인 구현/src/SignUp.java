import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp extends JFrame {
    private JPanel contentPane;
    private static SignUp signUp;
    PreparedStatement preparedStatement;

    private SignUp() {
        String[] prePhone = {"010", "019", "011", "012"};
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 320, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBounds(100, 100, 10, 10);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUsername = new JLabel("가입정보입력");
        lblUsername.setBounds(90, 10, 163, 34);
        lblUsername.setFont(new Font("Gulim", Font.BOLD, 15));
        panel.add(lblUsername);

        JLabel idLabel = new JLabel("아이디");
        idLabel.setBounds(10, 60, 163, 34);
        idLabel.setFont(new Font("Gulim", Font.BOLD, 12));
        panel.add(idLabel);

        JTextField id = new JTextField();
        id.setBounds(100, 65, 150, 20);
        id.setColumns(10);
        panel.add(id);

        JLabel pwLabel = new JLabel("비밀번호");
        pwLabel.setBounds(10, 100, 163, 34);
        pwLabel.setFont(new Font("Gulim", Font.BOLD, 12));
        panel.add(pwLabel);

        JTextField pw = new JTextField();
        pw.setBounds(100, 105, 150, 20);
        pw.setColumns(10);
        panel.add(pw);

        JLabel pwCheckLabel = new JLabel("비밀번호 확인");
        pwCheckLabel.setBounds(10, 140, 163, 34);
        pwCheckLabel.setFont(new Font("Gulim", Font.BOLD, 12));
        panel.add(pwCheckLabel);

        JTextField pwCheck = new JTextField();
        pwCheck.setBounds(100, 145, 150, 20);
        pwCheck.setColumns(10);
        panel.add(pwCheck);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(10, 180, 163, 34);
        nameLabel.setFont(new Font("Gulim", Font.BOLD, 12));
        panel.add(nameLabel);

        JTextField name = new JTextField();
        name.setBounds(100, 185, 150, 20);
        name.setColumns(10);
        panel.add(name);

        JButton loginButton = new JButton("가입");
        loginButton.setBounds(30, 265, 100, 20);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputId = id.getText();

                // 중복된 아이디일 경우 고려
                if(!check(inputId)){
                    JOptionPane.showMessageDialog(signUp, "중복된 아이디 입니다");
                    return;
                }
                String inputPassword = pw.getText();
                String inputPasswordCheck = pwCheck.getText();

                if (inputId == null || inputId.equals("")) {
                    JOptionPane.showMessageDialog(signUp, "아이디를 입력해주세요");
                    return;
                }

                if (inputPassword == null || inputPassword.equals("")) {
                    JOptionPane.showMessageDialog(signUp, "비밀번호를 입력해주세요");
                    return;
                }

                if(!inputPassword.equals(inputPasswordCheck)){
                    JOptionPane.showMessageDialog(signUp, "비밀번호 확인이 다릅니다");
                    return;
                }

                String inputName = name.getText();

                if (inputName == null || inputName.equals("")) {
                    JOptionPane.showMessageDialog(signUp, "이름을 입력해주세요");
                    return;
                }

                insert(inputId, inputPassword, inputName);
                JOptionPane.showMessageDialog(signUp, "회원가입완료");
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("취소");
        signUpButton.setBounds(160, 265, 100, 20);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home.open(signUp);
            }
        });
        panel.add(signUpButton);

        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }

    // 아이디 중복 확인
    private boolean check(String id) {
        String query = "select u_id from user where nickname= ?";
        ResultSet resultSet = null;
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            // id 로 찾은게 있다면 중복
            if(resultSet.next())
                return false;

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void insert(String id, String pw, String name) {
        String query = "insert into user(nickname, password, name) values(?,?,?)";
        try {
            preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            preparedStatement.setString(3, name);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
            System.exit(1);
        }
    }

    public static void open(JFrame jFrame) {
        if (signUp == null) {
            signUp = new SignUp();
        }

        if (jFrame != null)
            jFrame.setVisible(false);
        signUp.setVisible(true);
    }
}