package panels;

import models.Post;
import models.SecondHandItem;
import models.Seller;
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

public class PostEditPanel extends JPanel {
    private final Post post;
    private final Seller seller;
    private List<Post> posts;
    private final JComboBox comboBox;
    private JTextField postTitleInputField;
    private JTextArea postContentInputTextArea;
    private JTextField priceInputField;

    public PostEditPanel(Post post, Seller seller, List<Post> posts) {
        this.post = post;
        this.seller = seller;
        this.posts = posts;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        panel.add(postTitleLabel());
        panel.add(postTitleInputField());

        panel.add(new JLabel("- 카테고리 선택"));
        comboBox = new JComboBox(SecondHandItem.CATEGORY);
        panel.add(comboBox);
        panel.add(priceLabel());
        panel.add(priceInputField());

        panel.add(postContentLabel());

        JPanel panel1 = new JPanel();
        panel1.add(postContentInputTextArea());
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.add(postButton());
        add(panel2, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
    }

    private JLabel postTitleLabel() {
        return new JLabel("- 제목:");
    }

    private JTextField postTitleInputField() {
        postTitleInputField = new JTextField(20);
        postTitleInputField.setText(post.title());
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
        postContentInputTextArea.setText(post.content());
        return postContentInputTextArea;
    }

    private JButton postButton() {
        JButton button = new JButton("저장");
        button.addActionListener(e -> {
            String postTitle = postTitleInputField.getText();
            String postContent = postContentInputTextArea.getText();
            String category = String.valueOf(comboBox.getSelectedItem());
            long secondHandItemPrice = Long.parseLong(priceInputField.getText());

            seller.edit(post, postTitle, postContent, category, secondHandItemPrice);

            PostLoader postLoader = new PostLoader();
            try {
                postLoader.savePosts(posts);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return button;
    }
}
