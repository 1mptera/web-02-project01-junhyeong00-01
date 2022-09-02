package models;

public class Buyer{
    private long id;
    private String nickname;

    public Buyer(long userId, String userNickname) {

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
}
