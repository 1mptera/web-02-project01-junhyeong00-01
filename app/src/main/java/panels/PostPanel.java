package panels;

import frames.postsFrame;
import models.Post;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PostPanel extends JPanel {
    private Post post;
    private List<Post> posts;

    public PostPanel(Post post, List<Post> posts) {
        this.post = post;
        this.posts = posts;

        add(postTitleLabel(post));
    }

    private JLabel postTitleLabel(Post post) {
        JLabel label = new JLabel(post.title());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postsFrame(post);
            }
        });
        return label;
    }
}
