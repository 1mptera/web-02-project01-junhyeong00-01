package panels;

import frames.SignUpFrame;
import models.Seller;
import models.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

public class LoginPanel extends JPanel {

    private final JPanel loginPanel;
    private JTextField userNameInputField;
    private JTextField passwordInputField;
    private List<User> users;
    private Seller seller;

    public LoginPanel(List<User> users, Seller seller) {
        this.users = users;
        this.seller = seller;

        setOpaque(false);

        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(7, 1));
        loginPanel.setBackground(new Color(255, 255, 255, 190));

        loginPanel.add(LoginTitleLabel());
        loginPanel.add(userNameLabel());
        loginPanel.add(userNameInputField());
        loginPanel.add(passwordLabel());
        loginPanel.add(passwordInputField());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(loginButton());
        panel.add(signUpButton());

        loginPanel.add(panel);

        add(loginPanel);
    }

    private JLabel LoginTitleLabel() {
        return new JLabel("로그인", SwingConstants.CENTER);
    }

    private JLabel userNameLabel() {
        return new JLabel("ID");
    }

    private JTextField userNameInputField() {
        userNameInputField = new JTextField(13);
        return userNameInputField;
    }

    private JLabel passwordLabel() {
        return new JLabel("password");
    }

    private JTextField passwordInputField() {
        passwordInputField = new JTextField(13);
        return passwordInputField;
    }

    private JButton loginButton() {
        JButton button = new JButton("로그인");
        button.addActionListener(e -> {
            String userName = userNameInputField.getText();
            String password = passwordInputField.getText();

            if (userName.length() == 0) {
                userNameInputField.setText("아이디를 입력해주세요!");
            }

            if (password.length() == 0) {
                passwordInputField.setText("비밀번호를 입력해주세요!");
            }

            if (!(userName.length() == 0) && !(password.length() == 0)) {
                for (User user : users) {
                    if (userName.equals(user.userName()) && password.equals(user.password())) {
                        seller.login( user.id(), user.nickname());
                        this.removeAll();
                        this.add(new JLabel("환영합니다. " + user.nickname() + "님"));

                        this.setVisible(false);
                        this.setVisible(true);

                        break;
                    }

                    userNameInputField.setText("일치하는 계정이 없습니다");
                    passwordInputField.setText("다시 입력해주세요");
                }
            }

            if (userName.length() == 0 || password.length() == 0) {
                this.removeAll();
                add(loginPanel);

                this.setVisible(false);
                this.setVisible(true);
            }
        });
        return button;
    }

    private JButton signUpButton() {
        JButton button = new JButton("회원가입");
        button.addActionListener(e -> {
            JFrame signUpFrame = new SignUpFrame(users);
        });
        return button;
    }
}
