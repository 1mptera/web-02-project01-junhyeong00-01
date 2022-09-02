package models;

public class Transaction {
    public static final String TRANSACTION_COMPLETE = "거래완료";
    public static final String TRADING = "거래중";
    public static final String FOR_SALE = "판매중";

    private Long id;
    private long postId;
    private String sellerNickname;
    private long sellerId;
    private long buyerId;
    private String buyerNickname;
    private String status;

    public Transaction(long id, long postId, long sellerId, String sellerNickname,
                       long buyerId, String buyerNickname, String status) {
        this.id = id;
        this.postId = postId;
        this.sellerId = sellerId;
        this.sellerNickname = sellerNickname;
        this.buyerId = buyerId;
        this.buyerNickname = buyerNickname;
        this.status = status;
    }

    public long id() {
        return id;
    }

    public long postId() {
        return postId;
    }

    public String sellerNickname() {
        return sellerNickname;
    }

    public long sellerId() {
        return sellerId;
    }

    public long buyerId() {
        return buyerId;
    }

    public String buyerNickname() {
        return buyerNickname;
    }

    public String toCsvRow() {
        return id + "," +
                postId + "," +
                sellerId + "," +
                sellerNickname + "," +
                buyerId + "," +
                buyerNickname + "," +
                status;
    }

    @Override
    public String toString() {
        return "id: " + id + ", postId: " +
                postId + ", sellerId: " +
                sellerId + ", sellerNickname: " +
                sellerNickname + ", buyerId: " +
                buyerId + ", buyerNickname: " +
                buyerNickname + ", status: " +
                status;
    }

    public void completed() {
        this.status = TRANSACTION_COMPLETE;
    }

    public String isStatus() {
        return status;
    }

    public void updateStatus(Post post) {
        if (status.equals("거래완료")) {
            post.completeTransaction();
        }
    }
}
