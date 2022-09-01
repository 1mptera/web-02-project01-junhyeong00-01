package utils;

import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLoader {

    private Scanner scanner;

    public List<User> loadUsers() throws FileNotFoundException {
        List<User> posts = new ArrayList<>();

        File file = new File("data/usersData.csv");

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return posts;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            User user = parseUser(line);
            posts.add(user);
        }

        return posts;
    }

    public User parseUser(String text) {
        String[] words = text.split(",");

        long id = Long.parseLong(words[0]);
        return new User(words[1], words[2], words[3], id);
    }

    public void saveUsers(List<User> users) throws IOException {
        FileWriter fileWriter = new FileWriter("data/usersData.csv");

        for (User user : users) {
            String line = user.toCsvRow();
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }
}
