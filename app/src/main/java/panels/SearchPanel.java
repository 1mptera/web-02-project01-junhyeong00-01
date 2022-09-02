package panels;

import models.Post;
import models.SecondHandItem;
import models.CurrentAccount;
import models.Transaction;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class SearchPanel extends JPanel {
    private final List<Post> posts;
    private final List<Transaction> transactions;

    private CurrentAccount currentAccount;

    private JPanel searchPanel;
    private JTextField searchField;
    private JComboBox categoryComboBox;
    private JComboBox componentComboBox;
    private JComboBox transactionStatusComboBox;
    private JPanel postsPanel;

    public SearchPanel(List<Post> posts, CurrentAccount currentAccount, List<Transaction> transactions) {
        this.posts = posts;
        this.currentAccount = currentAccount;
        this.transactions = transactions;

        setOpaque(false);
        setLayout(new BorderLayout());

        add(searchPanel(), BorderLayout.NORTH);
        add(guidePanel());
        add(postsPanel(), BorderLayout.SOUTH);
    }

    private JPanel searchPanel() {
        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 255, 255, 190));

        String[] postComponents = {Post.TITLE, Post.CONTENT, Post.SELLER_NICKNAME};
        componentComboBox = new JComboBox(postComponents);
        searchPanel.add(componentComboBox);

        String[] transactionStatus = {Transaction.FOR_SALE, Transaction.TRANSACTION_COMPLETE};
        transactionStatusComboBox = new JComboBox(transactionStatus);
        searchPanel.add(transactionStatusComboBox);

        categoryComboBox = new JComboBox(SecondHandItem.CATEGORY);
        searchPanel.add(categoryComboBox);

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchPanel.add(searchButton());

        return searchPanel;
    }

    private JPanel postsPanel() {
        postsPanel = new JPanel();
        postsPanel.setLayout(new GridLayout(posts.size(), 1, 5, 5));
        return postsPanel;
    }

    private JButton searchButton() {
        JButton button = new JButton("검색");
        button.addActionListener(event -> {
            postsPanel.removeAll();

            String text = searchField.getText();
            String postComponent = String.valueOf(componentComboBox.getSelectedItem());
            String category = String.valueOf(categoryComboBox.getSelectedItem());
            String transactionStatus = String.valueOf(transactionStatusComboBox.getSelectedItem());

            for (Post post : posts) {
                if (post.isDeleted()) {
                    continue;
                }

                if (postComponent.equals(Post.TITLE)) {
                    if (!post.title().contains(text)) {
                        continue;
                    }
                }

                if (postComponent.equals(Post.CONTENT)) {
                    if (!post.content().contains(text)) {
                        continue;
                    }
                }

                if (postComponent.equals(Post.SELLER_NICKNAME)) {
                    if (!post.sellerNickname().contains(text)) {
                        continue;
                    }
                }

                if (!category.equals(SecondHandItem.CATEGORY[0])) {
                    if (!category.equals(post.category())) {
                        continue;
                    }
                }

                if (!transactionStatus.equals(post.transactionStatus())){
                    continue;
                }

                JPanel postPanel = new PostPanel(post, posts, currentAccount, transactions);
                postsPanel.add(postPanel);
            }

            setVisible(false);
            setVisible(true);
        });
        return button;
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
