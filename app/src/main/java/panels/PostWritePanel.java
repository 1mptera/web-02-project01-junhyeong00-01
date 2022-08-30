package panels;

import models.Post;
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

public class PostWritePanel extends JPanel {
    private final JComboBox comboBox;
    private List<Post> posts;
    private Seller seller;

    private JTextField postTitleInputField;
    private JTextArea postContentInputTextArea;

    public PostWritePanel(List<Post> posts, Seller seller) {
        this.posts = posts;
        this.seller = seller;
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 5, 5));

        panel.add(postTitleLabel());
        panel.add(postTitleInputField());

        panel.add(new JLabel("- 카테고리 선택"));
        String[] words = {"디지털기기", "생활가전", "가구", "주방", "의류", "게임", "도서", "스포츠", "기타"};
        comboBox = new JComboBox(words);
        panel.add(comboBox);
        panel.add(new JLabel("- 가격"));
        panel.add(new JTextField(20));

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
        return postTitleInputField;
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
            long id = posts.size() + 1;
            String sellerName = seller.userName();
            long sellerId = seller.userId();
            String category = String.valueOf(comboBox.getSelectedItem());

            Post post = new Post(id, postTitle, postContent, sellerName, sellerId, category);
            posts.add(post);

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
