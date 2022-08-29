package panels;

import models.Post;
import utils.PostLoader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class PostWritePanel extends JPanel {
    private List<Post> posts;

    private JTextField postTitleInputField;
    private JTextArea postContentInputTextArea;

    public PostWritePanel(List<Post> posts) {
        this.posts = posts;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        panel.add(postTitleLabel());
        panel.add(postTitleInputField());

        panel.add(postContentLabel());
        panel.add(postContentInputTextArea());

//       panel.add(new JLabel("- 카테고리 선택"));//TODO 카테고리 기능 추가

        panel.add(postButton());

        add(panel);
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
        postContentInputTextArea = new JTextArea();
        return postContentInputTextArea;
    }

    private JButton postButton() {
        JButton button = new JButton("등록하기");
        button.addActionListener(e -> {
            String postTitle = postTitleInputField.getText();
            String postContent = postContentInputTextArea.getText();

            Post post = new Post(postTitle, postContent);
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
