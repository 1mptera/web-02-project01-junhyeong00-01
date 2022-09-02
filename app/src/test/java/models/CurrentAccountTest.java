package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentAccountTest {
    @Test
    void creation() {
        CurrentAccount currentAccount = new CurrentAccount(1, "토끼");

        assertEquals(1, currentAccount.id());
        assertEquals("토끼", currentAccount.nickname());
    }

    @Test
    void login() {
        CurrentAccount currentAccount = new CurrentAccount(0, "");

        currentAccount.login(1, "토끼");

        assertEquals(1, currentAccount.id());
        assertEquals("토끼", currentAccount.nickname());
    }

    @Test
    void logout() {
        CurrentAccount currentAccount = new CurrentAccount(1, "토끼");

        currentAccount.logout();

        assertEquals(-1, currentAccount.id());
        assertEquals("", currentAccount.nickname());
    }

    @Test
    void edit() {
        CurrentAccount currentAccount = new CurrentAccount(1, "토끼");

        Post post = new Post(1,"제목", "내용", "토끼", 1, "디지털기기", 2000, "판매중", false);
        currentAccount.edit(post, "제목2", "내용2", "가구", 1000);

        assertEquals("제목2", post.title());
        assertEquals("내용2", post.content());
        assertEquals("가구", post.category());
        assertEquals(1000, post.secondHandItemPrice());
    }

    @Test
    void complete() {
        CurrentAccount currentAccount = new CurrentAccount(1, "토끼");

        Transaction transaction = new Transaction(1, 1, 1, "토끼", 2, "사람", "거래중");

        currentAccount.complete(transaction);

        assertEquals("거래완료", transaction.isStatus());
    }
}
