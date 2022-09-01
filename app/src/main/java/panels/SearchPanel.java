package panels;

import models.Post;
import models.SecondHandItem;
import models.Seller;
import models.Transaction;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

public class SearchPanel extends JPanel {
    private final JPanel searchPanel;
    private final JPanel postsPanel;
    private final JTextField searchField;
    private final JComboBox categoryComboBox;
    private final JComboBox componentComboBox;
    private List<Post> posts;
    private Seller seller;

    public SearchPanel(List<Post> posts, Seller seller) {
        this.posts = posts;
        this.seller = seller;

        setOpaque(false);
        setLayout(new BorderLayout());

        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 255, 255, 190));
        add(searchPanel, BorderLayout.NORTH);

        String[] postComponents = {Post.TITLE, Post.CONTENT, Post.SELLER_NICKNAME};
        componentComboBox = new JComboBox(postComponents);
        searchPanel.add(componentComboBox);

        JComboBox comboBox = new JComboBox(Transaction.TRANSACTION_STATUS);
        searchPanel.add(comboBox);

        categoryComboBox = new JComboBox(SecondHandItem.CATEGORY);
        searchPanel.add(categoryComboBox);

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchPanel.add(searchButton());

        postsPanel = new JPanel();
        postsPanel.setLayout(new GridLayout(posts.size(), 1, 5, 5));
        add(postsPanel, BorderLayout.SOUTH);

    }

    private JButton searchButton() {
        JButton button = new JButton("검색");
        button.addActionListener(event -> {
            postsPanel.removeAll();

            String text = searchField.getText();
            String postComponent = String.valueOf(componentComboBox.getSelectedItem());
            String category = String.valueOf(categoryComboBox.getSelectedItem());

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

                JPanel postPanel = new PostPanel(post, posts, seller);
                postsPanel.add(postPanel);
            }

            setVisible(false);
            setVisible(true);
        });
        return button;
    }
}
