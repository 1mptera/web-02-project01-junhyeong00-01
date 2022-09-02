package models;

public class CurrentAccount {
    private long id;
    private String nickname;

    public CurrentAccount(long userId, String userNickname) {
        this.id = userId;
        this.nickname = userNickname;
    }

    public long id() {
        return id;
    }

    public String nickname() {
        return nickname;
    }

    public void login(long id, String userNickname) {
        this.id = id;
        this.nickname = userNickname;
    }

    public void logout() {
        this.id = -1;
        this.nickname = "";
    }

    public void edit(Post post, String postTitle, String postContent, String category, long secondHandItemPrice) {
        post.change(postTitle, postContent, category, secondHandItemPrice);
    }

    public void complete(Transaction transaction) {
        transaction.completed();
    }
}
