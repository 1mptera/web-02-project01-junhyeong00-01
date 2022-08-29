package panels;

import models.Post;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;

public class postsPanel extends JPanel {
    public postsPanel(List<Post> posts) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(posts.size(), 1));

        for (Post post : posts) {
            JPanel postPanel = new PostPanel(post, posts);
            panel.add(postPanel);
        }

        add(panel);
    }
}
