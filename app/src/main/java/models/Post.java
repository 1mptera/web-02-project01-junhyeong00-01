package models;

public class Post {
    private long id;
    private String title;
    private String content;
    private String sellerNickname;
    private long sellerId;
    private String category;

    public Post(long id, String title, String content, String sellerNickname, long sellerId, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sellerNickname = sellerNickname;
        this.sellerId = sellerId;
        this.category = category;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public String toCsvRow() {
        return id + "," + title + "," + content
                + "," + sellerNickname + "," + sellerId + "," + category;
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
}
