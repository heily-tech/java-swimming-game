package game.swimming.activities;

import game.swimming.MainActivity;
import server.tcpClient;

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
    String password = "";
    String idSample = "", pwSample = ""; //변경 필요
    JOptionPane notFound;

    public initActivity(MainActivity main, tcpClient client) {
        this.main = main;
        setOpaque(false);
        setLayout(null);
        notFound = new JOptionPane();
        client.startClient();
        backgroundMusic.change("res/sfxs/start2.wav");
        backgroundMusic.play();

        loginBtn = new JButton();
        loginBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/loginBtn1.png")));
        loginBtn.setBorderPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/loginBtn2.png")));
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//            /*
                if(idField.getText().equals(idSample) && pwField.getText().equals(pwSample)) {
                    main.change("UserActivity");
                    main.sfx("res/sfxs/select_with_reverb.wav");
                    //setVisible(false);
                }
                else {
                    main.sfx("res/sfxs/error.wav");
                    notFound.showMessageDialog(null, "ID/Password가 일치하지 않습니다.");
                    idField.setText(null);
                    pwField.setText(null);
                }
            }
//            */
            /*
                char[] pw = pwField.getPassword();
                for (char cha : pw) {
                    Character.toString(cha);
                    password += (password.equals("")) ? "" + cha + "" : "" + cha + "";
                }
                client.send("@login" + idField.getText() + "," + password);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (client.getLoginResult()) {
                    main.change("UserActivity");
                    main.sfx("res/sfxs/select_with_reverb.wav");
                } else {
                    main.sfx("res/sfxs/error.wav");
                    notFound.showMessageDialog(null, "ID/Password가 일치하지 않습니다.");
                    idField.setText(null);
                    pwField.setText(null);
                }
            }

             */
        });
        loginBtn.setBounds(200, 600, 200, 75);
        add(loginBtn);

        joinBtn = new JButton();
        joinBtn.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/joinBtn1.png")));
        joinBtn.setBorderPainted(false);
        joinBtn.setContentAreaFilled(false);
        joinBtn.setRolloverIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/joinBtn2.png")));
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.sfx("res/sfxs/select_with_reverb.wav");
                new SignUpActivity(main, client);
            }
        });
        joinBtn.setBounds(600, 600, 200, 75);
        add(joinBtn);

        idLabel = new JLabel();
        idLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/idBase.png")));
        idLabel.setBounds(330, 360, 300, 50);
        add(idLabel);

        idField = new JTextField();
        idField.setBorder(BorderFactory.createEmptyBorder());
        idField.setOpaque(false);
        idField.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        idField.setBounds(420, 370, 180, 30);
        add(idField);

        pwLabel = new JLabel();
        pwLabel.setIcon(new ImageIcon(MainActivity.class.getResource("res/btns/init/pwBase.png")));
        pwLabel.setBounds(330, 460, 400, 50);
        add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBorder(BorderFactory.createEmptyBorder());
        pwField.setOpaque(false);
        pwField.setEchoChar('*');
        pwField.setFont(new Font("Press Start 2P", Font.PLAIN, 25));
        pwField.setBounds(420, 470, 180, 30);
        add(pwField);
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 800, null);
    }
}