package models;

public class Post {
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public String toCsvRow() {
        return title + "," + content;
    }

    @Override
    public String toString() {
        return title + " - " + content;
    }
}
