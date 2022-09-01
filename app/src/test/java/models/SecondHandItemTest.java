package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecondHandItemTest {
    @Test
    void creation() {
        SecondHandItem secondHandItem = new SecondHandItem(1000, "가구");

        assertEquals(1000, secondHandItem.price());
        assertEquals("가구", secondHandItem.category());
    }
}
