package frames;

import models.Post;
import models.Seller;
import panels.PostEditPanel;
import utils.PostLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class postFrame extends JFrame {
    private final JPanel postPanel;
    private Post post;
    private Seller seller;
    private List<Post> posts;
    private JPanel editPanel;

    public postFrame(Post post, Seller seller, List<Post> posts) {
        this.post = post;
        this.seller = seller;
        this.posts = posts;

        this.setTitle(post.title());
        this.setSize(350, 550);
        this.setLocation(750, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        postPanel = new JPanel();
        postPanel.setLayout(new GridLayout(4, 1, 10, 10));
        add(postPanel, BorderLayout.NORTH);

        postPanel.add(new JLabel("작성자: " + post.sellerNickname()));
        postPanel.add(new JLabel("카테고리: " + post.category()));
        postPanel.add(new JLabel("가격: " + post.secondHandItemPrice() + "원"));
        postPanel.add(new JLabel(""));

        JPanel panel1 = new JPanel();
        add(panel1);
        panel1.add(new JLabel(post.content()));

        add(editPanel(), BorderLayout.SOUTH);
    }

    private JPanel editPanel() {
        editPanel = new JPanel();
        if (seller.userId() == post.sellerId()) {
            editPanel.add(editButton());
            editPanel.add(deleteButton());
        }
        return editPanel;
    }

    private JButton editButton() {
        JButton button = new JButton("편집");
        button.addActionListener(e -> {
            JPanel postEditPanel = new PostEditPanel(post, seller, posts);

            editPanel.removeAll();
            postPanel.removeAll();
            postPanel.add(postEditPanel);

            postPanel.setVisible(false);
            postPanel.setVisible(true);
        });
        return button;
    }

    private JButton deleteButton() {
        JButton button = new JButton("삭제");
        button.addActionListener(e -> {
            post.delete();
            PostLoader postLoader = new PostLoader();
            try {
                postLoader.savePosts(posts);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });
        return button;
    }
}
