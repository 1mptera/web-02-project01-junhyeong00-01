package panels;

import models.Post;
import models.CurrentAccount;
import models.Transaction;
import models.User;
import utils.UserLoader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class MyPagePanel extends JPanel {
    private CurrentAccount currentAccount;
    private List<User> users;

    private JPanel passwordEditPanel;
    private JTextField passwordInputField;

    public MyPagePanel(List<Post> posts, CurrentAccount currentAccount,
                       List<Transaction> transactions, List<User> users) {
        this.currentAccount = currentAccount;
        this.users = users;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        add(panel, BorderLayout.PAGE_START);

        panel.add(profilePanel(), BorderLayout.WEST);
        panel.add(blankPanel());
        panel.add(passwordEditPanel());

        JPanel postsPanel = new JPanel();

        postsPanel.setLayout(new GridLayout(posts.size() + 1, 1, 5, 5));

        postsPanel.add(guidePanel());

        for (Post post : posts) {
            if (post.isDeleted()) {
                continue;
            }

            if (post.sellerId() != currentAccount.id()) {
                continue;
            }

            JPanel postPanel = new PostPanel(post, posts, currentAccount, transactions);
            postsPanel.add(postPanel);
        }

        add(postsPanel, BorderLayout.PAGE_END);

        setVisible(false);
        setVisible(true);
    }

    private JPanel profilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setPreferredSize(new Dimension(170, 160));

        panel.add(new JLabel(" - 계정 정보 -"));
        panel.add(new JLabel("  닉네임: " + currentAccount.nickname()));
        panel.add(passwordEditButton());
        panel.add(new JLabel("- 작성한 글 목록"));
        return panel;
    }

    private JPanel blankPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(170, 160));
        return panel;
    }

    private JPanel passwordEditPanel() {
        passwordEditPanel = new JPanel();
        passwordEditPanel.setLayout(new GridLayout(5, 1));
        passwordEditPanel.setPreferredSize(new Dimension(300, 170));
        return passwordEditPanel;
    }

    private JButton passwordEditButton() {
        JButton button = new JButton("비밀번호 수정");
        button.addActionListener(e -> {
            passwordEditPanel.add(new JLabel("- 비밀번호 수정 -"));
            passwordEditPanel.add(new JLabel("수정할 비밀번호 입력: "));
            passwordEditPanel.add(passwordInputField());

            for (User user : users){
                if (user.id() == currentAccount.id()) {
                    passwordEditPanel.add(editButton(user));
                }
            }

            setVisible(false);
            setVisible(true);
        });
        return button;
    }

    private JButton editButton(User user) {
        JButton button = new JButton("수정");
        button.addActionListener(e -> {
            String password = passwordInputField.getText();

            if (password.length() != 0) {
                user.editPassword(password);

                passwordEditPanel.removeAll();

                passwordEditPanel.add(new JLabel("수정완료!"));

                saveUsers();
            }

            if (password.length() == 0) {
                passwordInputField.setText("입력된 비밀번호가 없습니다");
            }

            setVisible(false);
            setVisible(true);
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

    private JTextField passwordInputField() {
        passwordInputField = new JTextField(15);
        return passwordInputField;
    }

    private JPanel guidePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(196, 190, 190, 122));
        panel.setBorder(new LineBorder(Color.GRAY, 2, true));

        panel.add(transactionStatusLabel());
        panel.add(categoryLabel());
        panel.add(postTitleLabel());
        panel.add(sellerNicknameLabel());

        return panel;
    }

    private JLabel transactionStatusLabel() {
        JLabel label = new JLabel("거래상태");
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }

    private JLabel categoryLabel() {
        JLabel label = new JLabel("카테고리");
        label.setPreferredSize(new Dimension(100, 20));
        return label;
    }

    private JLabel postTitleLabel() {
        JLabel label = new JLabel("제목");
        label.setPreferredSize(new Dimension(350, 20));
        return label;
    }

    private JLabel sellerNicknameLabel() {
        JLabel label = new JLabel("작성자");
        label.setPreferredSize(new Dimension(150, 20));
        return label;
    }
}
