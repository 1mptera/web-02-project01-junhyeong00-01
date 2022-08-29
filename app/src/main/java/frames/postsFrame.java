package frames;

import models.Post;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class postsFrame extends JFrame {
    public postsFrame(Post post) {
        this.setTitle(post.title());
        this.setSize(300, 300);
        this.setLocation(750, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        add(new JLabel(post.content()));
    }
}
