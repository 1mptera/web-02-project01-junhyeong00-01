package panels;

import models.Buyer;
import models.Post;
import models.Seller;
import models.Transaction;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class postsPanel extends JPanel {
    public postsPanel(List<Post> posts, Seller seller, Buyer buyer, List<Transaction> transactions) {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(posts.size() + 1, 1, 5, 5));

        panel.add(guidePanel());

        for (Post post : posts) {
            if (post.isDeleted()) {
                continue;
            }

            JPanel postPanel = new PostPanel(post, posts, seller, buyer, transactions);
            panel.add(postPanel);
        }

        add(panel);
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
