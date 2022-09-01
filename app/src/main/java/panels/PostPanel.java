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
import java.awt.GridLayout;
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
        setPreferredSize(new Dimension(700, 30));
//        setLayout(new GridLayout(1, 4));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postFrame(post, seller, posts);
            }
        });

        add(transactionStatusLabel());
        add(categoryLabel(post));
        add(postTitleLabel(post));
        add(sellerNicknameLabel(post));
    }

    private JLabel transactionStatusLabel() {
        JLabel label = new JLabel("판매중");
        label.setPreferredSize(new Dimension(70,20));
        return label;
    }

    private JLabel categoryLabel(Post post) {
        JLabel label = new JLabel(post.category());
        label.setPreferredSize(new Dimension(100,20));
        return label;
    }

    private JLabel postTitleLabel(Post post) {
        JLabel label = new JLabel(post.title());
        label.setPreferredSize(new Dimension(350,20));
        return label;
    }

    private JLabel sellerNicknameLabel(Post post) {
        JLabel label = new JLabel(" 작성자: " + post.sellerNickname());
        label.setPreferredSize(new Dimension(150,20));
        return label;
    }
}
