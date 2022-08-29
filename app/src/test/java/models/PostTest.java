package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    @Test
    void creation() {
        Post post = new Post("제목", "내용");

        assertEquals("제목", post.title());
        assertEquals("내용", post.content());
    }

    @Test
    void toCsvRow() {
        Post post = new Post("제목", "내용");

        assertEquals("제목,내용", post.toCsvRow());
    }
}
