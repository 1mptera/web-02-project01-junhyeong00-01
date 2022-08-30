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
}
