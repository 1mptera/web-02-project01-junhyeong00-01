package panels;

import frames.postFrame;
import models.Post;
import models.CurrentAccount;
import models.Transaction;

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
    private final Post post;

    public PostPanel(Post post, List<Post> posts, CurrentAccount currentAccount,
                     List<Transaction> transactions) {
        this.post = post;

        setBorder(new LineBorder(Color.GRAY, 1, true));
        setPreferredSize(new Dimension(700, 30));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postFrame(post, currentAccount, posts, transactions);
            }
        });

        add(transactionStatusLabel());
        add(categoryLabel());
        add(postTitleLabel());
        add(sellerNicknameLabel());
    }

    private JLabel transactionStatusLabel() {
        JLabel label = new JLabel(post.transactionStatus());
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }

    private JLabel categoryLabel() {
        JLabel label = new JLabel(post.category());
        label.setPreferredSize(new Dimension(100, 20));
        return label;
    }

    private JLabel postTitleLabel() {
        JLabel label = new JLabel(post.title());
        label.setPreferredSize(new Dimension(350, 20));
        return label;
    }

    private JLabel sellerNicknameLabel() {
        JLabel label = new JLabel(post.sellerNickname());
        label.setPreferredSize(new Dimension(150, 20));
        return label;
    }
}
