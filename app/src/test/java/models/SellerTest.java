package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {
    @Test
    void creation() {
        Seller seller = new Seller(1, "토끼");

        assertEquals(1, seller.id());
        assertEquals("토끼", seller.nickname());
    }

    @Test
    void login() {
        Seller seller = new Seller(0, "");

        seller.login(1, "토끼");

        assertEquals(1, seller.id());
        assertEquals("토끼", seller.nickname());
    }

    @Test
    void logout() {
        Seller seller = new Seller(1, "토끼");

        seller.logout();

        assertEquals(-1, seller.id());
        assertEquals("", seller.nickname());
    }

    @Test
    void edit() {
        Seller seller = new Seller(1, "토끼");

        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", 2000, "판매중", false);
        seller.edit(post, "제목2", "내용2", "가구", 1000);

        assertEquals("제목2", post.title());
        assertEquals("내용2", post.content());
        assertEquals("가구", post.category());
        assertEquals(1000, post.secondHandItemPrice());
    }

    @Test
    void complete() {
        Seller seller = new Seller(1, "토끼");

        Transaction transaction = new Transaction(1, 1, 1, "토끼", 2, "사람", "거래중");

        seller.complete(transaction);

        assertEquals("거래완료", transaction.isStatus());
    }
}
