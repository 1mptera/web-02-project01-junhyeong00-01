package utils;

import models.Post;
import models.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class userLoaderTest {
    @Test
    void loadUser() throws FileNotFoundException {
        UserLoader userLoader = new UserLoader();

        List<User> users = userLoader.loadUsers();

        assertNotNull(users);
    }

    @Test
    void parseUser() {
        UserLoader userLoader = new UserLoader();

        User user = userLoader.parseUser("1,아이디,비번,토끼");

        assertEquals("id: 1, userName: 아이디, password: 비번, nickname: 토끼", user.toString());
    }
}
