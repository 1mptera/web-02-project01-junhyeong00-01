package panels;

import frames.postFrame;
import models.Post;
import models.CurrentAccount;
import models.Transaction;
import utils.PostLoader;
import utils.TransactionLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class TransactionPanel extends JPanel {
    private final List<Post> posts;
    private final List<Transaction> transactions;

    private Post post;
    private CurrentAccount currentAccount;
    private Transaction transaction;

    public TransactionPanel(Post post, List<Post> posts, CurrentAccount currentAccount,
                             List<Transaction> transactions, Transaction transaction) {
        this.post = post;
        this.posts = posts;
        this.currentAccount = currentAccount;
        this.transactions = transactions;
        this.transaction = transaction;

        setBorder(new LineBorder(Color.GRAY, 1, true));
        setPreferredSize(new Dimension(700, 30));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame postFrame = new postFrame(post, currentAccount, posts, transactions);
            }
        });

        if (currentAccount.id() == transaction.sellerId()) {
            add(sellLabel());
            add(categoryLabel());
            add(postTitleLabel());
            add(buyerNicknameLabel());
            add(transactionCompleteButton());
        }

        if (currentAccount.id() == transaction.buyerId()) {
            add(buyLabel());
            add(categoryLabel());
            add(postTitleLabel());
            add(sellerNicknameLabel());
            add(transactionCompleteButton());
        }
    }

    private JLabel sellLabel() {
        JLabel label = new JLabel("판매");
        label.setPreferredSize(new Dimension(70, 20));
        return label;
    }

    private JLabel buyLabel() {
        JLabel label = new JLabel("구매");
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
        label.setPreferredSize(new Dimension(280, 20));
        return label;
    }

    private JLabel sellerNicknameLabel() {
        JLabel label = new JLabel(" 판매자: " + transaction.sellerNickname());
        label.setPreferredSize(new Dimension(150, 20));
        return label;
    }

    private JLabel buyerNicknameLabel() {
        JLabel label = new JLabel(" 구매자: " + transaction.buyerNickname());
        label.setPreferredSize(new Dimension(150, 20));
        return label;
    }

    private JButton transactionCompleteButton() {
        JButton button = new JButton(transaction.isStatus());
        button.setPreferredSize(new Dimension(70, 20));
        button.addActionListener(e -> {
            if (currentAccount.id() == transaction.sellerId()) {
                currentAccount.complete(transaction);

                transaction.updateStatus(post);

                savePosts();
                saveTransactions();
            }

            button.setText(transaction.isStatus());
            setVisible(false);
            setVisible(true);
        });
        return button;
    }

    private void savePosts() {
        PostLoader postLoader = new PostLoader();
        try {
            postLoader.savePosts(posts);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void saveTransactions() {
        TransactionLoader transactionLoader = new TransactionLoader();
        try {
            transactionLoader.saveTransactions(transactions);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
