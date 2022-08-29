package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void creation() {
        User user = new User("id", "비번", "토끼");

        assertEquals("id", user.userName());
        assertEquals("비번", user.password());
        assertEquals("토끼", user.nickname());
    }

    @Test
    void toCsvRow() {
        User user = new User("id", "비번", "토끼");

        assertEquals("id,비번,토끼", user.toCsvRow());
    }
}