package panels;

import models.Post;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class postsPanel extends JPanel {
    public postsPanel(List<Post> posts) {
        JPanel panel = new JPanel();
//        JScrollPane scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.setLayout(new GridLayout(posts.size(), 1));

//        panel.setPreferredSize(new Dimension(350, 300));
//        scrollPane.setViewportView(panel);

        for (Post post : posts) {
            JPanel postPanel = new PostPanel(post, posts);
            panel.add(postPanel);
        }

        add(panel);
    }
}
