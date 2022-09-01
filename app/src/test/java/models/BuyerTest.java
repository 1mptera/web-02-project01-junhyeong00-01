package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    @Test
    void creation() {
        Buyer buyer = new Buyer(1, "토끼");

        assertEquals(1, buyer.id());
        assertEquals("토끼", buyer.nickname());
    }

    @Test
    void login() {
        Buyer buyer = new Buyer(0, "");

        buyer.login(1, "토끼");

        assertEquals(1, buyer.id());
        assertEquals("토끼", buyer.nickname());
    }

    @Test
    void logout() {
        Buyer buyer = new Buyer(1, "토끼");

        buyer.logout();

        assertEquals(-1, buyer.id());
        assertEquals("", buyer.nickname());
    }
}
