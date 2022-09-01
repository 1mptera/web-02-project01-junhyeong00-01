package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void creation() {
        Transaction transaction = new Transaction(1, 1, 2, "토끼", 3, "감자", "판매중");

        assertEquals(1, transaction.id());
        assertEquals(1, transaction.postId());
        assertEquals("토끼", transaction.sellerNickname());
        assertEquals(2, transaction.sellerId());
        assertEquals(3, transaction.buyerId());
        assertEquals("감자", transaction.buyerNickname());
        assertEquals("판매중", transaction.isStatus());
    }

    @Test
    void toCsvRow() {
        Transaction transaction = new Transaction(1, 1, 2, "토끼", 3, "감자", "판매중");

        assertEquals("1,1,2,토끼,3,감자,판매중", transaction.toCsvRow());
    }

    @Test
    void completed() {
        Transaction transaction = new Transaction(1, 1, 1, "토끼", 2, "사람", "판매중");

        transaction.completed();

        assertEquals("거래완료",transaction.isStatus());
    }

    @Test
    void updateStatus() {
        Transaction transaction = new Transaction(1, 1, 1, "토끼", 2, "사람", "거래완료");

        Post post = new Post(1, "제목", "내용", "토끼", 1, "디지털기기", 2000, "판매중", false);

        transaction.updateStatus(post);

        assertEquals("거래완료", post.transactionStatus());
    }
}
