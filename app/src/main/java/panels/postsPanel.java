package panels;

import models.Post;
import models.Seller;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

public class postsPanel extends JPanel {
    public postsPanel(List<Post> posts, Seller seller) {
        JPanel panel = new JPanel();
//        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.setLayout(new GridLayout(posts.size(), 1, 5, 5));

//        panel.setPreferredSize(new Dimension(350, 300));
//        scrollPane.setViewportView(panel);

        for (Post post : posts) {
            if (post.isDeleted()) {
                continue;
            }

            JPanel postPanel = new PostPanel(post, posts, seller);
            panel.add(postPanel);
        }

        add(panel);
    }
}
