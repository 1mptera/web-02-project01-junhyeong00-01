package frames;

import models.User;
import utils.UserLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class SignUpFrame extends JFrame {
    private final List<User> users;

    private final JPanel signUpPanel;
    private JTextField userNameInputField;
    private JTextField passwordInputField;
    private JTextField nicknameInputField;

    public SignUpFrame(List<User> users) {
        this.users = users;

        this.setTitle("회원가입");
        this.setSize(200, 300);
        this.setLocation(750, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(9, 1));
        signUpPanel.add(userNameLabel());
        signUpPanel.add(userNameInputField());
        signUpPanel.add(passwordLabel());
        signUpPanel.add(passwordInputField());
        signUpPanel.add(nicknameLabel());
        signUpPanel.add(nicknameInputField());
        signUpPanel.add(signUpButton());
        add(signUpPanel);
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

    private JLabel nicknameLabel() {
        return new JLabel("닉네임");
    }

    private JTextField nicknameInputField() {
        nicknameInputField = new JTextField(13);
        return nicknameInputField;
    }

    private JButton signUpButton() {
        JButton button = new JButton("회원가입 완료");
        button.addActionListener(e -> {

            String userName = userNameInputField.getText();
            String password = passwordInputField.getText();
            String nickname = nicknameInputField.getText();

            if (userName.length() == 0) {
                userNameInputField.setText("아이디를 입력해주세요!");
            }

            if (password.length() == 0) {
                passwordInputField.setText("비밀번호를 입력해주세요!");
            }

            if (nickname.length() == 0) {
                nicknameInputField.setText("닉네임을 입력해주세요!");
            }

            if (!(userName.length() == 0)
                    && !(password.length() == 0)
                    && !(nickname.length() == 0)) {
                long id = users.size() + 1;

                User user = new User(userName, password, nickname, id);
                users.add(user);

                saveUsers();
                dispose();
            }

            if (userName.length() == 0
                    || password.length() == 0
                    || nickname.length() == 0) {
                this.setVisible(false);
                this.setVisible(true);
            }
        });
        return button;
    }

    private void saveUsers() {
        UserLoader userLoader = new UserLoader();
        try {
            userLoader.saveUsers(users);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
