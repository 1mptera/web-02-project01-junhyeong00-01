package utils;

import models.Post;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostLoaderTest {
    @Test
    void loadPost() throws FileNotFoundException {
        PostLoader postLoader = new PostLoader();

        List<Post> posts = postLoader.loadPost();

        assertNotNull(posts);
    }

    @Test
    void parsePost() {
        PostLoader postLoader = new PostLoader();

        Post post = postLoader.parsePost("제목,내용");

        assertEquals("제목 - 내용", post.toString());
    }
}
