package models;

public class User {
    private String userName;
    private String password;
    private String nickname;
    private long id;

    public User(String userName, String password, String nickname, long id) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.id = id;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }

    @Override
    public String toString() {
        return "id: "+ id
                + ", userName: " + userName
                + ", password: " + password
                + ", nickname: " + nickname;
    }

    public String nickname() {
        return nickname;
    }

    public String toCsvRow() {
        return id + "," + userName + "," + password + "," + nickname;
    }

    public long id() {
        return id;
    }
}
