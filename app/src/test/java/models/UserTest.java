package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation() {
        User user = new User("id", "비번", "토끼", 1);

        assertEquals("id", user.userName());
        assertEquals("비번", user.password());
        assertEquals("토끼", user.nickname());
        assertEquals(1, user.id());
    }

    @Test
    void toCsvRow() {
        User user = new User("id", "비번", "토끼", 1);

        assertEquals("1,id,비번,토끼", user.toCsvRow());
    }
}
