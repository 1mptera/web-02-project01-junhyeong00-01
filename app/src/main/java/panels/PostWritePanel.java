package panels;

import models.Post;
import models.SecondHandItem;
import models.CurrentAccount;
import models.Transaction;
import utils.PostLoader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class PostWritePanel extends JPanel {
    private final List<Post> posts;

    private CurrentAccount currentAccount;

    private JComboBox comboBox;
    private JTextField postTitleInputField;
    private JTextArea postContentInputTextArea;
    private JTextField priceInputField;

    public PostWritePanel(List<Post> posts, CurrentAccount currentAccount) {
        this.posts = posts;
        this.currentAccount = currentAccount;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        panel.add(postTitleLabel());
        panel.add(postTitleInputField());
        panel.add(categoryLabel());
        panel.add(categoryComboBox());
        panel.add(priceLabel());
        panel.add(priceInputField());
        panel.add(postContentLabel());

        add(postContentPanel());
        add(postButtonPanel(), BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
    }

    private JComboBox categoryComboBox() {
        comboBox = new JComboBox(SecondHandItem.CATEGORY);
        return comboBox;
    }

    private JPanel postButtonPanel() {
        JPanel panel = new JPanel();
        panel.add(postButton());
        return panel;
    }

    private JPanel postContentPanel() {
        JPanel panel = new JPanel();
        panel.add(postContentInputTextArea());
        return panel;
    }

    private JLabel categoryLabel() {
        return new JLabel("- 카테고리 선택");
    }

    private JLabel postTitleLabel() {
        return new JLabel("- 제목:");
    }

    private JTextField postTitleInputField() {
        postTitleInputField = new JTextField(20);
        return postTitleInputField;
    }

    private JLabel priceLabel() {
        return new JLabel("- 가격");
    }

    private JTextField priceInputField() {
        priceInputField = new JTextField(20);
        return priceInputField;
    }

    private JLabel postContentLabel() {
        return new JLabel("- 내용:");
    }

    private JTextArea postContentInputTextArea() {
        postContentInputTextArea = new JTextArea(20, 25);
        postContentInputTextArea.setLineWrap(true);
        return postContentInputTextArea;
    }

    private JButton postButton() {
        JButton button = new JButton("등록하기");
        button.addActionListener(e -> {
            String postTitle = postTitleInputField.getText();
            String postContent = postContentInputTextArea.getText();
            String sellerName = currentAccount.nickname();
            String category = String.valueOf(comboBox.getSelectedItem());
            long id = posts.size() + 1;
            long sellerId = currentAccount.id();
            long price = Long.parseLong(priceInputField.getText());

            if (!category.equals(SecondHandItem.CATEGORY[0]) && postTitle.length() != 0
                    && postContent.length() != 0) {
                SecondHandItem secondHandItem = new SecondHandItem(price, category);
                Post post = new Post(id, postTitle, postContent,
                        sellerName, sellerId, secondHandItem.category(),
                        secondHandItem.price(), Transaction.FOR_SALE, false);
                posts.add(post);

                savePosts();

                removeAll();
            }

            setVisible(false);
            setVisible(true);
        });
        return button;
    }

    private void savePosts() {
        PostLoader postLoader = new PostLoader();
        try {
            postLoader.savePosts(posts);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
