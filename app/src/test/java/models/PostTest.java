package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void creation() {
        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", false);

        assertEquals("제목", post.title());
        assertEquals("내용", post.content());
        assertEquals(1, post.id());
        assertEquals("토끼", post.sellerNickname());
        assertEquals(1, post.sellerId());
        assertEquals("디지털기기", post.category());
        assertEquals(false, post.isDeleted());
    }

    @Test
    void toCsvRow() {
        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", false);

        assertEquals("1,제목,내용,토끼,1,디지털기기,false", post.toCsvRow());
    }

    @Test
    void change() {
        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", false);

        post.change("제목2", "내용2", "가구");

        assertEquals("제목2", post.title());
        assertEquals("내용2", post.content());
        assertEquals("가구", post.category());
    }
}
