package utils;

import models.Transaction;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionLoaderTest {
    @Test
    void loadTransactions() throws FileNotFoundException {
        TransactionLoader transactionLoader = new TransactionLoader();

        List<Transaction> transactions = transactionLoader.loadTransactions();

        assertNotNull(transactions);
    }

    @Test
    void parseTransaction() {
        TransactionLoader transactionLoader = new TransactionLoader();

        Transaction transaction = transactionLoader.parseTransaction("1,2,3,근손실 싫어,1,토끼,거래완료");

        assertEquals("id: 1, postId: 2, sellerId: 3, sellerNickname: 근손실 싫어," +
                " buyerId: 1, buyerNickname: 토끼, status: 거래완료", transaction.toString());
    }
}
