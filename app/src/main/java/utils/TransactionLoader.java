package utils;

import models.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionLoader {
    private Scanner scanner;

    public List<Transaction> loadTransactions() throws FileNotFoundException {
        List<Transaction> transactions = new ArrayList<>();

        File file = new File("data/transactionsData.csv");

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return transactions;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Transaction transaction = parseTransaction(line);
            transactions.add(transaction);
        }

        return transactions;
    }

    public Transaction parseTransaction(String text) {
        String[] words = text.split(",");

        long id = Long.parseLong(words[0]);
        long postId = Long.parseLong(words[1]);
        long sellerId = Long.parseLong(words[2]);
        long buyerId = Long.parseLong(words[4]);
        return new Transaction(id, postId, sellerId, words[3], buyerId, words[5], words[6]);
    }

    public void saveTransactions(List<Transaction> transactions) throws IOException {
        FileWriter fileWriter = new FileWriter("data/transactionsData.csv");

        for (Transaction transaction : transactions) {
            String line = transaction.toCsvRow();
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }
}
