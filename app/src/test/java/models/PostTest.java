package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void creation() {
        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기");

        assertEquals("제목", post.title());
        assertEquals("내용", post.content());
        assertEquals(1, post.id());
        assertEquals("토끼", post.sellerNickname());
        assertEquals(1, post.sellerId());
        assertEquals("디지털기기", post.category());
    }

    @Test
    void toCsvRow() {
        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기");

        assertEquals("1,제목,내용,토끼,1,디지털기기", post.toCsvRow());
    }
}
