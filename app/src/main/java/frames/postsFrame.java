package frames;

import models.Post;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class postsFrame extends JFrame {
    public postsFrame(Post post) {
        this.setTitle(post.title());
        this.setSize(300, 300);
        this.setLocation(750, 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        add(panel);

        panel.add(new JLabel("작성자: " + post.sellerNickname()));
        panel.add(new JLabel("카테고리: " + post.category()));
        panel.add(new JLabel("가격: " ));
        panel.add(new JLabel(post.content()));
    }
}
