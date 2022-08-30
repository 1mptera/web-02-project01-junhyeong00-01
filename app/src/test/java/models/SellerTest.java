package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {
    @Test
    void creation() {
        Seller seller = new Seller(1, "토끼");

        assertEquals(1, seller.userId());
        assertEquals("토끼", seller.userName());
    }

    @Test
    void login() {
        Seller seller = new Seller(0, "");

        seller.login(1, "토끼");

        assertEquals(1, seller.userId());
        assertEquals("토끼", seller.userName());
    }

    @Test
    void logout() {
        Seller seller = new Seller(1, "토끼");

        seller.logout();

        assertEquals(-1, seller.userId());
        assertEquals("손님모드", seller.userName());
    }

    @Test
    void edit() {
        Seller seller = new Seller(1, "토끼");

        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", false);
        seller.edit(post, "제목2", "내용2", "가구");

        assertEquals("제목2", post.title());
        assertEquals("내용2", post.content());
        assertEquals("가구", post.category());
    }
}
