package models;

public class Post {
    public static final String SELLER_NICKNAME = "판매자";
    public static final String TITLE = "제목";
    public static final String CONTENT = "내용";

    private long id;
    private String title;
    private String content;
    private String sellerNickname;
    private long sellerId;
    private String category;
    private long secondHandItemPrice;
    private String transactionStatus;
    private boolean deleted;

    public Post(long id, String title, String content, String sellerNickname, long sellerId, String category, long secondHandItemPrice, String transactionStatus, boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sellerNickname = sellerNickname;
        this.sellerId = sellerId;
        this.category = category;
        this.secondHandItemPrice = secondHandItemPrice;
        this.transactionStatus = transactionStatus;
        this.deleted = deleted;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public String toCsvRow() {
        return id + "," +
                title + "," +
                content + "," +
                sellerNickname + "," +
                sellerId + "," +
                category + "," +
                secondHandItemPrice + "," +
                transactionStatus + "," +
                deleted;
    }

    @Override
    public String toString() {
        return "id: " + id + ", title: " +
                title + ", content: " +
                content + ", sellerNickname: " +
                sellerNickname + ", sellerId: " +
                sellerId + ", category: " +
                category + ", secondHandItemPrice: " +
                secondHandItemPrice + ", transactionStatus: " +
                transactionStatus + ", deleted: " +
                deleted;
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

    public void change(String postTitle, String postContent, String category, long secondHandItemPrice) {
        this.title = postTitle;
        this.content = postContent;
        this.category = category;
        this.secondHandItemPrice = secondHandItemPrice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        deleted = true;
    }

    public long secondHandItemPrice() {
        return secondHandItemPrice;
    }

    public void completeTransaction() {
        transactionStatus = "거래완료";
    }

    public String transactionStatus() {
        return transactionStatus;
    }
}
