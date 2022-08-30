package application;

import models.Post;
import models.Seller;
import models.User;
import panels.ImagePanel;
import panels.LoginPanel;
import panels.PostWritePanel;
import panels.postsPanel;
import utils.PostLoader;
import utils.UserLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.List;

public class SecondHandMarket {
    private List<Post> posts;
    private List<User> users;

    private JFrame frame;
    private JPanel contentPanel;
    private JPanel loginPanel;
    private ImagePanel imagePanel;
    private Seller seller = new Seller(-1, "");
    private JPanel menuPanel;

    public static void main(String[] args) throws FileNotFoundException {
        SecondHandMarket application = new SecondHandMarket();
        application.run();
    }

    public SecondHandMarket() throws FileNotFoundException {
        PostLoader postLoader = new PostLoader();
        posts = postLoader.loadPost();

        UserLoader userLoader = new UserLoader();
        users = userLoader.loadUser();
    }

    public void run() {
        initFrame();

        initMenu();

        initContentPanel();

        frame.setVisible(true);
    }

    public void initFrame() {
        frame = new JFrame("우리 마켓");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocation(200, 100);

        imagePanel = new ImagePanel("data/background2.png");
        imagePanel.setLayout(new BorderLayout());
        frame.add(imagePanel);
        frame.setVisible(true);
    }

    public void initMenu() {
        menuPanel = new JPanel();
        menuPanel.setOpaque(false);

        menuPanel.add(homeButton());
        menuPanel.add(addPostButton());
        menuPanel.add(logoutButton());

        imagePanel.add(menuPanel, BorderLayout.PAGE_START);
    }

    private JButton homeButton() {
        JButton button = new JButton("홈");
        button.setPreferredSize(new Dimension(130, 45));
        button.addActionListener(e -> {
            if (seller.userId() != -1) {
                JPanel postPanel = new postsPanel(posts, seller);

                showContentPanel(postPanel);
            }
        });

        return button;
    }

    private JButton addPostButton() {
        JButton button = new JButton("판매 글 등록");
        button.setPreferredSize(new Dimension(130, 45));
        button.addActionListener(e -> {
            if (seller.userId() != -1) {
                JPanel postWritePanel = new PostWritePanel(posts, seller);

                showContentPanel(postWritePanel);
            }
        });

        return button;
    }

    private JButton logoutButton() {
        JButton button = new JButton("로그아웃");
        button.setPreferredSize(new Dimension(130, 45));
        button.addActionListener(e -> {
            seller.logout();
            loginPanel = new LoginPanel(users, seller);

            showContentPanel(loginPanel);
        });

        return button;
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);

        loginPanel = new LoginPanel(users, seller);
        showContentPanel(loginPanel);

        imagePanel.add(contentPanel);
    }

    private void showContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);

        contentPanel.setVisible(false);
        contentPanel.setVisible(true);

        frame.setVisible(true);
    }
}
