package utils;

import models.Post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostLoader {
    private Scanner scanner;

    public List<Post> loadPosts() throws FileNotFoundException {
        List<Post> posts = new ArrayList<>();

        File file = new File("data/postsData.csv");

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return posts;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Post post = parsePost(line);
            posts.add(post);
        }

        return posts;
    }

    public Post parsePost(String text) {
        String[] words = text.split(",");

        long id = Long.parseLong(words[0]);
        long sellerId = Long.parseLong(words[4]);
        long  secondHandItemPrice = Long.parseLong(words[6]);
        boolean deleted = Boolean.parseBoolean(words[8]);
        return new Post(id, words[1], words[2], words[3], sellerId, words[5], secondHandItemPrice,words[7], deleted);
    }

    public void savePosts(List<Post> posts) throws IOException {
        FileWriter fileWriter = new FileWriter("data/postsData.csv");

        for (Post post : posts) {
            String line = post.toCsvRow();
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }
}
