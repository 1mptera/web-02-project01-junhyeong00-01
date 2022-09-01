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

        List<Post> posts = postLoader.loadPosts();

        assertNotNull(posts);
    }

    @Test
    void parsePost() {
        PostLoader postLoader = new PostLoader();

        Post post = postLoader.parsePost("1,당근,팔아요,토끼,1,디지털기기,2000,판매중,false");

        assertEquals("id: 1, title: 당근, content: 팔아요, sellerNickname: 토끼," +
                " sellerId: 1, category: 디지털기기, secondHandItemPrice: 2000," +
                " transactionStatus: 판매중, deleted: false", post.toString());
    }
}
