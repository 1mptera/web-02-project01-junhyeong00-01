package panels;

import frames.postFrame;
import models.Post;
import models.Seller;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PostPanel extends JPanel {
    private Post post;
    private List<Post> posts;
    private Seller seller;

    public PostPanel(Post post, List<Post> posts, Seller seller) {
        this.post = post;
        this.posts = posts;
        this.seller = seller;

        setBorder(new LineBorder(Color.GRAY, 1, true));
        setPreferredSize(new Dimension(350, 50));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postFrame(post, seller, posts);
            }
        });

        add(postTitleLabel(post));
    }

    private JLabel postTitleLabel(Post post) {
        JLabel label = new JLabel(post.title());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postFrame(post, seller, posts);
            }
        });
        return label;
    }
}
