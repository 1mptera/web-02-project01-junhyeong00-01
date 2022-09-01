package panels;

import models.Buyer;
import models.Post;
import models.Seller;
import models.Transaction;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class TransactionsPanel extends JPanel {
    public TransactionsPanel(List<Post> posts, Seller seller, Buyer buyer, List<Transaction> transactions) {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(transactions.size() + 1, 1, 5, 5));

        panel.add(guidePanel());

        for (Transaction transaction : transactions) {

            for (Post post : posts) {
                if (transaction.postId() != post.id()) {
                    continue;
                }

                if (transaction.sellerId() != seller.id() && transaction.buyerId() != buyer.id()) {
                    continue;
                }

                JPanel transactionPanel = new TransactionPanel(post, posts, seller, buyer, transactions, transaction);
                panel.add(transactionPanel);
            }
        }

        add(panel);
    }

    private JPanel guidePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(196, 190, 190, 122));
        panel.setBorder(new LineBorder(Color.GRAY, 2, true));

        panel.add(classificationLabel());
        panel.add(categoryLabel());
        panel.add(postTitleLabel());
        panel.add(traderNicknameLabel());
        panel.add(transactionStatusLabel());

        return panel;
    }

    private JLabel classificationLabel() {
        JLabel label = new JLabel("분류");
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }

    private JLabel categoryLabel() {
        JLabel label = new JLabel("카테고리");
        label.setPreferredSize(new Dimension(100, 20));
        return label;
    }

    private JLabel postTitleLabel() {
        JLabel label = new JLabel("판매글 제목");
        label.setPreferredSize(new Dimension(280, 20));
        return label;
    }

    private JLabel traderNicknameLabel() {
        JLabel label = new JLabel(" 거래자");
        label.setPreferredSize(new Dimension(150, 20));
        return label;
    }

    private JLabel transactionStatusLabel() {
        JLabel label = new JLabel(" 거래 상태");
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }
}
