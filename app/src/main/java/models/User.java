package models;

public class User {
    private String userName;
    private String password;
    private String nickname;

    public User(String userName, String password, String nickname) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }

    @Override
    public String toString() {
        return "id: " + userName
                + ", password: " + password
                + ", nickname: " + nickname;
    }

    public String nickname() {
        return nickname;
    }

    public String toCsvRow() {
        return userName + "," + password + "," + nickname;
    }
}
