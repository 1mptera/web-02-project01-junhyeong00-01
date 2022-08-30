package models;

public class Post {
    private long id;
    private String title;
    private String content;
    private String sellerNickname;
    private long sellerId;
    private String category;
    private boolean deleted;

    public Post(long id, String title, String content, String sellerNickname, long sellerId, String category, boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sellerNickname = sellerNickname;
        this.sellerId = sellerId;
        this.category = category;
        this.deleted = deleted;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public String toCsvRow() {
        return id + "," + title + "," + content
                + "," + sellerNickname + "," + sellerId
                + "," + category + "," + deleted;
    }

    @Override
    public String toString() {
        return title + " - " + content;
    }

    public long id() {
        return id;
    }

    public String sellerNickname() {
        return sellerNickname;
    }

    public long sellerId() {
        return sellerId;
    }

    public String category() {
        return category;
    }

    public void change(String postTitle, String postContent, String category) {
        this.title = postTitle;
        this.content = postContent;
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        deleted = true;
    }
}
