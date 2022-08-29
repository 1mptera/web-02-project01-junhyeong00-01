package application;

import models.Post;
import panels.PostWritePanel;
import panels.postsPanel;
import utils.PostLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.List;

public class SecondHandMarket {
    private List<Post> posts;

    private JFrame frame;
    private JPanel contentPanel;

    public static void main(String[] args) throws FileNotFoundException {
        SecondHandMarket application = new SecondHandMarket();
        application.run();
    }

    public SecondHandMarket() throws FileNotFoundException {
        PostLoader postLoader = new PostLoader();
        posts = postLoader.loadPost();
    }

    public void run() {
        initFrame();

        initMenu();

        initContentPanel();

        frame.setVisible(true);
    }

    public void initFrame() {
        frame = new JFrame("중고 마켓");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocation(200, 100);
    }

    public void initMenu() {
        JPanel panel = new JPanel();

        panel.add(createHomeButton());
        panel.add(createAddPostButton());

        frame.add(panel, BorderLayout.PAGE_START);
    }

    private JButton createHomeButton() {
        JButton button = new JButton("홈");
        button.setPreferredSize(new Dimension(130, 45));
        button.addActionListener(e -> {
            JPanel postPanel = new postsPanel(posts);

            showContentPanel(postPanel);
        });

        return button;
    }

    private JButton createAddPostButton() {
        JButton button = new JButton("판매 글 등록");
        button.setPreferredSize(new Dimension(130, 45));
        button.addActionListener(e -> {
            JPanel postWritePanel = new PostWritePanel(posts);

            showContentPanel(postWritePanel);
        });

        return button;
    }

    private void initContentPanel() {
        contentPanel = new JPanel();

        frame.add(contentPanel);
    }

    private void showContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);

        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }
}
