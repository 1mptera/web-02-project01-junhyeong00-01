package frames;

import models.Post;
import models.CurrentAccount;
import models.Transaction;
import panels.PostEditPanel;
import utils.PostLoader;
import utils.TransactionLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

public class postFrame extends JFrame {
    private final List<Transaction> transactions;
    private final List<Post> posts;

    private Post post;
    private CurrentAccount currentAccount;

    private final JPanel postPanel;
    private JPanel editPanel;

    public postFrame(Post post, CurrentAccount currentAccount, List<Post> posts, List<Transaction> transactions) {
        this.post = post;
        this.currentAccount = currentAccount;
        this.posts = posts;
        this.transactions = transactions;

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
        if (currentAccount.id() == post.sellerId()) {
            editPanel.add(editButton());
            editPanel.add(deleteButton());
        }

        if (currentAccount.id() != post.sellerId() && !post.transactionStatus().equals("거래완료")) {
            editPanel.add(transactionButton());
        }
        return editPanel;
    }

    private JButton editButton() {
        JButton button = new JButton("편집");
        button.addActionListener(e -> {
            JPanel postEditPanel = new PostEditPanel(post, currentAccount, posts);

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

    private JButton transactionButton() {
        JButton button = new JButton("거래");
        button.addActionListener(e -> {
            long transactionId = transactions.size() + 1;
            Transaction transaction = new Transaction(transactionId, post.id(), post.sellerId(), post.sellerNickname(), currentAccount.id(), currentAccount.nickname(), Transaction.TRADING);
            transactions.add(transaction);

            saveTransactions();

            dispose();
        });
        return button;
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
